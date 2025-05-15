import os
import math
import networkx as nx                                                                     # type: ignore
import matplotlib.pyplot as plt                                                           # type: ignore

grafo = nx.Graph()

def main():
    global grafo
    grafo = nx.Graph()
    while True:
        os.system('cls' if os.name == 'nt' else 'clear')
        print("Sistema de Busca de Vertices")
        print("1. Montar Grafo")
        print("2. Gerar Grafo")
        print("3. Mostrar Grafo")
        print("4. Busca Por Profundidade")
        print("5. Busca Por Largura")
        print("6. Sair")
        opcao = input("Escolha uma opção: ")
        if opcao == '1':
            montar_grafo()
        elif opcao == '2':
            GerarGrafo()
        elif opcao == '3':
            mostrar_grafo()
        elif opcao == '4':
            Busca_Profundidade()
        elif opcao == '5':
            Busca_Largura()
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
    print("foi gerado a arvore balanceada maximizada de tamanho menor possivel sendo maior que a quantidade de vertices")
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')

def mostrar_grafo():
    global grafo
    plt.figure(figsize=(6, 6))
    nx.draw(grafo, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
    plt.ion()  # Turn on interactive mode
    plt.show(block=False)  # Show plot without 
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')
def Busca_Profundidade():
    global grafo
    if not grafo:
        print("Grafo vazio. Por favor, monte ou gere um grafo primeiro.")
        input("Pressione Enter para continuar...")
        return    

    print("Busca em Profundidade")
    print("Vértices disponíveis:", end=" ")
    print(", ".join(map(str, grafo.nodes())))

    vertice_inicial = int(input("Digite o vértice inicial: "))
    vertice_final = int(input("Digite o vértice final: "))

    if vertice_inicial not in grafo.nodes() or vertice_final not in grafo.nodes():
        print("Vértice(s) inválido(s).")
        input("Pressione Enter para continuar...")
        return

    visitados = set()
    pilha = [(vertice_inicial, [vertice_inicial])]
    etapa = 1

    while pilha:
        vertice, caminho_atual = pilha.pop()
        
        print(f"Caminho atual: {' -> '.join(map(str, caminho_atual))}")
        
        if vertice == vertice_final:
            print("\nDestino encontrado!")
            print(f"Caminho final: {' -> '.join(map(str, caminho_atual))}")
            input("Pressione Enter para continuar...")
            os.system('cls' if os.name == 'nt' else 'clear')
            return

        if vertice not in visitados:
            visitados.add(vertice)
            for vizinho in reversed(list(grafo.neighbors(vertice))):
                if vizinho not in visitados:
                    novo_caminho = caminho_atual + [vizinho]
                    pilha.append((vizinho, novo_caminho))
        
        etapa += 1

    print("\nCaminho não encontrado.")
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')
def Busca_Largura():
    global grafo
    if not grafo:
        print("Grafo vazio. Por favor, monte ou gere um grafo primeiro.")
        input("Pressione Enter para continuar...")
        return
    
    print("Busca em Largura")
    print("Vértices disponíveis:", end=" ")
    print(", ".join(map(str, grafo.nodes())))
    
    vertice_inicial = int(input("Digite o vértice inicial: "))
    vertice_final = int(input("Digite o vértice final: \n"))
    
    if vertice_inicial not in grafo.nodes() or vertice_final not in grafo.nodes():
        print("Vértice(s) inválido(s).")
        input("Pressione Enter para continuar...")
        return

    visitados = {vertice_inicial}
    fila = [(vertice_inicial, [vertice_inicial])]
    etapa = 1
    
    while fila:
        vertice, caminho_atual = fila.pop(0)
        print(f"{' -> '.join(map(str, caminho_atual))}")
        
        if vertice == vertice_final:
            print("\nDestino encontrado!")
            print(f"Caminho final: {' -> '.join(map(str, caminho_atual))}")
            input("Pressione Enter para continuar...")
            os.system('cls' if os.name == 'nt' else 'clear')
            return
            
        for vizinho in grafo.neighbors(vertice):
            if vizinho not in visitados:
                visitados.add(vizinho)
                novo_caminho = caminho_atual + [vizinho]
                fila.append((vizinho, novo_caminho))
        etapa += 1
    
    print("\nCaminho não encontrado.")
    input("Pressione Enter para continuar...")
    os.system('cls' if os.name == 'nt' else 'clear')
if __name__ == "__main__":
    main()