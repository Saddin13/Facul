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
    print("Criando um grafo bipartido para disciplinas e laboratórios...")
    grafo = CriarGrafoDiscLab()
    plt.figure(figsize=(8, 6))
    pos = nx.spring_layout(grafo)
    nx.draw(grafo, pos, with_labels=True, node_color='lightblue', edge_color='gray', node_size=1000, font_size=12)
    plt.title("Grafo Bipartido de Disciplinas e Laboratórios")
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
    plt.show()
if __name__ == "__main__":
    main()