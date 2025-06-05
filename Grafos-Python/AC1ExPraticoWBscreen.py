import os
import networkx as nx                                                                       # type: ignore
import matplotlib.pyplot as plt                                                             # type: ignore

def CriarGrafoDiscLab():
    Grafo = nx.Graph()
    while True:
        try:
            nLaboratorios = int(input("Digite o número de laboratórios Disponiveis: "))
            if nLaboratorios <= 0:
                print("Número inválido. Por favor, insira um número positivo.")
            else:
                break
        except ValueError:
            os.system('cls' if os.name == 'nt' else 'clear')
            print("Entrada inválida! Por favor, insira um número Natural.")
    for i in range(nLaboratorios):
        Grafo.add_node(f"Lab{i+1}", bipartite=0)
    while True:
        try:    
            nDisciplinas = int(input("Digite o número de disciplinas: "))
            if nDisciplinas <= 0:
                print("Número inválido. Por favor, insira um número positivo.")
            else:
                break
        except ValueError:
            os.system('cls' if os.name == 'nt' else 'clear')
            print("Entrada inválida! Por favor, insira um número Natural.")
    nLabU = 1
    for i in range(nDisciplinas):
        while True:
            try:
                nTurmas = int(input(f"Digite o número de turmas da disciplina {i+1}: "))
                if nTurmas <= 0:
                    print("Número inválido. Por favor, insira um número positivo.")
                else:
                    break
            except ValueError:
                os.system('cls' if os.name == 'nt' else 'clear')
                print("Entrada inválida! Por favor, insira um número Natural.")
        nTurmasT = 0
        for j in range(nTurmas):
            turma = f"M{i+1}T{j+1}"
            Grafo.add_node(turma, bipartite=1)
            lab = f"Lab{nLabU}"
            nLabU = nLabU + 1
            Grafo.add_edge(turma, lab)
            nTurmasT = nTurmasT+1
            if nLabU > nLaboratorios:
                nLabU = 1
    nHorarios = Professores(Grafo,nLaboratorios,nTurmasT)
    diaHorario(Grafo,nHorarios)
    return Grafo
def Professores(Grafo,nLaboratorios,nTurmasT):
    while True:
        try:
            nProfessores = int(input("Digite o número de professores: "))
            if nProfessores <= 0:
                print("Número inválido. Por favor, insira um número positivo.")
            else:
                break
        except ValueError:
            os.system('cls' if os.name == 'nt' else 'clear')
            print("Entrada inválida! Por favor, insira um número Natural.")
    professores = []
    if nProfessores > nLaboratorios:
        nHorarios = int((nTurmasT / nLaboratorios)+1)
    else:
        nHorarios = int((nTurmasT / nProfessores)+1)
    for i in range(nProfessores):
        professor = f"Prof{i+1}"
        professores.append(professor)
        Grafo.add_node(professor, bipartite=2)
    turmas = [node for node, data in Grafo.nodes(data=True) if data.get('bipartite') == 1]
    for i, turma in enumerate(turmas):
        professor = professores[i % nProfessores]
        Grafo.add_edge(professor, turma)
    return nHorarios
def diaHorario(Grafo,nHorariosT):
    nDiasN = (nHorariosT*2)/3
    if nDiasN > 3:
        print("A quantidade de dias nessesarios para dar Duas aula por semana para cada turma é maior que 5, o que torna impossivel.")
    nDias = 3
    nHorarios = 3
    turmas = [node for node, data in Grafo.nodes(data=True) if data.get('bipartite') == 1]
    turma_index = 0 
    for k in range(2):
        for i in range(nDias):
            if i == 2:
                    j=1
                    dia1horario = f"D{i+1}H{j+1}"
                    dia2horario = f"D{i+1}H{j+2}"
                    Grafo.add_node(dia1horario, bipartite=3)
                    Grafo.add_node(dia2horario, bipartite=3)
                    if turma_index < len(turmas):
                        turma = turmas[turma_index]
                        Grafo.add_edge(dia1horario, turma)
                        Grafo.add_edge(dia2horario, turma)
                        professor = [n for n in Grafo.neighbors(turma) if Grafo.nodes[n].get('bipartite') == 2]
                        if professor:
                            Grafo.add_edge(dia1horario, professor[0])
                            Grafo.add_edge(dia2horario, professor[0])   
                        turma_index = turma_index + 1
            else:
                for j in range(nHorarios):
                    dia1horario = f"D{i+1}H{j+1}"
                    dia2horario = f"D{i+3}H{j+1}"    
                    Grafo.add_node(dia1horario, bipartite=3)
                    Grafo.add_node(dia2horario, bipartite=3)
                    if turma_index < len(turmas):
                        turma = turmas[turma_index]
                        Grafo.add_edge(dia1horario, turma)
                        Grafo.add_edge(dia2horario, turma)
                        professor = [n for n in Grafo.neighbors(turma) if Grafo.nodes[n].get('bipartite') == 2]
                        if professor:
                            Grafo.add_edge(dia1horario, professor[0])
                            Grafo.add_edge(dia2horario, professor[0])
                        turma_index =turma_index+1
