"""
Otimização de Rota de Coleta de Lixeiras Hospitalares
Autor: Caio Leal, Raphael Avila, João Marcelo Angeli, Vitor Saddi Ribeiro
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
# 1. CONFIGURAÇÃO DOS NÓS (posições em metros dentro do hospital)
# ══════════════════════════════════════════════════════════════

os.system("cls" if os.name == "nt" else "clear")

START_NODE = 0
END_NODE = 21

NODES = {
    0: {"name": "Início (Carrinhos)",   "x": 0,     "y": 0,     "depot": True},
    1: {"name": "UTI",                  "x": 10,    "y": 90,    "depot": False},
    2: {"name": "Centro Cirúrgico",     "x": 30,    "y": 130,   "depot": False},
    3: {"name": "Emergência",           "x": 40,    "y": 20,    "depot": False},
    4: {"name": "Farmácia",             "x": 110,   "y": 80,    "depot": False},
    5: {"name": "Enfermaria A",         "x": 90,    "y": 130,   "depot": False},
    6: {"name": "Enfermaria B",         "x": 140,   "y": 120,   "depot": False},
    7: {"name": "Triagem",              "x": 20,    "y": 30,    "depot": False},
    8: {"name": "Radiologia",           "x": 50,    "y": 50,    "depot": False},
    9: {"name": "Pediatria",            "x": 140,   "y": 100,   "depot": False},
    10:{"name": "Ambulatório",           "x": 130,   "y": 30,   "depot": False},
    11:{"name": "Laboratório",          "x": 30,    "y": 100,   "depot": False},
    12:{"name": "Hematologia",          "x": 70,    "y": 140,   "depot": False},
    13:{"name": "Administração",        "x": 130,   "y": 70,    "depot": False},
    14:{"name": "Nutrição",             "x": 110,   "y": 110,   "depot": False},
    15:{"name": "Fisioterapia",         "x": 80,    "y": 20,    "depot": False},
    16:{"name": "Obstetrícia",          "x": 70,    "y": 110,   "depot": False},
    17:{"name": "Ortopedia",            "x": 110,   "y": 40,    "depot": False},
    18:{"name": "Quimioterapia",        "x": 60,    "y": 80,    "depot": False},
    19:{"name": "Higienização",         "x": 120,   "y": 140,   "depot": False},
    20:{"name": "Internação",           "x": 90,    "y": 90,    "depot": False},
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
            print(f"  [{node['name']:20s}] nível gerado: {fill_levels[nid]/100:g}L")
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

#Restriçoes fixas para o modelo final

THRESHOLD = 60
print(f"  -> Limiar de coleta fixado em: {THRESHOLD/100:g}L")

CAPACITY = 400
print(f"  -> Capacidade fixada em: {CAPACITY/100:g}L")

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
# 4. RESOLUÇÃO
# ══════════════════════════════════════════════════════════════

must_visit = [n for n in G.nodes if not G.nodes[n]["depot"] and fill_levels.get(n, 0) >= THRESHOLD]

if not must_visit:
    print("Nenhuma lixeira precisa ser coletada!")
    exit()

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
        vol_i = sum(fill_levels[n] for n in clusters[idx_i])
        vol_j = sum(fill_levels[n] for n in clusters[idx_j])
        if vol_i + vol_j <= CAPACITY:
            clusters[idx_i].extend(clusters[idx_j])
            del clusters[idx_j]

searching = True
while searching:
    searching = False
    
    for i in range(len(clusters)):
        for j in range(len(clusters)):
            if i == j: continue 

            for node in clusters[i]:
                vol_j = sum(fill_levels[n] for n in clusters[j])
                if vol_j + fill_levels[node] > CAPACITY: 
                    continue

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

    for i in range(len(clusters)):
        for j in range(i + 1, len(clusters)):
            for node_i in clusters[i]:
                for node_j in clusters[j]:
                    vol_i = sum(fill_levels[n] for n in clusters[i])
                    vol_j = sum(fill_levels[n] for n in clusters[j])
                    new_vol_i = vol_i - fill_levels[node_i] + fill_levels[node_j]
                    new_vol_j = vol_j - fill_levels[node_j] + fill_levels[node_i]
                    
                    if new_vol_i > CAPACITY or new_vol_j > CAPACITY:
                        continue

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

trips = []
trip_vols = []
total_dist = 0
trip_lengths = []

for chunk in clusters:
    dist, route = get_optimal_route_dist(chunk)
    vol = sum(fill_levels[n] for n in chunk)
    trips.append(route)
    trip_vols.append(vol)
    trip_lengths.append(dist)
    total_dist += dist

ignored = [n for n in G.nodes if not G.nodes[n]["depot"] and n not in must_visit]

print("─" * 50)
for t, (trip, length, vol) in enumerate(zip(trips, trip_lengths, trip_vols)):
    names = " → ".join("Início" if n == START_NODE else "Descarte" if n == END_NODE else G.nodes[n]["name"].split()[0] for n in trip)
    # Impressão convertida para Litros
    print(f"  Viagem {t+1} (Carga: {vol/100:g}L / {CAPACITY/100:g}L): {names}")
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
             f"Threshold: {THRESHOLD/100:g}L  |  Capacidade Max: {CAPACITY/100:g}L de Volume  |  "
             f"Distância total ótima: {total_dist:.1f} m",
             fontsize=13, fontweight='bold', color=PALETTE["text"], y=0.98)

gs = GridSpec(3, 3, figure=fig, left=0.05, right=0.97, top=0.93, bottom=0.06, hspace=0.45, wspace=0.32)

# ── GRÁFICO 1: Mapa do hospital ──────────────────────────────
ax_map = fig.add_subplot(gs[:, :2])
ax_map.set_facecolor(PALETTE["bg"])

pos = nx.get_node_attributes(G, 'pos')

nx.draw_networkx_edges(G, pos, ax=ax_map, edge_color=PALETTE["edge_bg"], width=0.6)

for t, trip in enumerate(trips):
    col = TRIP_COLORS[t]
    route_edges = [(trip[k], trip[k+1]) for k in range(len(trip)-1)]
    # Curvatura reduzida de 0.15 para 0.08 para ficar mais limpo
    nx.draw_networkx_edges(G, pos, edgelist=route_edges, ax=ax_map, edge_color=col, 
                           width=2.5, arrows=True, arrowsize=15, connectionstyle='arc3,rad=0.08')
    
    edge_labels = { (u, v): f"{G[u][v]['weight']:.0f}m" for u, v in route_edges }
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, ax=ax_map, 
                                 font_color=col, font_size=8, font_weight='bold')

node_colors = [PALETTE["depot"] if G.nodes[n]["depot"] else (PALETTE["route"] if n in must_visit else PALETTE["ignored"]) for n in G.nodes]
nx.draw_networkx_nodes(G, pos, ax=ax_map, node_color=node_colors, node_size=600, edgecolors="white", linewidths=1.5)

labels = {n: "I" if n == START_NODE else "F" if n == END_NODE else str(n) for n in G.nodes}
nx.draw_networkx_labels(G, pos, labels, ax=ax_map, font_color="white", font_size=10, font_weight="bold")

for n in G.nodes:
    x, y = pos[n]
    nome = G.nodes[n]["name"].replace("Enfermaria", "Enf.").replace(" (Carrinhos)", "").replace(" (Descarte)", "")
    ax_map.text(x, y + 5, nome, ha='center', fontsize=8, color=PALETTE["text"], 
                bbox=dict(boxstyle='round', fc=PALETTE["bg"], ec='none', alpha=0.8))
    
    if not G.nodes[n]["depot"]:
        pct = fill_levels[n]
        badge_col = PALETTE["route"] if pct >= THRESHOLD else PALETTE["ignored"]
        # Convertido para Litros no mapa
        ax_map.text(x, y - 5, f"{pct/100:g}L", ha='center', va='top', fontsize=8, color=badge_col, fontweight='bold')

ax_map.set_xlim(-10, 160)
ax_map.set_ylim(-10, 160)
ax_map.axis('off')

legend_handles = [
    mpatches.Patch(color=PALETTE["depot"],   label="Depósitos (I = Início, F = Fim)"),
    mpatches.Patch(color=PALETTE["route"],   label=f"Lixeira na rota (≥{THRESHOLD/100:g}L)"),
    mpatches.Patch(color=PALETTE["ignored"], label=f"Ignorada (<{THRESHOLD/100:g}L)"),
]
for t in range(len(trips)):
    legend_handles.append(mpatches.Patch(color=TRIP_COLORS[t], label=f"Viagem {t+1} (Vol: {trip_vols[t]/100:g}L)"))
ax_map.legend(handles=legend_handles, loc='lower right', fontsize=8, framealpha=0.9)

# ── GRÁFICO 2: Nível de preenchimento ────────
ax_bar = fig.add_subplot(gs[0, 2])
ax_bar.set_facecolor(PALETTE["bg"])
ax_bar.set_title("Nível de Preenchimento", fontsize=10, fontweight='bold', color=PALETTE["text"])

ids   = [n for n in G.nodes if not G.nodes[n]["depot"]]
names = [G.nodes[n]["name"].replace("Enfermaria", "Enf.").replace("Centro ", "Ctr.") for n in ids]
# Eixo X das barras mapeado para Litros
pcts_L  = [fill_levels[n] / 100 for n in ids]
colors_bar = [PALETTE["route"] if p * 100 >= THRESHOLD else PALETTE["ignored"] for p in pcts_L]

bars = ax_bar.barh(names, pcts_L, color=colors_bar, edgecolor='white', linewidth=0.6, height=0.65)
ax_bar.axvline(x=THRESHOLD/100, color="#185FA5", lw=1.5, linestyle='--', label=f"Limiar ({THRESHOLD/100:g}L)")

for bar, pct_L in zip(bars, pcts_L):
    ax_bar.text(min(pct_L + 0.015, 1.05), bar.get_y() + bar.get_height()/2, f"{pct_L:g}L", 
                va='center', ha='left', fontsize=8, color=PALETTE["text"], fontweight='bold')

ax_bar.set_xlim(0, 1.1)
ax_bar.set_xlabel("Preenchimento (L)", fontsize=8, color=PALETTE["text"])
ax_bar.legend(fontsize=7.5, framealpha=0.9)
ax_bar.grid(axis='x', color=PALETTE["grid"], lw=0.5)

# ── GRÁFICO 3: Lixo Acumulado por Viagem (Barras Empilhadas) ───
ax_trash = fig.add_subplot(gs[1, 2])
ax_trash.set_facecolor(PALETTE["bg"])
ax_trash.set_title("Volume de Lixo por Viagem", fontsize=10, fontweight='bold', color=PALETTE["text"])

STACK_COLORS = ["#4A8F79", "#F07C41", "#533AB7", "#E24B4A", "#F4C244", "#2B6888", "#8D4D85", "#B85B67", "#39719E"]

if trips:
    trip_labels = [f"V {i+1}" for i in range(len(trips))]
    
    for t, trip in enumerate(trips):
        trip_label = trip_labels[t]
        bottom_L = 0
        nodes_in_trip = [n for n in trip if not G.nodes[n]["depot"]]
        
        for i, n in enumerate(nodes_in_trip):
            val_L = fill_levels[n] / 100
            c = STACK_COLORS[i % len(STACK_COLORS)]
            ax_trash.bar(trip_label, val_L, bottom=bottom_L, color=c, edgecolor='white', width=0.55)
            
            # Textos dentro dos bloquinhos ajustados para Litros
            if val_L >= 0.25:
                ax_trash.text(trip_label, bottom_L + val_L/2, f"Nó {n}\n{val_L:g}L", ha='center', va='center', 
                              fontsize=7, color='white', fontweight='bold')
            bottom_L += val_L
            
        ax_trash.text(trip_label, bottom_L + 0.05, f"{bottom_L:g}L", ha='center', va='bottom', 
                      fontsize=8.5, fontweight='bold', color=PALETTE["text"])

    ax_trash.axhline(y=CAPACITY/100, color="#E24B4A", lw=1.5, linestyle='--', label=f"Capacidade Máx ({CAPACITY/100:g}L)")
    
    ax_trash.set_ylim(0, (CAPACITY/100) * 1.15)
    ax_trash.set_ylabel("Volume Acumulado (L)", fontsize=8, color=PALETTE["text"])
    ax_trash.legend(fontsize=7.5, framealpha=0.9, loc='upper right')
    ax_trash.grid(axis='y', color=PALETTE["grid"], lw=0.5)

# ── GRÁFICO 4: Distância por viagem ──────────
ax_trip = fig.add_subplot(gs[2, 2])
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
         "Modelo Final: Backend em % | Frontend convertido em Litros (L)",
         fontsize=8, color="#888780", ha='left', va='bottom')

# Exibe o gráfico diretamente na tela
print("\nGerando gráfico na tela...")
plt.show()