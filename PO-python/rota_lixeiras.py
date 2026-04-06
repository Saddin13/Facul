"""
Autores: Caio Leal, João Marcelo Angeli, Raphael Ávila e Vitor Saddi
Projeto: Sistema de otimização de rotas de coleta de lixeiras hospitalares usando IoT
"""

import os
import math
import random
import itertools
import networkx as nx
import matplotlib.pyplot as plt
import matplotlib.patches as mpatches
from matplotlib.gridspec import GridSpec

os.system("cls" if os.name == "nt" else "clear") 

# ══════════════════════════════════════════════════════════════
# 1. CONFIGURAÇÃO DOS NÓS (posições em metros dentro do hospital)
# ══════════════════════════════════════════════════════════════

NODES = {
    0: {"name": "Depósito",          "x": 50,  "y": 50,  "depot": True},
    1: {"name": "UTI",               "x": 10,  "y": 90,  "depot": False},
    2: {"name": "Centro Cirúrgico",  "x": 30,  "y": 130, "depot": False},
    3: {"name": "Emergência",        "x": 90,  "y": 130, "depot": False},
    4: {"name": "Farmácia",          "x": 120, "y": 80,  "depot": False},
    5: {"name": "Enfermaria A",      "x": 110, "y": 20,  "depot": False},
    6: {"name": "Enfermaria B",      "x": 60,  "y": 10,  "depot": False},
    7: {"name": "Triagem",           "x": 10,  "y": 30,  "depot": False},
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

os.system("cls" if os.name == "nt" else "clear") 

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

    DEFAULTS = {1: 82, 2: 45, 3: 91, 4: 30, 5: 74, 6: 58, 7: 88}

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

THRESHOLD = 60
print(f"  -> Limiar de coleta fixado em: {THRESHOLD}%")

CAPACITY = 5
print(f"  -> Capacidade definida: {CAPACITY}")
print("\nProcessando rotas...\n")

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
# 4. RESOLUÇÃO DO PROBLEMA (TSP - FORÇA BRUTA)
# ══════════════════════════════════════════════════════════════

must_visit = [n for n in G.nodes if not G.nodes[n]["depot"] and fill_levels.get(n, 0) >= THRESHOLD]

print(f"Nós que precisam de coleta (≥ {THRESHOLD}%): {[G.nodes[n]['name'] for n in must_visit]}")
print(f"Total: {len(must_visit)} lixeiras | Capacidade: {CAPACITY}/viagem\n")

trips = []
total_dist = 0
trip_lengths = []

for i in range(0, len(must_visit), CAPACITY):
    chunk = must_visit[i:i + CAPACITY]
    
    best_route = []
    min_dist = float('inf')

    for perm in itertools.permutations(chunk):
        current_route = [0] + list(perm) + [0]
        
        current_dist = sum(G[current_route[k]][current_route[k+1]]['weight'] for k in range(len(current_route)-1))
        
        if current_dist < min_dist:
            min_dist = current_dist
            best_route = current_route
            
    trips.append(best_route)
    trip_lengths.append(min_dist)
    total_dist += min_dist

ignored = [n for n in G.nodes if not G.nodes[n]["depot"] and n not in must_visit]

print("─" * 50)
for t, (trip, length) in enumerate(zip(trips, trip_lengths)):
    names = " → ".join(G.nodes[n]["name"].split()[0] if n != 0 else "Depósito" for n in trip)
    print(f"  Viagem {t+1}: {names}")
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
TRIP_COLORS = ["#185FA5", "#0F6E56", "#854F0B", "#A32D2D", "#533AB7", "#993556", "#3B6D11"]

fig = plt.figure(figsize=(16, 11), facecolor=PALETTE["bg"])
fig.suptitle("Otimização de Rota — Coleta de Lixeiras Hospitalares IoT\n"
             f"Threshold: {THRESHOLD}%  |  Capacidade: {CAPACITY} coletas/viagem  |  "
             f"Distância total ótima: {total_dist:.1f} m",
             fontsize=13, fontweight='bold', color=PALETTE["text"], y=0.98)

gs = GridSpec(2, 3, figure=fig, left=0.05, right=0.97, top=0.93, bottom=0.06, hspace=0.38, wspace=0.32)

ax_map = fig.add_subplot(gs[:, :2])
ax_map.set_facecolor(PALETTE["bg"])

pos = nx.get_node_attributes(G, 'pos')

nx.draw_networkx_edges(G, pos, ax=ax_map, edge_color=PALETTE["edge_bg"], width=0.6)

for t, trip in enumerate(trips):
    col = TRIP_COLORS[t % len(TRIP_COLORS)]
    route_edges = [(trip[k], trip[k+1]) for k in range(len(trip)-1)]
    nx.draw_networkx_edges(G, pos, edgelist=route_edges, ax=ax_map, edge_color=col, 
                           width=2.5, arrows=True, arrowsize=15, connectionstyle='arc3,rad=0.1')
    
    edge_labels = { (u, v): f"{G[u][v]['weight']:.0f}m" for u, v in route_edges }
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, ax=ax_map, 
                                 font_color=col, font_size=8, font_weight='bold')