def main():
    os.system('cls' if os.name == 'nt' else 'clear')
    print("Sistema de Gerenciamento de Laboratórios")
    print("Criando um grafo multipartido para disciplinas, laboratórios, professores e horários...")
    grafo = CriarGrafoDiscLab()
    plt_obj = visualizar_grafo_interativo(grafo)
    os.system('cls' if os.name == 'nt' else 'clear')
    print("Turma - Professor - Laboratório - Dia e Horário")
    for node in grafo.nodes(data=True):
        if node[1].get('bipartite') == 1:
            professor = [n for n in grafo.neighbors(node[0]) if grafo.nodes[n].get('bipartite') == 2]
            lab = [n for n in grafo.neighbors(node[0]) if grafo.nodes[n].get('bipartite') == 0]
            DH = [n for n in grafo.neighbors(node[0]) if grafo.nodes[n].get('bipartite') == 3]

            professorstring = ", ".join(professor) if professor else "Nenhum"
            labstring = ", ".join(lab) if lab else "Nenhum"
            DHstring = ", ".join(DH) if DH else "Nenhum"
        
            print(f"{node[0]}  - {professorstring}     - {labstring}        - {DHstring}")
    plt_obj.show()
def visualizar_grafo_interativo(grafo):
    plt.figure(figsize=(12, 10))
    laboratorios = [node for node, data in grafo.nodes(data=True) if data.get('bipartite') == 0]
    turmas = [node for node, data in grafo.nodes(data=True) if data.get('bipartite') == 1]
    professores = [node for node, data in grafo.nodes(data=True) if data.get('bipartite') == 2]
    horarios = [node for node, data in grafo.nodes(data=True) if data.get('bipartite') == 3]
    pos = {}
    for i, lab in enumerate(laboratorios):
        pos[lab] = (i * 2, 3)
    for i, turma in enumerate(turmas):
        pos[turma] = (i * 2, 2)
    for i, prof in enumerate(professores):
        pos[prof] = (i * 3, 1)
    for i, horario in enumerate(horarios):
        pos[horario] = (i * 1.5, 0)
    node_colors = {}
    for node in laboratorios:
        node_colors[node] = 'lightblue'
    for node in turmas:
        node_colors[node] = 'lightgreen'
    for node in professores:
        node_colors[node] = 'salmon'
    for node in horarios:
        node_colors[node] = 'yellow'
    all_edges = list(grafo.edges())
    edge_collection = nx.draw_networkx_edges(grafo, pos, edgelist=all_edges, 
                                           edge_color='#555555', width=1.0, alpha=0.5)
    node_collection = nx.draw_networkx_nodes(grafo, pos, 
                                           node_color=[node_colors[node] for node in grafo.nodes()],
                                           node_size=700)
    nx.draw_networkx_labels(grafo, pos, font_size=10, font_weight='bold')
    lab_patch = plt.Line2D([0], [0], marker='o', color='w', markerfacecolor='lightblue', 
                          markersize=10, label='Laboratórios')
    turma_patch = plt.Line2D([0], [0], marker='o', color='w', markerfacecolor='lightgreen', 
                            markersize=10, label='Turmas')
    prof_patch = plt.Line2D([0], [0], marker='o', color='w', markerfacecolor='salmon', 
                           markersize=10, label='Professores')
    horario_patch = plt.Line2D([0], [0], marker='o', color='w', markerfacecolor='yellow', 
                              markersize=10, label='Horários')
    plt.legend(handles=[lab_patch, turma_patch, prof_patch, horario_patch])
    plt.title("Alocação de Laboratórios, Professores e Horários para Turmas\n(Clique em um nó para destacar suas conexões)")
    plt.axis('off')
    last_clicked_node = [None]
    highlighted_edge_collection = [None]
    def on_click(event):
        if event.inaxes is not None:
            cont, ind = node_collection.contains(event)
            if cont:
                node_idx = ind["ind"][0]
                clicked_node = list(grafo.nodes())[node_idx]
                edge_collection.set_color('#555555')
                edge_collection.set_linewidth(1.0)
                edge_collection.set_alpha(0.5)
                if highlighted_edge_collection[0] is not None:
                    highlighted_edge_collection[0].remove()
                    highlighted_edge_collection[0] = None
                if clicked_node == last_clicked_node[0]:
                    last_clicked_node[0] = None
                    plt.title("Alocação de Laboratórios, Professores e Horários para Turmas\n(Clique em um nó para destacar suas conexões)")
                    plt.draw()
                    return
                last_clicked_node[0] = clicked_node
                highlighted_edges = []
                for u, v in grafo.edges():
                    if u == clicked_node or v == clicked_node:
                        highlighted_edges.append((u, v))
                if highlighted_edges:
                    highlighted_edge_collection[0] = nx.draw_networkx_edges(
                        grafo, pos, edgelist=highlighted_edges, 
                        edge_color='#CC0000',  
                        width=2.5, 
                        alpha=1.0
                    )
                node_info = f"Nó: {clicked_node}"
                if clicked_node in turmas:
                    professor = [n for n in grafo.neighbors(clicked_node) if grafo.nodes[n].get('bipartite') == 2]
                    lab = [n for n in grafo.neighbors(clicked_node) if grafo.nodes[n].get('bipartite') == 0]
                    DH = [n for n in grafo.neighbors(clicked_node) if grafo.nodes[n].get('bipartite') == 3]
                    professorstring = ", ".join(professor) if professor else "Nenhum"
                    labstring = ", ".join(lab) if lab else "Nenhum"
                    DHstring = ", ".join(DH) if DH else "Nenhum"
                    node_info += f"\nProfessor: {professorstring}\nLaboratório: {labstring}\nHorários: {DHstring}"
                plt.title(node_info)
                plt.draw()
    plt.gcf().canvas.mpl_connect('button_press_event', on_click)
    return plt
if __name__ == "__main__":
    main()