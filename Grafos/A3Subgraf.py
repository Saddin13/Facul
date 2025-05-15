import os
import networkx as nx                                                               # type: ignore
import matplotlib.pyplot as plt                                                     # type: ignore

grafo = nx.Graph()
subgrafo = nx.Graph()

def main():
    while True:
        try:
            os.system('cls' if os.name == 'nt' else 'clear')
            print("1. Montar Novo Grafo")
            print("2. Gerar Grafo Aleatório")
            print("3. Entrada do Subgrafo")
            print("4. Gerar Subgrafo Aleatório")
            print("5. Mostrar Grafo e Subgrafo")
            print("6. Verificar Classe do Subgrafo")
            print("7. Calcular todas as possibilidades de subgrafos")
            print("0. Sair")

            menu = int(input("\nSelecione uma opção: "))
            
            if menu == 1:
                os.system('cls' if os.name == 'nt' else 'clear')
                montar_grafo()
                input("\nPressione Enter para continuar...")
            elif menu == 2:
                os.system('cls' if os.name == 'nt' else 'clear')
                gerar_grafo_aleatorio()
            elif menu == 3:
                if len(grafo.nodes()) == 0:
                    raise ValueError("Grafo principal está vazio. Crie um grafo primeiro.")
                os.system('cls' if os.name == 'nt' else 'clear')
                entrada_subgrafo()
                input("\nPressione Enter para continuar...")
            elif menu == 4:
                if len(grafo.nodes()) == 0:
                    raise ValueError("Grafo principal está vazio. Crie um grafo primeiro.")
                os.system('cls' if os.name == 'nt' else 'clear')
                gerar_subgrafo_aleatorio()
            elif menu == 5:
                if len(grafo.nodes()) == 0:
                    raise ValueError("Grafo principal está vazio. Crie um grafo primeiro.")
                os.system('cls' if os.name == 'nt' else 'clear')
                mostrar_grafos()
            elif menu == 6:
                if len(subgrafo.nodes()) == 0:
                    raise ValueError("Subgrafo está vazio. Crie um subgrafo primeiro.")
                os.system('cls' if os.name == 'nt' else 'clear')
                verificar_classe_subgrafo()
            elif menu == 7:
                if len(grafo.nodes()) == 0:
                    raise ValueError("Grafo principal está vazio. Crie um grafo primeiro.")
                os.system('cls' if os.name == 'nt' else 'clear')
                calcular_subgrafos_possiveis()
            elif menu == 0:
                os.system('cls' if os.name == 'nt' else 'clear')
                break
            else:
                print("Opção inválida!")
            
        except ValueError as e:
            print(f"Erro: {str(e)}")
        except Exception as e:
            print(f"Erro inesperado: {str(e)}")
        
        os.system('cls' if os.name == 'nt' else 'clear')

def montar_grafo():
    try:
        global grafo
        grafo.clear()
        vertices = int(input("Digite o número de vértices: "))
        if vertices <= 0:
            raise ValueError("Número de vértices deve ser positivo")
            
        matriz = [[0] * vertices for _ in range(vertices)]
        
        for i in range(vertices-1):
            valores = input(f"Digite os valores para o vértice {i} (conexões com vértices {i+1} até {vertices-1}, separados por espaço): ").split()
            if len(valores) != vertices - (i + 1):
                raise ValueError(f"Número incorreto de valores. Esperado {vertices - (i + 1)} valores.")
            
            for j, valor in enumerate(valores):
                if valor not in ['0', '1']:
                    raise ValueError("Valores devem ser 0 ou 1")
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
            
    except ValueError as e:
        print(f"Erro: {str(e)}")
        grafo.clear()
    except Exception as e:
        print(f"Erro inesperado: {str(e)}")
        grafo.clear()

def gerar_grafo_aleatorio():
    try:
        global grafo
        grafo.clear()
        
        print("\nEscolha o tipo de grafo:")
        print("1. Aleatório")
        print("2. Ciclo")
        print("3. Roda")
        print("4. Completo")
        print("5. Euleriano")
        
        tipo = int(input("Digite o tipo desejado: "))
        if tipo not in [1, 2, 3, 4, 5]:
            raise ValueError("Tipo de grafo inválido")
            
        n = int(input("Digite o número de vértices (mínimo 3): "))
        if n < 3:
            raise ValueError("Número de vértices deve ser maior ou igual a 3")
        
        if tipo == 1:
            grafo = nx.erdos_renyi_graph(n, 0.5)
        elif tipo == 2:
            grafo = nx.cycle_graph(n)
        elif tipo == 3:
            grafo = nx.wheel_graph(n)
        elif tipo == 4:
            grafo = nx.complete_graph(n)
        elif tipo == 5:
            tentativas = 0
            while True:
                temp_grafo = nx.erdos_renyi_graph(n, 0.5)
                if all(temp_grafo.degree(node) % 2 == 0 for node in temp_grafo.nodes) and nx.is_connected(temp_grafo):
                    grafo = temp_grafo
                    break
                tentativas += 1                
    except ValueError as e:
        print(f"Erro: {str(e)}")
        grafo.clear()
    except Exception as e:
        print(f"Erro inesperado: {str(e)}")
        grafo.clear()

