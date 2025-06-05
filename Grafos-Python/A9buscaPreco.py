import os
import math
import random
import networkx as nx                                                                     # type: ignore
import matplotlib.pyplot as plt                                                           # type: ignore

grafo = nx.Graph()

def main():
    global grafo
    grafo = nx.Graph()
    valorado = 0
    while True:
        os.system('cls' if os.name == 'nt' else 'clear')
        print("Sistema de Busca de Vertices")
        print("1. Montar Grafo")
        print("2. Gerar Grafo")
        print("3. Gerar valores para as arestas")
        print("4. Mostrar Grafo")
        print("5. Busca Por Preco")
        print("6. Sair")
        opcao = input("Escolha uma opção: ")
        if opcao == '1':
            montar_grafo()
        elif opcao == '2':
            GerarGrafo()
            valorado = 1
        elif opcao == '3':
            valorado = gerarvaloresArestas()
        elif opcao == '4':
            mostrar_grafo()
        elif opcao == '5':
            BuscaPorPreco(valorado)
        elif opcao == '6':
            break

def montar_grafo():
    global grafo
    grafo.clear()
    vertices = int(input("Digite o número de vértices: "))
    matriz = [[0] * vertices for _ in range(vertices)]
    
    for i in range(vertices-1):
        valores = input(f"Digite os valores para o vértice {i} (conexões com vértices {i+1} até {vertices-1}, separados por espaço): ").split()
        for j, valor in enumerate(valores):
            atual_j = j + i + 1 
            matriz[i][atual_j] = int(valor)
            matriz[atual_j][i] = int(valor) 

    for i in range(vertices):
        for j in range(vertices):
            if matriz[i][j] == 1:
                grafo.add_edge(i, j)

    print("\nMatriz resultante:")
    for linha in matriz:
        print(" ".join(map(str, linha)))
    print("Grafo montado com sucesso!")
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')

def GerarGrafo():
    global grafo
    grafo.clear()
    os.system('cls' if os.name == 'nt' else 'clear')
    print("Sistema de Geração de Grafos")
    print("Escolha o tipo de grafo:")
    print("1. Aleatório")
    print("2. Ciclo")
    print("3. Roda")
    print("4. Completo")
    print("5. Euleriano")
    print("6. Arvores")
    
    tipo = int(input("Digite o tipo desejado: "))
    n = int(input("Digite o número de vértices (mínimo 3): "))
    
    if tipo == 1:
        grafo = nx.erdos_renyi_graph(n, 0.3) 
    elif tipo == 2:
        grafo = nx.cycle_graph(n)
    elif tipo == 3:
        grafo = nx.wheel_graph(n)
    elif tipo == 4:
        grafo = nx.complete_graph(n)
    elif tipo == 5:
        while True:
            grafo = nx.erdos_renyi_graph(n, 0.5)
            if all(grafo.degree(node) % 2 == 0 for node in grafo.nodes) and nx.is_connected(grafo):
                break
    elif tipo == 6:
        ramificacoes = int(input("Quantas ramificações deseja? "))
        altura = math.floor(math.log(n, ramificacoes))
        grafo = nx.balanced_tree(ramificacoes, altura)
    gerarvaloresArestas()
    mostrar_grafo()
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')

def mostrar_grafo():
    global grafo
    plt.figure(figsize=(6, 6))
    pos = nx.spring_layout(grafo)  # Calculate layout once and reuse it
    
    # Draw the graph
    nx.draw(grafo, pos, with_labels=True, node_color='lightblue', 
           edge_color='gray', node_size=1000, font_size=12)
    
    # Draw edge labels (weights)
    edge_labels = nx.get_edge_attributes(grafo, 'weight')
    nx.draw_networkx_edge_labels(grafo, pos, edge_labels=edge_labels)
    
    plt.ion()  # Turn on interactive mode
    plt.show(block=False)  # Show plot without blocking
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')

