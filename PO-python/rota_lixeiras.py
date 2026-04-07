"""
Otimização de Rota de Coleta de Lixeiras Hospitalares
======================================================
Grafo e Programação (VRP/TSP) com NetworkX
Autor: Vitor Saddi Ribeiro — IC PUC Goiás
"""
import os
import math
import random
import itertools
import networkx as nx
import matplotlib.pyplot as plt
import matplotlib.patches as mpatches
from matplotlib.gridspec import GridSpec

# ══════════════════════════════════════════════════════════════
# 1. CONFIGURAÇÃO DOS NÓS (Layout Lógico e Balanceado)
# ══════════════════════════════════════════════════════════════

os.system("cls" if os.name == "nt" else "clear") # Limpa o terminal para melhor visualização

START_NODE = 0
END_NODE = 21

NODES = {
    0: {"name": "Início (Carrinhos)",   "x": 0,     "y": 0,     "depot": True},
    
    # Ala de Urgência (Perto da Entrada)
    7: {"name": "Triagem",              "x": 20,    "y": 30,    "depot": False},
    3: {"name": "Emergência",           "x": 40,    "y": 20,    "depot": False},
    1: {"name": "UTI",                  "x": 20,    "y": 70,    "depot": False},
    
    # Ala de Imagens e Diagnósticos
    8: {"name": "Radiologia",           "x": 50,    "y": 50,    "depot": False},
    11:{"name": "Laboratório",          "x": 30,    "y": 100,   "depot": False},
    
    # Ala Cirúrgica e Especialidades (Restrito)
    2: {"name": "Centro Cirúrgico",     "x": 40,    "y": 130,   "depot": False},
    12:{"name": "Hematologia",          "x": 70,    "y": 140,   "depot": False},
    16:{"name": "Obstetrícia",          "x": 70,    "y": 110,   "depot": False},
    18:{"name": "Quimioterapia",        "x": 60,    "y": 80,    "depot": False},
    
    # Ala Ambulatorial e Reabilitação
    15:{"name": "Fisioterapia",         "x": 80,    "y": 20,    "depot": False},
    17:{"name": "Ortopedia",            "x": 110,   "y": 40,    "depot": False},
    10:{"name": "Ambulatório",           "x": 130,   "y": 30,   "depot": False},
    
    # Ala de Internação (Coração do Hospital)
    20:{"name": "Internação",           "x": 90,    "y": 90,    "depot": False},
    5: {"name": "Enfermaria A",         "x": 90,    "y": 130,   "depot": False},
    6: {"name": "Enfermaria B",         "x": 140,   "y": 120,   "depot": False},
    9: {"name": "Pediatria",            "x": 140,   "y": 100,   "depot": False},
    
    # Apoio e Logística Distribuída
    4: {"name": "Farmácia",             "x": 110,   "y": 80,    "depot": False},
    14:{"name": "Nutrição",             "x": 110,   "y": 110,   "depot": False},
    13:{"name": "Administração",        "x": 130,   "y": 70,    "depot": False},
    19:{"name": "Higienização",         "x": 120,   "y": 140,   "depot": False},
    
    # Saída
    21:{"name": "Fim (Descarte)",       "x": 150,   "y": 150,   "depot": True},
}

# ══════════════════════════════════════════════════════════════════
# 2. ENTRADA DE DADOS
# ══════════════════════════════════════════════════════════════════
print("=" * 60)
print("  SISTEMA DE OTIMIZAÇÃO DE ROTA — LIXEIRAS HOSPITALARES IoT")
print("=" * 60)
print("\nOpção 1: Usar dados randômicos")
print("Opção 2: Inserir dados manualmente")

escolha = input("\nEscolha uma opção (1 ou 2): ").strip()
fill_levels = {}

if escolha == "1":
    print("\n[!] Gerando níveis randômicos para as lixeiras...\n")
    for nid, node in NODES.items():
        if not node["depot"]:
            fill_levels[nid] = random.randint(20, 99)
            print(f"  [{node['name']:20s}] nível gerado: {fill_levels[nid]}%")
    print()

elif escolha == "2":
    print("\nInforme o nível de preenchimento de cada lixeira (0–100%).")
    print("Pressione ENTER para usar o valor padrão entre colchetes.\n")

    DEFAULTS = {
        1: 82, 2: 45, 3: 91, 4: 30, 5: 74, 6: 58, 7: 88, 8: 63, 9: 50, 10: 77,
        11: 69, 12: 54, 13: 40, 14: 85, 15: 33, 16: 60, 17: 47, 18: 92, 19: 38, 20: 71,
    }

    for nid, node in NODES.items():
        if node["depot"]:
            continue
        default = DEFAULTS[nid]
        while True:
            raw = input(f"  [{node['name']:20s}] nível atual [{default:3d}%]: ").strip()
            if raw == "":
                fill_levels[nid] = default
                break
            try:
                val = int(raw)
                if 0 <= val <= 100:
                    fill_levels[nid] = val
                    break
                print("    ⚠ Digite um valor entre 0 e 100.")
            except ValueError:
                print("    ⚠ Valor inválido. Digite apenas números inteiros.")
    print()