def entrada_subgrafo():
    try:
        global grafo, subgrafo
        subgrafo.clear()
        print("Vértices disponíveis no grafo principal:", list(grafo.nodes()))
        vertices = input("Digite os vértices do subgrafo (separados por espaço): ").split()
        vertices = [int(v) for v in vertices]
        
        if not all(v in grafo.nodes() for v in vertices):
            raise ValueError("Vértices inválidos selecionados")
        
        subgrafo = grafo.subgraph(vertices).copy()
        
    except ValueError as e:
        print(f"Erro: {str(e)}")
        subgrafo.clear()
    except Exception as e:
        print(f"Erro inesperado: {str(e)}")
        subgrafo.clear()

def gerar_subgrafo_aleatorio():
    try:
        global grafo, subgrafo
        subgrafo.clear()
        
        print("\nEscolha o tipo de subgrafo:")
        print("1. Clique")
        print("2. Conjunto Independente")
        print("3. Aleatório")
        
        tipo = int(input("Digite o tipo desejado: "))
        if tipo not in [1, 2, 3]:
            raise ValueError("Tipo de subgrafo inválido")
        
        total_vertices = len(grafo.nodes())
        
        if tipo == 1:
            cliques = list(nx.find_cliques(grafo))
            if not cliques:
                raise ValueError("Não foi possível encontrar cliques no grafo")
            maior_clique = max(cliques, key=len)
            max_vertices = len(maior_clique) - 1  # Limite máximo para clique
            if max_vertices <= 0:
                raise ValueError("Não é possível criar um clique menor neste grafo")
            print(f"\nTamanho do maior clique possível: {max_vertices}")
            n = int(input(f"Digite o número de vértices para o clique (máximo {max_vertices}): "))
            if n <= 0 or n > max_vertices:
                raise ValueError(f"O número deve estar entre 1 e {max_vertices}")
            vertices = maior_clique[:n]
            subgrafo = grafo.subgraph(vertices).copy()
            
        elif tipo == 2:
            maior_independente = nx.maximal_independent_set(grafo)
            max_vertices = len(maior_independente) - 1  # Limite máximo para conjunto independente
            if max_vertices <= 0:
                raise ValueError("Não é possível criar um conjunto independente menor neste grafo")
            print(f"\nTamanho do maior conjunto independente possível: {max_vertices}")
            n = int(input(f"Digite o número de vértices para o conjunto independente (máximo {max_vertices}): "))
            if n <= 0 or n > max_vertices:
                raise ValueError(f"O número deve estar entre 1 e {max_vertices}")
            vertices = list(maior_independente)[:n]
            subgrafo = grafo.subgraph(vertices).copy()
            
        else:
            max_vertices = total_vertices - 1  # Limite para subgrafo aleatório
            print(f"\nTamanho máximo para subgrafo aleatório: {max_vertices}")
            n = int(input(f"Digite o número de vértices para o subgrafo (máximo {max_vertices}): "))
            if n <= 0 or n > max_vertices:
                raise ValueError(f"O número deve estar entre 1 e {max_vertices}")
            import random
            vertices = random.sample(list(grafo.nodes()), n)
            subgrafo = grafo.subgraph(vertices).copy()
            
    except ValueError as e:
        print(f"Erro: {str(e)}")
        subgrafo.clear()
    except Exception as e:
        print(f"Erro inesperado: {str(e)}")
        subgrafo.clear()



