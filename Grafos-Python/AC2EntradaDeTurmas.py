import os
import networkx as nx                                                                       
import matplotlib.pyplot as plt                                                             

def CriarGrafoDiscLab():
    Grafo = nx.Graph()
    Mturmas = int(input ("Quantas turmas você quer adicionar: "))
    i = 0
    while i < Mturmas:
        i = i + 1
        try:
            Total = input("Digite o numero da disciplina, turma e horario separados por espaços: ")
            Total = Total.split()
            Disciplina = Total[0]
            Turma = Total[1]
            Horario = Total[2]
            Grafo.add_node(Disciplina, bipartite=0)
            Grafo.add_node(Turma, bipartite=1)
            Grafo.add_node(Horario, bipartite=2)
            Grafo.add_edge(Disciplina, Turma)
            Grafo.add_edge(Turma, Horario)

            # Verifica se existem turmas da mesma disciplina no mesmo horário
            for node in Grafo.nodes(data=True):
                if node[1].get('bipartite') == 1:  # Verifica se é uma turma
                    turma_horarios = [n for n in Grafo.neighbors(node[0]) if Grafo.nodes[n].get('bipartite') == 2]
                    if Horario in turma_horarios:
                        Grafo.add_edge(Turma, node[0])  # Adiciona aresta entre turmas no mesmo horário
        except (IndexError, ValueError):
            print("Entrada inválida. Certifique-se de digitar a disciplina, turma e horário separados por espaços.")
            continue
        break

    # Alocar laboratórios para as turmas, minimizando o número de laboratórios
    laboratorios = []
    for turma in [node for node, data in Grafo.nodes(data=True) if data.get('bipartite') == 1]:
        horarios_turma = [n for n in Grafo.neighbors(turma) if Grafo.nodes[n].get('bipartite') == 2]
        alocado = False
        for lab in laboratorios:
            if all(horario not in Grafo.neighbors(lab) for horario in horarios_turma):
                Grafo.add_node(lab, bipartite=0)
                for horario in horarios_turma:
                    Grafo.add_edge(lab, horario)
                alocado = True
                break
        if not alocado:
            novo_lab = f"Lab{len(laboratorios) + 1}"
            laboratorios.append(novo_lab)
            Grafo.add_node(novo_lab, bipartite=0)
            for horario in horarios_turma:
                Grafo.add_edge(novo_lab, horario)
    return Grafo

def diaHorario(Grafo, nHorariosT):
    nDiasN = (nHorariosT*2)/3
    if nDiasN > 3:
        print("A quantidade de dias necessários para dar duas aulas por semana para cada turma é maior que 5, o que torna impossível.")
    
    nDias = 3
    nHorarios = 3
    turmas = [node for node, data in Grafo.nodes(data=True) if data.get('bipartite') == 1]
    turma_index = 0 
    
    for k in range(2):
        for i in range(nDias):
            if i == 2:
                j = 1
                dia1horario = f"D{i+1}H{j+1}"
                dia2horario = f"D{i+1}H{j+2}"
                Grafo.add_node(dia1horario, bipartite=2)
                Grafo.add_node(dia2horario, bipartite=2)
                if turma_index < len(turmas):
                    turma = turmas[turma_index]
                    Grafo.add_edge(dia1horario, turma)
                    Grafo.add_edge(dia2horario, turma)
                    turma_index = turma_index + 1
            else:
                for j in range(nHorarios):
                    dia1horario = f"D{i+1}H{j+1}"
                    dia2horario = f"D{i+3}H{j+1}"    
                    Grafo.add_node(dia1horario, bipartite=2)
                    Grafo.add_node(dia2horario, bipartite=2)
                    if turma_index < len(turmas):
                        turma = turmas[turma_index]
                        Grafo.add_edge(dia1horario, turma)
                        Grafo.add_edge(dia2horario, turma)
                        turma_index = turma_index + 1

def main():
    os.system('cls' if os.name == 'nt' else 'clear')
    print("Sistema de Gerenciamento de Laboratórios")
    print("Criando um grafo bipartido para disciplinas e laboratórios...")
    grafo = CriarGrafoDiscLab()
    plt.figure(figsize=(8, 6))
    pos = nx.spring_layout(grafo)
    nx.draw(grafo, pos, with_labels=True, node_color='lightblue', 
            edge_color='gray', node_size=1000, font_size=12)
    plt.title("Grafo Bipartido de Disciplinas e Laboratórios")
    
    os.system('cls' if os.name == 'nt' else 'clear')
    print("Turma - Laboratório - Dia e Horário")
    for node in grafo.nodes(data=True):
        if node[1].get('bipartite') == 1:
            lab = [n for n in grafo.neighbors(node[0]) if grafo.nodes[n].get('bipartite') == 0]
            DH = [n for n in grafo.neighbors(node[0]) if grafo.nodes[n].get('bipartite') == 2]

            labstring = ", ".join(lab) if lab else "Nenhum"
            DHstring = ", ".join(DH) if DH else "Nenhum"

            print(f"{node[0]} - {labstring} - {DHstring}")
    
    plt.show()

if __name__ == "__main__":
    main()