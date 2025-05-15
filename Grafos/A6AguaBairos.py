import os
import networkx as nx                                                                       # type: ignore
import matplotlib.pyplot as plt                                                             # type: ignore

grafo = nx.Graph()

def mostrar_grafo():
    global grafo
    plt.figure(figsize=(6, 6))
    nx.draw(grafo, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
    plt.show()
def montar_grafo():
    global grafo
    grafo.clear()
    vertices = 6
    matriz = [[0] * vertices for _ in range(vertices)]
    for i in range(vertices-1):
        valores = input(f"Digite os valores para o Bairro {i} (conexões com Bairros {i+1} até {vertices-1}, separados por espaço): ").split()
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
    
    print("\nGrafo montado com sucesso")
    input("Pressione Enter para continuar...")
def verificar_grafo():
    global grafo
    todos_com_duas_arestas = all(degree >= 2 for _, degree in grafo.degree())
    if todos_com_duas_arestas:
        if nx.is_planar(grafo):
            print("O Sistema passado é funcional.")
        else:
            print("O Sistema passado não é funcional devido ao cruzamento de linhas.")
    else:
        print("O Sistema passado nao é funcional, Pois tem bairros conectados por menos de 2 tubulaçoes.")
    input("Pressione Enter para continuar...")

def main():
    global grafo
    grafo = nx.Graph()
    while True:
        os.system('cls' if os.name == 'nt' else 'clear')
        print("Sistema de Gerenciamento de Bairros")
        print("1. Montar Sistema")
        print("2. Gerar Sistema ")
        print("3. Mostrar Sistema")
        print("4. Verificar se é Possivel")
        print("5. Sair")
        opcao = input("Escolha uma opção: ")    
        if opcao == '1':
            montar_grafo()
        elif opcao == '2':
            grafo = nx.erdos_renyi_graph(6, 0.5)
        elif opcao == '3':
            mostrar_grafo()
        elif opcao == '4':
            verificar_grafo()
        elif opcao == '5':
            break
        else:
            print("Opção inválida. Tente novamente.")

if __name__ == "__main__":
    main()                                                                                                                        # type: ignore