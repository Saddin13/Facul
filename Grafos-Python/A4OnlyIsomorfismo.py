import os
import networkx as nx                                                                     # type: ignore
import matplotlib.pyplot as plt                                                           # type: ignore

grafo1 = nx.Graph()
grafo2 = nx.Graph()

def main():
    while True:
        os.system('cls' if os.name == 'nt' else 'clear')
        print("1. Montar Novo Grafo")
        print("2. Gerar Grafo Aleatório")
        print("3. Mostrar Grafo")
        print("4. Comparar Grafos (Isomorfismo)")
        print("0. Sair")

        try:
            menu = int(input("\nSelecione uma opção: "))
        except ValueError:
            print("Opção inválida! Digite um número válido.")
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
                num = int(input("Digite o número do grafo que deseja gerar (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            GerarGrafo(num)    
        elif menu == 3:
            while True:
                num = int(input("Digite o número do grafo que deseja demonstrar (1 ou 2): "))
                if num in (1, 2):
                    break
                print("Número inválido! Digite 1 ou 2.")
            os.system('cls' if os.name == 'nt' else 'clear')
            mostrar_grafo(num)
            input("\nPressione Enter para continuar...")
        elif menu == 4:
            os.system('cls' if os.name == 'nt' else 'clear')
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

def mostrar_grafo(num):
    global grafo1, grafo2
    grafo = grafo1 if num == 1 else grafo2
    plt.figure(figsize=(6, 6))
    nx.draw(grafo, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
    plt.ion()
    plt.show(block=False)  

def CompararGrafos():
    global grafo1, grafo2
    print("Comparando Grafos...")
    
    if len(grafo1.nodes) != len(grafo2.nodes):
        print("Não são isomórficos: número de vértices diferentes")
        print(f"Grafo 1: {len(grafo1.nodes)} vértices | Grafo 2: {len(grafo2.nodes)} vértices")
        input("Pressione Enter para continuar...")
        return
    
    if len(grafo1.edges) != len(grafo2.edges):
        print("Não são isomórficos: número de arestas diferentes")
        print(f"Grafo 1: {len(grafo1.edges)} arestas | Grafo 2: {len(grafo2.edges)} arestas")
        input("Pressione Enter para continuar...")
        return
    
    graus1 = sorted([grafo1.degree(n) for n in grafo1.nodes])
    graus2 = sorted([grafo2.degree(n) for n in grafo2.nodes])
    
    if graus1 != graus2:
        print("Não são isomórficos: sequência de graus diferentes")
        print(f"Grafo 1: {graus1} | Grafo 2: {graus2}")
        input("Pressione Enter para continuar...")
        return
    
    comp1 = list(nx.connected_components(grafo1))
    comp2 = list(nx.connected_components(grafo2))
    
    if len(comp1) != len(comp2):
        print("Não são isomórficos: número de componentes conectados diferentes")
        print(f"Grafo 1: {len(comp1)} componentes | Grafo 2: {len(comp2)} componentes")
        input("Pressione Enter para continuar...")
        return
    
    tamanhos1 = sorted([len(c) for c in comp1])
    tamanhos2 = sorted([len(c) for c in comp2])
    
    if tamanhos1 != tamanhos2:
        print("Não são isomórficos: tamanhos dos componentes conectados diferentes")
        print(f"Grafo 1: {tamanhos1} | Grafo 2: {tamanhos2}")
        input("Pressione Enter para continuar...")
        return
    
    ciclos1 = sorted([len(c) for c in nx.cycle_basis(grafo1)])
    ciclos2 = sorted([len(c) for c in nx.cycle_basis(grafo2)])
    
    if ciclos1 != ciclos2:
        print("Não são isomórficos: estrutura de ciclos diferentes")
        print(f"Grafo 1: {ciclos1} | Grafo 2: {ciclos2}")
        input("Pressione Enter para continuar...")
        return
    
    if nx.is_isomorphic(grafo1, grafo2):
        print("Os grafos são isomórficos!")
        isomorfismo = nx.isomorphism.GraphMatcher(grafo1, grafo2)
        if isomorfismo.is_isomorphic():
            mapeamento = isomorfismo.mapping
            print("\nMapeamento bijetor entre os vértices:")
            for v1, v2 in mapeamento.items():
                print(f"Vértice {v1} do Grafo 1 → Vértice {v2} do Grafo 2")
    else:
        print("Não são isomórficos: falha na verificação final de isomorfismo")
        print("Os grafos passaram por todas as verificações básicas, mas o algoritmo de isomorfismo detectou diferenças estruturais mais complexas.")
    
    plt.figure(figsize=(12, 6))
    
    plt.subplot(121)
    plt.title("Grafo 1")
    nx.draw(grafo1, with_labels=True, node_color='lightblue', edge_color='gray', node_size=800, font_size=10)
    
    plt.subplot(122)
    plt.title("Grafo 2")
    nx.draw(grafo2, with_labels=True, node_color='lightgreen', edge_color='gray', node_size=800, font_size=10)
    
    plt.tight_layout()
    plt.ion()
    plt.show(block=False)
    
    input("Pressione Enter para continuar...")

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
        grafo = nx.erdos_renyi_graph(n, 0.5)
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

if __name__ == "__main__":
    main()