def mostrar_grafos():
    try:
        global grafo, subgrafo
        plt.figure(figsize=(12, 6))
        
        plt.subplot(121)
        plt.title("Grafo Principal")
        nx.draw(grafo, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
        
        plt.subplot(122)
        plt.title("Subgrafo")
        if len(subgrafo.nodes()) > 0:
            nx.draw(subgrafo, with_labels=True, node_color='lightgreen', edge_color='gray', node_size=1000, font_size=12)
        else:
            plt.text(0.5, 0.5, "Subgrafo vazio", horizontalalignment='center', verticalalignment='center')
        
        plt.show()
        
    except Exception as e:
        print(f"Erro ao mostrar grafos: {str(e)}")

def verificar_classe_subgrafo():
    try:
        global subgrafo
        
        if len(subgrafo.nodes()) < 2:
            raise ValueError("Subgrafo precisa ter pelo menos 2 vértices para classificação")
        
        vertices = list(subgrafo.nodes())
        is_clique = all(subgrafo.has_edge(u, v) for u in vertices for v in vertices if u != v)
        is_independent = len(subgrafo.edges()) == 0
        
        print("Classificação do subgrafo:")
        if is_clique:
            print("É um clique")
        elif is_independent:
            print("É um conjunto independente")
        else:
            print("Não é nem clique nem conjunto independente")
        input("\nPressione Enter para continuar...")
    except ValueError as e:
        print(f"Erro: {str(e)}")
    except Exception as e:
        print(f"Erro inesperado: {str(e)}")
def calcular_subgrafos_possiveis():
    try:
        global grafo
        from itertools import combinations                                          # type: ignore
        from matplotlib.widgets import Button, TextBox, CheckButtons                # type: ignore
        
        if len(grafo.nodes()) > 10:
            resposta = input("Aviso: Grafos grandes podem gerar muitos subgrafos. Continuar? (s/n): ")
            if resposta.lower() != 's':
                return
        
        vertices = list(grafo.nodes())
        todas_possibilidades = []
        
        for r in range(1, len(vertices) + 1):
            for comb in combinations(vertices, r):
                subg = grafo.subgraph(comb).copy()
                todas_possibilidades.append(subg)
                
        indice_atual = [0]
        filtros = {'Cliques': True, 'Independentes': True, 'Outros': True}
        num_vertices = [None]  # Adiciona uma variável para armazenar o número de vértices desejado
        
        def is_clique(g):
            vertices = list(g.nodes())
            return all(g.has_edge(u, v) for u in vertices for v in vertices if u != v)
            
        def is_independent(g):
            return len(g.edges()) == 0
            
        def get_filtered_subgraphs():
            filtered = []
            for g in todas_possibilidades:
                if (filtros['Cliques'] and is_clique(g)) or \
                   (filtros['Independentes'] and is_independent(g)) or \
                   (filtros['Outros'] and not is_clique(g) and not is_independent(g)):
                    if num_vertices[0] is None or len(g.nodes()) == num_vertices[0]:
                        filtered.append(g)
            return filtered
        
        fig, (ax_main, ax_sub) = plt.subplots(1, 2, figsize=(14, 6))
        plt.subplots_adjust(bottom=0.3, left=0.3)
        
        def update_plot():
            filtered = get_filtered_subgraphs()
            if not filtered:
                ax_sub.clear()
                ax_sub.text(0.5, 0.5, "Nenhum subgrafo encontrado\ncom os filtros atuais",
                            ha='center', va='center')
                return
                
            if indice_atual[0] >= len(filtered):
                indice_atual[0] = len(filtered) - 1
                
            ax_sub.clear()
            ax_sub.set_title(f"Subgrafo {indice_atual[0] + 1}/{len(filtered)}")
            nx.draw(filtered[indice_atual[0]], ax=ax_sub, with_labels=True,
                    node_color='lightgreen', edge_color='gray',
                    node_size=1000, font_size=12)
            
        def next_graph(event):
            filtered = get_filtered_subgraphs()
            if indice_atual[0] < len(filtered) - 1:
                indice_atual[0] += 1
                update_plot()
                plt.draw()
                
        def prev_graph(event):
            if indice_atual[0] > 0:
                indice_atual[0] -= 1
                update_plot()
                plt.draw()
                
        def goto_graph(text):
            filtered = get_filtered_subgraphs()
            try:
                num = int(text)
                if 1 <= num <= len(filtered):
                    indice_atual[0] = num - 1
                    update_plot()
                    plt.draw()
            except ValueError:
                pass
                
        def on_key(event):
            if event.key == 'right':
                next_graph(event)
            elif event.key == 'left':
                prev_graph(event)
                
        def on_check(label):
            filtros[label] = not filtros[label]
            indice_atual[0] = 0
            update_plot()
            plt.draw()
        
        def on_vertices_change(text):
            try:
                num = int(text)
                if num > 0:
                    num_vertices[0] = num
                else:
                    num_vertices[0] = None
            except ValueError:
                num_vertices[0] = None
            indice_atual[0] = 0
            update_plot()
            plt.draw()
                
        ax_prev = plt.axes([0.2, 0.05, 0.15, 0.075])
        ax_next = plt.axes([0.7, 0.05, 0.15, 0.075])
        ax_text = plt.axes([0.4, 0.05, 0.1, 0.075])
        ax_goto = plt.axes([0.5, 0.05, 0.15, 0.075])
        ax_check = plt.axes([0.05, 0.4, 0.15, 0.15])
        ax_vertices = plt.axes([0.05, 0.2, 0.15, 0.075])
        
        btn_prev = Button(ax_prev, 'Anterior')
        btn_next = Button(ax_next, 'Próximo')
        text_box = TextBox(ax_text, '', initial='')
        btn_goto = Button(ax_goto, 'Ir para')
        check = CheckButtons(ax_check, ['Cliques', 'Independentes', 'Outros'], 
                           [True, True, True])
        vertices_box = TextBox(ax_vertices, 'Vértices', initial='')
        
        btn_prev.on_clicked(prev_graph)
        btn_next.on_clicked(next_graph)
        btn_goto.on_clicked(lambda x: goto_graph(text_box.text))
        check.on_clicked(on_check)
        vertices_box.on_submit(on_vertices_change)
        
        fig.canvas.mpl_connect('key_press_event', on_key)
        
        # Desenha o grafo completo na subplot principal
        ax_main.set_title("Grafo Completo")
        nx.draw(grafo, ax=ax_main, with_labels=True, node_color='lightblue', edge_color='gray', node_size=500, font_size=10)
        
        update_plot()
        plt.show()
            
    except Exception as e:
        print(f"Erro ao calcular subgrafos: {str(e)}")
if __name__ == "__main__":
    main()