else:
    print("\nOpção inválida! Encerrando o programa.")
    exit()

# ── Configurações do Solver ──────────────────────────────────────

THRESHOLD = 60
print(f"  -> Limiar de coleta fixado em: {THRESHOLD}%")
CAPACITY = 5
print(f"  -> Capacidade máxima por carrinho: {CAPACITY}")
print("\nProcessando rotas (C&W + Relocate + Swap + TSP Exato)...\n")

# ══════════════════════════════════════════════════════════════
# 3. CRIAÇÃO DO GRAFO COM NETWORKX
# ══════════════════════════════════════════════════════════════

G = nx.Graph()

for nid, data in NODES.items():
    G.add_node(nid, pos=(data["x"], data["y"]), name=data["name"], depot=data["depot"])

for i in G.nodes:
    for j in G.nodes:
        if i < j:
            dist = round(math.hypot(NODES[i]["x"] - NODES[j]["x"], NODES[i]["y"] - NODES[j]["y"]), 2)
            G.add_edge(i, j, weight=dist)

# ══════════════════════════════════════════════════════════════
# 4. RESOLUÇÃO (C&W + ROUBO + TROCA + FORÇA BRUTA)
# ══════════════════════════════════════════════════════════════

must_visit = [n for n in G.nodes if not G.nodes[n]["depot"] and fill_levels.get(n, 0) >= THRESHOLD]

if not must_visit:
    print("Nenhuma lixeira precisa ser coletada!")
    exit()

# Função auxiliar: Acha a melhor rota e distância para um grupo de nós
def get_optimal_route_dist(cluster):
    if not cluster:
        return 0, []
    min_dist = float('inf')
    best_r = []
    for perm in itertools.permutations(cluster):
        r = [START_NODE] + list(perm) + [END_NODE]
        d = sum(G[r[k]][r[k+1]]['weight'] for k in range(len(r)-1))
        if d < min_dist:
            min_dist = d
            best_r = r
    return min_dist, best_r

# --- FASE 1: Agrupamento Inicial por Economias (Clarke & Wright) ---
clusters = [[n] for n in must_visit]
savings = []
for i in must_visit:
    for j in must_visit:
        if i != j:
            s = G[i][END_NODE]['weight'] + G[START_NODE][j]['weight'] - G[i][j]['weight']
            savings.append((s, i, j))

savings.sort(key=lambda x: x[0], reverse=True)

for s, i, j in savings:
    idx_i = next((idx for idx, c in enumerate(clusters) if i in c), -1)
    idx_j = next((idx for idx, c in enumerate(clusters) if j in c), -1)
    
    if idx_i != -1 and idx_j != -1 and idx_i != idx_j:
        if len(clusters[idx_i]) + len(clusters[idx_j]) <= CAPACITY:
            clusters[idx_i].extend(clusters[idx_j])
            del clusters[idx_j]

# --- FASE 2: BUSCA LOCAL (ROUBO & TROCA) ---
searching = True
while searching:
    searching = False
    
    # 2.1 ROUBO (Relocate)
    for i in range(len(clusters)):
        for j in range(len(clusters)):
            if i == j or len(clusters[j]) >= CAPACITY: continue 

            for node in clusters[i]:
                cost_i, _ = get_optimal_route_dist(clusters[i])
                cost_j, _ = get_optimal_route_dist(clusters[j])
                current_cost = cost_i + cost_j

                new_cluster_i = [n for n in clusters[i] if n != node]
                new_cluster_j = clusters[j] + [node]

                new_cost_i, _ = get_optimal_route_dist(new_cluster_i)
                new_cost_j, _ = get_optimal_route_dist(new_cluster_j)
                
                if (new_cost_i + new_cost_j) < current_cost - 0.01:
                    clusters[i] = new_cluster_i
                    clusters[j] = new_cluster_j
                    searching = True
                    break
            if searching: break
        if searching: break
        
    if searching: continue 

    # 2.2 ESCAMBO (Swap)
    for i in range(len(clusters)):
        for j in range(i + 1, len(clusters)):
            for node_i in clusters[i]:
                for node_j in clusters[j]:
                    cost_i, _ = get_optimal_route_dist(clusters[i])
                    cost_j, _ = get_optimal_route_dist(clusters[j])
                    current_cost = cost_i + cost_j

                    new_cluster_i = [n for n in clusters[i] if n != node_i] + [node_j]
                    new_cluster_j = [n for n in clusters[j] if n != node_j] + [node_i]

                    new_cost_i, _ = get_optimal_route_dist(new_cluster_i)
                    new_cost_j, _ = get_optimal_route_dist(new_cluster_j)
                    
                    if (new_cost_i + new_cost_j) < current_cost - 0.01:
                        clusters[i] = new_cluster_i
                        clusters[j] = new_cluster_j
                        searching = True
                        break
                if searching: break
            if searching: break
        if searching: break

