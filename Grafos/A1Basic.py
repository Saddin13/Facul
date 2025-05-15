import os
import networkx as nx                                                                       # type: ignore
import matplotlib.pyplot as plt                                                             # type: ignore

grafo = nx.Graph()

def main():
    while True:
        print("1. Montar Grafo")
        print("2. Demonstrar a Matriz do Grafo")
        print("3. Demonstrar Conexões")
        print("4. Mostrar Grafo")
        print("5. Sair")

        try:
            menu = int(input("\nSelecione uma opção: "))
        except ValueError:
            print("Opção inválida! Digite um número entre 1 e 5.")
            continue

        if menu == 1:
            os.system('cls' if os.name == 'nt' else 'clear')
            montar_grafo()    
        elif menu == 2:
            matriz_grafo()
        elif menu == 3:
            conexoes()
        elif menu == 4:
            mostrar_grafo()
        elif menu == 5:
            os.system('cls' if os.name == 'nt' else 'clear')
            break
        else:
            print("Opção inválida!")
            os.system('cls' if os.name == 'nt' else 'clear')
            

def montar_grafo():
    global grafo
    grafo.clear()
    vertices = int(input("Digite o número de vértices: "))
    matriz = [[0] * vertices for _ in range(vertices)]
    
    # Preenche a parte triangular superior da matriz
    for i in range(vertices-1):  # vai até o penúltimo vértice
        valores = input(f"Digite os valores para o vértice {i} (conexões com vértices {i+1} até {vertices-1}, separados por espaço): ").split()
        for j, valor in enumerate(valores):
            atual_j = j + i + 1  # ajusta o índice j para começar depois de i
            matriz[i][atual_j] = int(valor)
            matriz[atual_j][i] = int(valor)  # Espelha o valor (grafo não direcionado)

    # Adiciona as arestas ao grafo
    for i in range(vertices):
        for j in range(vertices):
            if matriz[i][j] == 1:
                grafo.add_edge(i, j)

    print("\nMatriz resultante:")
    for linha in matriz:
        print(" ".join(map(str, linha)))
    
    print("\nGrafo montado com sucesso")
    input("Pressione Enter para continuar...")

def matriz_grafo():
    global grafo
    vertices = list(grafo.nodes)
    matriz = [[0] * len(vertices) for _ in range(len(vertices))]

    for i, v1 in enumerate(vertices):
        for j, v2 in enumerate(vertices):
            if grafo.has_edge(v1, v2):
                matriz[i][j] = 1

    print("Matriz do Grafo:")
    for linha in matriz:
        print(" ".join(map(str, linha)))

    input("Pressione Enter para continuar...")

def conexoes():
    global grafo
    print("Conexões de todos os vértices:")
    for vertice in grafo.nodes:
        print(f"{vertice}: {list(grafo.neighbors(vertice))}")
    input("Pressione Enter para continuar...")

def mostrar_grafo():
    global grafo
    plt.figure(figsize=(6, 6))
    nx.draw(grafo, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
    plt.show()

if __name__ == "__main__":
    main()