import os
import networkx as nx                                                                       # type: ignore
import matplotlib.pyplot as plt                                                             # type: ignore

grafo1 = nx.Graph()
grafo2 = nx.Graph()

def main():
    while True:
        os.system('cls' if os.name == 'nt' else 'clear')
        print("1. Montar Novo Grafo")
        print("2. Gerar Grafo Aleatório")
        print("3. Demonstrar a Matriz de Adjacência dos Grafo")
        print("4. Demonstrar a Matriz de Incidencia dos Grafo")
        print("5. Demonstrar Conexões")
        print("6. Mostrar Grafo")
        print("7. Mostrar Classes do Grafo")
        print("8. Comparar Grafos")
        print("0. Sair")

        try:
            menu = int(input("\nSelecione uma opção: "))
        except ValueError:
            print("Opção inválida! Digite um número entre 1 e 5.")
            continue

        if menu == 1:
            while True:
                num = int(input("Digite o número do grafo que deseja montar (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            montar_grafo(num)
        elif menu == 2:
            while True:
                num = int(input("Digite o número do grafo que deseja demonstrar Ilustrado (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            GerarGrafo(num)    
        elif menu == 3:
            while True:
                num = int(input("Digite o número do grafo que deseja demonstrar a Matriz (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            matrizADJ_grafo(num)
        elif menu == 4:
            while True:
                num = int(input("Digite o número do grafo que deseja demonstrar a Matriz(1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            MatrizINC_grafo(num)
        elif menu == 5:
            while True:
                num = int(input("Digite o número do grafo que deseja Demonstrar as Conexoes (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            conexoes(num)
        elif menu == 6:
            while True:
                num = int(input("Digite o número do grafo que deseja demonstrar Ilustrado (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            mostrar_grafo(num)
        elif menu == 7:
            while True:
                num = int(input("Digite o número do grafo que deseja demonstrar as Classes(1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            verificar_tipo_grafo(num)
            input("\nPressione Enter para continuar...")
        elif menu == 8:
            CompararGrafos()
        elif menu == 0:
            os.system('cls' if os.name == 'nt' else 'clear')
            break
        else:
            print("Opção inválida!")
        os.system('cls' if os.name == 'nt' else 'clear')    
def montar_grafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
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
    
    if num == 1:
        grafo1 = grafo
    elif num == 2:
        grafo2 = grafo
    
    print("\nGrafo montado com sucesso")
    input("Pressione Enter para continuar...")
def matrizADJ_grafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
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
def MatrizINC_grafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2

    vertices = list(grafo.nodes)
    edges = list(grafo.edges)
    
    matriz = [[0] * len(edges) for _ in range(len(vertices))]
    
    for edge_idx, (v1, v2) in enumerate(edges):
        matriz[v1][edge_idx] = 1
        matriz[v2][edge_idx] = 1
    
    print("Matriz de Incidência do Grafo:")
    header = "     "
    for i in range(len(edges)):
        header += f"E{i}   "
    print(header)
    for i, linha in enumerate(matriz):
        row = f"V{i}   "
        for val in linha:
            row += f"{val}    "
        print(row)


    input("Pressione Enter para continuar...")
def conexoes(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
    print("Conexões de todos os vértices:")
    for vertice in grafo.nodes:
        print(f"{vertice}: {list(grafo.neighbors(vertice))}")
    input("Pressione Enter para continuar...")
def mostrar_grafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
    plt.figure(figsize=(6, 6))
    nx.draw(grafo, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
    plt.ion()  # Turn on interactive mode
    plt.show(block=False)  # Show plot without blocking
def CompararGrafos():
    global grafo1, grafo2
    print("Comparando Grafos...")
    iso = isomorfo()
    if iso == 1:
        print("Os grafos são isomórficos")
    else:
        print("Os grafos não são isomórficos")
    #isso roda a função de montar a matriz do grafo para ambos para caso queira comparar    
    vertices1 = list(grafo1.nodes)
    matriz1 = [[0] * len(vertices1) for _ in range(len(vertices1))]
    for i, v1 in enumerate(vertices1):
        for j, v2 in enumerate(vertices1):
            if grafo1.has_edge(v1, v2):
                matriz1[i][j] = 1

    vertices2 = list(grafo2.nodes)
    matriz2 = [[0] * len(vertices2) for _ in range(len(vertices2))]
    for i, v1 in enumerate(vertices2):
        for j, v2 in enumerate(vertices2):
            if grafo2.has_edge(v1, v2):
                matriz2[i][j] = 1

    print("Matriz do Grafo 1:".ljust(20) + "Matriz do Grafo 2:")
    for linha1, linha2 in zip(matriz1, matriz2):
        print(" ".join(map(str, linha1)).ljust(20) + " ".join(map(str, linha2)))


    verificar_tipo_grafo(1)
    verificar_tipo_grafo(2)

    mostrar_grafo(1)
    mostrar_grafo(2)
    
    input("Pressione Enter para continuar...")
    def isomorfo():
        global grafo1, grafo2
        if len(grafo1.nodes) == len(grafo2.nodes) and len(grafo1.edges) == len(grafo2.edges):
            graus_grafo1 = sorted([grafo1.degree(n) for n in grafo1.nodes])
            graus_grafo2 = sorted([grafo2.degree(n) for n in grafo2.nodes])
            if graus_grafo1 != graus_grafo2:
                print("Os grafos não são isomórficos (graus diferentes)")
                return 0
            from itertools import permutations
            for perm in permutations(grafo2.nodes):
                mapping = {u: v for u, v in zip(grafo1.nodes, perm)}
                if verificar_correspondencia(mapping):
                    return 1
        return 0
    def verificar_correspondencia(mapping):
        global grafo1, grafo2
        for u in grafo1.nodes:
            for v in grafo1.nodes:
                print(f"Verificando aresta {u} - {v} com mapeamento {mapping[u]} - {mapping[v]}")
                if grafo1.has_edge(u, v) != grafo2.has_edge(mapping[u], mapping[v]):
                    return False
        return True
    
def GerarGrafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
    grafo.clear()
    
    print("\nEscolha o tipo de grafo:")
    print("1. Aleatório")
    print("2. Ciclo")
    print("3. Roda")
    print("4. Completo")
    print("5. Euleriano")
    
    tipo = int(input("Digite o tipo desejado: "))
    n = int(input("Digite o número de vértices (mínimo 3): "))
    
    if tipo == 1:
        grafo = n.erdoxs_renyi_graph(n, 0.5)
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
    
    if num == 1:
        grafo1 = grafo
    else:
        grafo2 = grafo
        
    print(f"\nGrafo {num} gerado com sucesso")
    input("Pressione Enter para continuar...")
def verificar_tipo_grafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
    
    n_vertices = len(grafo.nodes)
    n_arestas = len(grafo.edges)
    
    # Verifica se é um ciclo
    is_ciclo = all(grafo.degree(node) == 2 for node in grafo.nodes) and n_vertices == n_arestas
    
    # Verifica se é uma roda
    centro = None
    for node in grafo.nodes:
        if grafo.degree(node) == n_vertices - 1:
            centro = node
            break
    vertices_ciclo = set(grafo.nodes) - {centro} if centro is not None else set()
    is_roda = (centro is not None and 
              all(grafo.degree(v) == 3 for v in vertices_ciclo) and 
              n_vertices >= 4)
    
    # Verifica se é completo
    n_arestas_completo = (n_vertices * (n_vertices - 1)) // 2
    is_completo = n_arestas == n_arestas_completo
    
    # Verifica se é euleriano
    is_euleriano = all(grafo.degree(node) % 2 == 0 for node in grafo.nodes) and nx.is_connected(grafo)
    
    print(f"\nCaracterísticas do grafo{num}: ")
    print(f"É um ciclo: {is_ciclo}")
    print(f"É uma roda: {is_roda}")
    print(f"É completo: {is_completo}")
    print(f"É euleriano: {is_euleriano}")
    
if __name__ == "__main__":
    main()