clusters = [c for c in clusters if c]
# -----------------------------------------------------------------

trips = []
total_dist = 0
trip_lengths = []

for chunk in clusters:
    dist, route = get_optimal_route_dist(chunk)
    trips.append(route)
    trip_lengths.append(dist)
    total_dist += dist

ignored = [n for n in G.nodes if not G.nodes[n]["depot"] and n not in must_visit]

print("─" * 50)
for t, (trip, length) in enumerate(zip(trips, trip_lengths)):
    names = " → ".join("Início" if n == START_NODE else "Descarte" if n == END_NODE else G.nodes[n]["name"].split()[0] for n in trip)
    print(f"  Viagem {t+1} (Carga: {len(trip)-2}): {names}")
    print(f"           Distância: {length:.1f} m")
print("─" * 50)
print(f"  DISTÂNCIA TOTAL ÓTIMA: {total_dist:.1f} m")
print(f"  Lixeiras coletadas:    {len(must_visit)}")
print(f"  Lixeiras ignoradas:    {len(ignored)}")
print("─" * 50)

# ══════════════════════════════════════════════════════════════
# 5. VISUALIZAÇÃO COM NETWORKX + MATPLOTLIB
# ══════════════════════════════════════════════════════════════

PALETTE = {
    "depot":   "#185FA5", "route":   "#E24B4A", "ignored": "#B4B2A9",
    "edge_bg": "#E0DED8", "bg":      "#FAFAF8", "text":    "#2C2C2A", "grid": "#EDECEA",
}
base_colors = ["#185FA5", "#0F6E56", "#854F0B", "#A32D2D", "#533AB7", "#993556", "#3B6D11", "#D18B15"]
TRIP_COLORS = [base_colors[i % len(base_colors)] for i in range(max(10, len(trips)))]

fig = plt.figure(figsize=(16, 11), facecolor=PALETTE["bg"])
fig.suptitle("Otimização de Rota — Coleta de Lixeiras Hospitalares IoT\n"
             f"Threshold: {THRESHOLD}%  |  Capacidade: {CAPACITY} coletas/viagem  |  "
             f"Distância total ótima: {total_dist:.1f} m",
             fontsize=13, fontweight='bold', color=PALETTE["text"], y=0.98)

gs = GridSpec(2, 3, figure=fig, left=0.05, right=0.97, top=0.93, bottom=0.06, hspace=0.38, wspace=0.32)

# ── GRÁFICO 1: Mapa do hospital ──────────────────────────────
ax_map = fig.add_subplot(gs[:, :2])
ax_map.set_facecolor(PALETTE["bg"])

pos = nx.get_node_attributes(G, 'pos')

# Desenha as arestas de fundo
nx.draw_networkx_edges(G, pos, ax=ax_map, edge_color=PALETTE["edge_bg"], width=0.6)

# Desenha as arestas da rota
for t, trip in enumerate(trips):
    col = TRIP_COLORS[t]
    route_edges = [(trip[k], trip[k+1]) for k in range(len(trip)-1)]
    nx.draw_networkx_edges(G, pos, edgelist=route_edges, ax=ax_map, edge_color=col, 
                           width=2.5, arrows=True, arrowsize=15, connectionstyle='arc3,rad=0.15')
    
    edge_labels = { (u, v): f"{G[u][v]['weight']:.0f}m" for u, v in route_edges }
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, ax=ax_map, 
                                 font_color=col, font_size=8, font_weight='bold')

# Desenha os nós
node_colors = [PALETTE["depot"] if G.nodes[n]["depot"] else (PALETTE["route"] if n in must_visit else PALETTE["ignored"]) for n in G.nodes]
nx.draw_networkx_nodes(G, pos, ax=ax_map, node_color=node_colors, node_size=600, edgecolors="white", linewidths=1.5)

labels = {n: "I" if n == START_NODE else "F" if n == END_NODE else str(n) for n in G.nodes}
nx.draw_networkx_labels(G, pos, labels, ax=ax_map, font_color="white", font_size=10, font_weight="bold")