node_colors = [PALETTE["depot"] if n == 0 else (PALETTE["route"] if n in must_visit else PALETTE["ignored"]) for n in G.nodes]
nx.draw_networkx_nodes(G, pos, ax=ax_map, node_color=node_colors, node_size=600, edgecolors="white", linewidths=1.5)

labels = {n: "D" if n == 0 else str(n) for n in G.nodes}
nx.draw_networkx_labels(G, pos, labels, ax=ax_map, font_color="white", font_size=10, font_weight="bold")

for n in G.nodes:
    x, y = pos[n]
    nome = G.nodes[n]["name"].replace("Enfermaria", "Enf.")
    ax_map.text(x, y + 5, nome, ha='center', fontsize=8, color=PALETTE["text"], 
                bbox=dict(boxstyle='round', fc=PALETTE["bg"], ec='none', alpha=0.8))
    
    if n != 0:
        pct = fill_levels[n]
        badge_col = PALETTE["route"] if pct >= THRESHOLD else PALETTE["ignored"]
        ax_map.text(x, y - 5, f"{pct}%", ha='center', va='top', fontsize=8, color=badge_col, fontweight='bold')

legend_handles = [
    mpatches.Patch(color=PALETTE["depot"],   label="Depósito"),
    mpatches.Patch(color=PALETTE["route"],   label=f"Lixeira na rota (≥{THRESHOLD}%)"),
    mpatches.Patch(color=PALETTE["ignored"], label=f"Ignorada (<{THRESHOLD}%)"),
]
for t in range(len(trips)):
    legend_handles.append(mpatches.Patch(color=TRIP_COLORS[t], label=f"Viagem {t+1}"))
ax_map.legend(handles=legend_handles, loc='lower right', fontsize=8, framealpha=0.9)
ax_map.axis('off')
ax_map.tick_params(left=True, bottom=True, labelleft=True, labelbottom=True)
ax_map.grid(True, color=PALETTE["grid"], lw=0.5, zorder=0)

ax_bar = fig.add_subplot(gs[0, 2])
ax_bar.set_facecolor(PALETTE["bg"])
ax_bar.set_title("Nível de Preenchimento", fontsize=10, fontweight='bold', color=PALETTE["text"])

ids   = [n for n in G.nodes if n != 0]
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

ax_trip = fig.add_subplot(gs[1, 2])
ax_trip.set_facecolor(PALETTE["bg"])
ax_trip.set_title("Distância por Viagem", fontsize=10, fontweight='bold', color=PALETTE["text"])

if trips:
    trip_labels = [f"V {i+1}" for i in range(len(trips))]
    trip_cols   = [TRIP_COLORS[i % len(TRIP_COLORS)] for i in range(len(trips))]
    bars2 = ax_trip.bar(trip_labels, trip_lengths, color=trip_cols, edgecolor='white', width=0.55)

    for bar, length in zip(bars2, trip_lengths):
        ax_trip.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 0.5,
                     f"{length:.1f}m", ha='center', va='bottom', fontsize=8.5, fontweight='bold', color=PALETTE["text"])

    ax_trip.axhline(y=total_dist/len(trips), color="#888780", lw=1.2, linestyle=':', label=f"Média: {total_dist/len(trips):.1f}m")
    ax_trip.set_ylim(0, max(trip_lengths) * 1.3)

ax_trip.set_ylabel("Distância (m)", fontsize=8, color=PALETTE["text"])
ax_trip.legend(fontsize=7.5, framealpha=0.9)
ax_trip.grid(axis='y', color=PALETTE["grid"], lw=0.5)

fig.text(0.05, 0.01,
         "Modelo: Grafos com NetworkX  |  Solver: Força Bruta (Solução Exata TSP)",
         fontsize=8, color="#888780", ha='left', va='bottom')

print("\nGerando gráfico na tela...")
plt.show()