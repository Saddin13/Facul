import os
import networkx as nx                                                                       
import matplotlib.pyplot as plt                                                             
import numpy as np
import random

grafo = nx.Graph()

def posicoes_personalizadas():
    pos = {}
    # Heptágono (7 nós centrais)
    centro = (0, 0)
    raio_hepta = 1.0
    for i in range(7):
        ang = 2 * np.pi * i / 7
        pos[i] = (centro[0] + raio_hepta * np.cos(ang), centro[1] + raio_hepta * np.sin(ang))
    # Quadrado externo (nós 7,8,9,10)
    raio_ext = 2.0
    for i, idx in enumerate(range(7, 11)):
        ang = np.pi/4 + np.pi/2 * i
        pos[idx] = (centro[0] + raio_ext * np.cos(ang), centro[1] + raio_ext * np.sin(ang))
    # Quadrado interno (nós 11,12,13,14)
    raio_int = 0.5
    for i, idx in enumerate(range(11, 15)):
        ang = np.pi/4 + np.pi/2 * i
        pos[idx] = (centro[0] + raio_int * np.cos(ang), centro[1] + raio_int * np.sin(ang))
    return pos
def mostrar_grafo(casaAtual, pos):
    # Limpa e configura a figura
    plt.clf()
    fig = plt.gcf()
    fig.set_size_inches(12, 9)

    # Cria subgrafo com casaAtual e seus vizinhos
    subgrafo = grafo.subgraph([casaAtual] + list(grafo.neighbors(casaAtual)))
    sub_pos = {n: pos[n] for n in subgrafo.nodes}
    labels = nx.get_node_attributes(grafo, 'valor')

    # Define cores e tamanhos dos nós
    node_colors = []
    node_sizes = []
    for n in subgrafo.nodes:
        if n == casaAtual:
            node_colors.append('orange')
            node_sizes.append(1000)
        else:
            node_colors.append('lightblue')
            node_sizes.append(700)

    # Desenha o subgrafo
    nx.draw(
        subgrafo, sub_pos, with_labels=True,
        node_color=node_colors, edge_color='gray', node_size=node_sizes
    )

    # Mostra o valor acima de cada nó
    for k in subgrafo.nodes:
        x, y = sub_pos[k]
        v = labels.get(k, "")
        plt.text(
            x, y + 0.03, str(v),
            fontsize=10, color='red', ha='center', va='bottom', fontweight='bold'
        )

    plt.title(f'Casa Atual: {casaAtual} e seus vizinhos')

    # Função para detectar clique em vizinho
    clicked_node = {'node': None}
    def on_click(event):
        if event.inaxes:
            for node, (x, y) in sub_pos.items():
                dx = event.xdata - x
                dy = event.ydata - y
                if dx*dx + dy*dy < 0.03:
                    if node != casaAtual:
                        print(f"Você clicou no vizinho {node} com valor {labels.get(node)}")
                        clicked_node['node'] = node
                        plt.close()
                    break

    # Conecta evento de clique e exibe a janela
    cid = plt.gcf().canvas.mpl_connect('button_press_event', on_click)
    plt.show()
    plt.gcf().canvas.mpl_disconnect(cid)

    # Retorna o valor do nó clicado, se houver
    if clicked_node['node'] is not None:
        return labels.get(clicked_node['node'])
    return None
def gerarValoresCasas():
    global grafo
    distancias = nx.single_source_shortest_path_length(grafo, 0)
    valores = {}
    while True:
        for node in grafo.nodes:
            distancia = distancias[node]
            if distancia == 0 or node == 0:
                valor = 5  # Agora o nó 0 sempre recebe 5
                valores[node] = valor
            elif node < 14:
                minimo = (distancia * 10) - 10
                if minimo == 0:
                    minimo = 3
                maximo = distancia * 10
                valor = random.randint(minimo, maximo)
                valores[node] = valor
            elif node == 14:
                valores[node] = 100
        # Garante que pelo menos um vizinho de 0 tenha valor < 4
        vizinhos_0 = list(grafo.neighbors(0))
        vizinhos_validos = [v for v in vizinhos_0 if v != 14]
        if vizinhos_validos:
            vizinho_especial = vizinhos_validos[0]
        else:
            vizinho_especial = None
        valores[vizinho_especial] = 3
        soma = sum(val for node, val in valores.items() if node != 14)
        if soma > 100:
            print("Debug: Valores gerados:", valores)
            print("Debug: Soma dos valores (sem o nó 14):", soma)
            break
        print("Debug: Valores gerados:", valores)
        print("Debug: Soma dos valores (sem o nó 14):", soma)

    nx.set_node_attributes(grafo, valores, 'valor')
def main():
    global grafo
    grafo = nx.Graph()
    while True:
        grafo = nx.erdos_renyi_graph(15, 0.6)
        if all(grafo.degree(node) % 2 == 0 for node in grafo.nodes) and nx.is_connected(grafo):
            break
    CasaAtual = 0
    gerarValoresCasas()
    print("Jogo de Anuncio Fake")
    #posicoes = nx.spring_layout(grafo, seed=42, k=1.2) 
    posicoes = posicoes_personalizadas()
    while True:
        volta = mostrar_grafo(CasaAtual, posicoes)
        if volta is None:
            print("Selecione um vizinho para continuar.")
            continue
        valor_atual = grafo.nodes[CasaAtual]['valor']
        # Verifica se todos os vizinhos têm valor maior ou igual ao valor atual
        vizinhos = list(grafo.neighbors(CasaAtual))
        valores_vizinhos = [grafo.nodes[v]['valor'] for v in vizinhos]
        if all(valor_atual >= v for v in valores_vizinhos):
            print("Você perdeu! Todos os vizinhos têm valor maior ou igual ao da casa atual.")
            break
        if volta > valor_atual:
            print("Interação inválida! O valor do vizinho é maior que o valor da casa atual. Tente novamente.")
            continue
        else:
            # Encontra o novo nó pelo valor retornado
            novo_no = [n for n, v in grafo.nodes(data='valor') if v == volta][0]
            # Soma os valores
            novo_valor = grafo.nodes[novo_no]['valor'] + valor_atual
            # Atualiza as conexões: conecta 0 aos vizinhos do novo_no (exceto o antigo 0)
            vizinhos_novo = list(grafo.neighbors(novo_no))
            grafo.remove_node(CasaAtual)  # Remove o antigo nó 0
            grafo = nx.relabel_nodes(grafo, {novo_no: 0})  # Renomeia o novo nó para 0
            # Atualiza o valor do nó 0
            grafo.nodes[0]['valor'] = novo_valor
            CasaAtual = 0
            # Verifica se o nó 14 ainda existe no grafo
            if 14 not in grafo.nodes:
                print("O nó 14 foi removido. Fim de jogo!")
                break
    plt.close('all') 
    print("Você chegou ao fim do jogo! Parabéns!")
    print(f"Valor da casa atual antes de remover o nó 14: {grafo.nodes[0]['valor']}")
    print(f"Parabens Voce matou o BOSS com: {grafo.nodes[0]['valor'] - 100}")
if __name__ == "__main__":
    main()