# Rótulos e Preenchimentos
for n in G.nodes:
    x, y = pos[n]
    nome = G.nodes[n]["name"].replace("Enfermaria", "Enf.").replace(" (Carrinhos)", "").replace(" (Descarte)", "")
    ax_map.text(x, y + 5, nome, ha='center', fontsize=8, color=PALETTE["text"], 
                bbox=dict(boxstyle='round', fc=PALETTE["bg"], ec='none', alpha=0.8))
    
    if not G.nodes[n]["depot"]:
        pct = fill_levels[n]
        badge_col = PALETTE["route"] if pct >= THRESHOLD else PALETTE["ignored"]
        ax_map.text(x, y - 5, f"{pct}%", ha='center', va='top', fontsize=8, color=badge_col, fontweight='bold')

ax_map.set_xlim(-10, 160)
ax_map.set_ylim(-10, 160)
ax_map.axis('off')

# Legenda do Mapa
legend_handles = [
    mpatches.Patch(color=PALETTE["depot"],   label="Depósitos (I = Início, F = Fim)"),
    mpatches.Patch(color=PALETTE["route"],   label=f"Lixeira na rota (≥{THRESHOLD}%)"),
    mpatches.Patch(color=PALETTE["ignored"], label=f"Ignorada (<{THRESHOLD}%)"),
]
for t in range(len(trips)):
    legend_handles.append(mpatches.Patch(color=TRIP_COLORS[t], label=f"Viagem {t+1} (Carga: {len(trips[t])-2})"))
ax_map.legend(handles=legend_handles, loc='lower right', fontsize=8, framealpha=0.9)

# ── GRÁFICO 2: Nível de preenchimento ────────
ax_bar = fig.add_subplot(gs[0, 2])
ax_bar.set_facecolor(PALETTE["bg"])
ax_bar.set_title("Nível de Preenchimento", fontsize=10, fontweight='bold', color=PALETTE["text"])

ids   = [n for n in G.nodes if not G.nodes[n]["depot"]]
names = [G.nodes[n]["name"].replace("Enfermaria", "Enf.").replace("Centro ", "Ctr.") for n in ids]
pcts  = [fill_levels[n] for n in ids]
colors_bar = [PALETTE["route"] if p >= THRESHOLD else PALETTE["ignored"] for p in pcts]

bars = ax_bar.barh(names, pcts, color=colors_bar, edgecolor='white', linewidth=0.6, height=0.65)
ax_bar.axvline(x=THRESHOLD, color="#185FA5", lw=1.5, linestyle='--', label=f"Limiar ({THRESHOLD}%)")

for bar, pct in zip(bars, pcts):
    ax_bar.text(min(pct + 1.5, 97), bar.get_y() + bar.get_height()/2, f"{pct}%", 
                va='center', ha='left', fontsize=8, color=PALETTE["text"], fontweight='bold')

ax_bar.set_xlim(0, 110)
ax_bar.set_xlabel("Preenchimento (%)", fontsize=8, color=PALETTE["text"])
ax_bar.legend(fontsize=7.5, framealpha=0.9)
ax_bar.grid(axis='x', color=PALETTE["grid"], lw=0.5)

# ── GRÁFICO 3: Distância por viagem ──────────
ax_trip = fig.add_subplot(gs[1, 2])
ax_trip.set_facecolor(PALETTE["bg"])
ax_trip.set_title("Distância por Viagem", fontsize=10, fontweight='bold', color=PALETTE["text"])

if trips:
    trip_labels = [f"V {i+1}" for i in range(len(trips))]
    bars2 = ax_trip.bar(trip_labels, trip_lengths, color=TRIP_COLORS[:len(trips)], edgecolor='white', width=0.55)

    for bar, length in zip(bars2, trip_lengths):
        ax_trip.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 0.5,
                     f"{length:.1f}m", ha='center', va='bottom', fontsize=8.5, fontweight='bold', color=PALETTE["text"])

    ax_trip.axhline(y=total_dist/len(trips), color="#888780", lw=1.2, linestyle=':', label=f"Média: {total_dist/len(trips):.1f}m")
    ax_trip.set_ylim(0, max(trip_lengths) * 1.3)

ax_trip.set_ylabel("Distância (m)", fontsize=8, color=PALETTE["text"])
ax_trip.legend(fontsize=7.5, framealpha=0.9)
ax_trip.grid(axis='y', color=PALETTE["grid"], lw=0.5)

# ── Rodapé atualizado ────────────────────────────────────────
fig.text(0.05, 0.01,
         "Modelo Final: Mapa Balanceado + Clarke & Wright + Busca Local + TSP Exato",
         fontsize=8, color="#888780", ha='left', va='bottom')

# Exibe o gráfico diretamente na tela
print("\nGerando gráfico na tela...")
plt.show()