def gerarvaloresArestas():
    global grafo
    os.system('cls' if os.name == 'nt' else 'clear')
    if not grafo:
        print("Grafo vazio. Por favor, monte ou gere um grafo primeiro.")
        input("Pressione Enter para continuar...")
        return
    print("Sistema de Geração de Valores para Arestas")
    print("Escolha uma opção:")
    print("1. Arestas com pesos definidos pelo usuário")
    print("2. Arestas com pesos aleatórios entre 1 e 10")
    opcao = input("Escolha uma opção: ")
    while True:
        if opcao == '1':
            for u, v in grafo.edges():
                valor = int(input(f"Digite o valor para a aresta ({u}, {v}): "))
                grafo[u][v]['weight'] = valor
            print("Valores das arestas atualizados com sucesso!")
            input("Pressione Enter para continuar...")
            return 1
        elif opcao == '2':
            for u, v in grafo.edges():
                valor = random.randint(1, 10)
                grafo[u][v]['weight'] = valor
            print("Valores das arestas atualizados com sucesso!")
            input("Pressione Enter para continuar...")
            return 1
        else:
            print("Opção inválida. Por favor, escolha 1 ou 2.")
            input("Pressione Enter para continuar...")
def BuscaPorPreco(valorado):
    global grafo
    if not grafo or valorado != 1:
        print("Grafo vazio. Por favor, monte ou gere um grafo primeiro ou voce ainda nao gerou valores para as arestas.")
        input("Pressione Enter para continuar...")
        return
    os.system('cls' if os.name == 'nt' else 'clear')
    print("Sistema de Busca por Preço")
    origem = int(input("Digite o vértice de origem: "))
    if origem not in grafo.nodes():
        print("Vértice de origem inválido.")
        input("Pressione Enter para continuar...")
        return

    try:
        # Gerar todas as permutações possíveis dos vértices (exceto origem)
        outros_vertices = list(set(grafo.nodes()) - {origem})
        melhor_caminho = None
        menor_peso = float('inf')

        # Testar todas as permutações possíveis
        from itertools import permutations
        for perm in permutations(outros_vertices):
            caminho_atual = [origem] + list(perm) + [origem]
            peso_atual = 0
            valido = True

            # Calcular peso do caminho atual
            for i in range(len(caminho_atual) - 1):
                if not grafo.has_edge(caminho_atual[i], caminho_atual[i+1]):
                    valido = False
                    break
                peso_atual += grafo[caminho_atual[i]][caminho_atual[i+1]]['weight']

            if valido and peso_atual < menor_peso:
                menor_peso = peso_atual
                melhor_caminho = caminho_atual

        if melhor_caminho:
            # Mostrar o resultado visualmente
            plt.figure(figsize=(6, 6))
            pos = nx.spring_layout(grafo)
            
            # Desenhar o grafo completo
            nx.draw(grafo, pos, with_labels=True, node_color='lightblue', 
                   edge_color='gray', node_size=1000, font_size=12)
            
            # Desenhar o caminho encontrado em vermelho
            path_edges = list(zip(melhor_caminho[:-1], melhor_caminho[1:]))
            nx.draw_networkx_edges(grafo, pos, edgelist=path_edges, 
                                 edge_color='red', width=2)
            
            # Mostrar os pesos das arestas
            edge_labels = nx.get_edge_attributes(grafo, 'weight')
            nx.draw_networkx_edge_labels(grafo, pos, edge_labels=edge_labels)
            
            plt.title("Caminho de Menor Peso Total")
            plt.show()
            
            print("\nMelhor caminho encontrado:")
            print(" -> ".join(map(str, melhor_caminho)))
            print(f"Peso total do caminho: {menor_peso}")
        else:
            print("Não foi possível encontrar um caminho válido que passe por todos os vértices.")
            
    except Exception as e:
        print(f"Erro ao buscar caminho: {e}")

    input("\nPressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')

if __name__ == "__main__":
    main()