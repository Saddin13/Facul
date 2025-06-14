import os
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np
import random
import pygame

grafo = nx.Graph()
def mostrar_mensagem_pygame(screen, font, mensagens, cor=(255,255,255), tempo=5, botao_restart=True):
    import time
    WIDTH, HEIGHT = screen.get_size()
    screen.fill((10, 10, 10))
    y = HEIGHT // 2 - 30 * len(mensagens) // 2
    for msg in mensagens:
        text = font.render(msg, True, cor)
        rect = text.get_rect(center=(WIDTH // 2, y))
        screen.blit(text, rect)
        y += 40
    pygame.display.flip()
    start = time.time()
    if botao_restart:
        # Mostra botão de restart após tempo
        while time.time() - start < tempo:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    exit()
            pygame.time.wait(100)
        # Mostra botão de restart
        botao_text = font.render("Pressione R para reiniciar", True, cor)
        rect = botao_text.get_rect(center=(WIDTH // 2, HEIGHT // 2 + 60))
        screen.blit(botao_text, rect)
        pygame.display.flip()
        esperando = True
        while esperando:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    exit()
                elif event.type == pygame.KEYDOWN and event.key == pygame.K_r:
                    esperando = False
            pygame.time.wait(100)
def posicoes_personalizadas():
    pos = {}
    centro = (0, 0)
    raio_hepta = 1.7
    for i in range(7):
        ang = 2 * np.pi * i / 7
        pos[i] = (centro[0] + raio_hepta * np.cos(ang), centro[1] + raio_hepta * np.sin(ang))
    raio_ext = 2.0
    for i, idx in enumerate(range(7, 11)):
        ang = np.pi/4 + np.pi/2 * i
        pos[idx] = (centro[0] + raio_ext * np.cos(ang), centro[1] + raio_ext * np.sin(ang))
    raio_int = 0.5
    for i, idx in enumerate(range(11, 15)):
        ang = np.pi/4 + np.pi/2 * i
        pos[idx] = (centro[0] + raio_int * np.cos(ang), centro[1] + raio_int * np.sin(ang))
    return pos

def mostrar_grafo(casaAtual, pos):
    WIDTH, HEIGHT = 1200, 1000
    NODE_RADIUS = 30
    BOSS_RADIUS = 50
    FPS = 60

    BLACK = (10, 10, 10)
    BLUE = (0, 120, 255)
    LIGHT_BLUE = (120, 180, 255)
    ORANGE = (200, 100, 0)   
    GRAY = (80, 80, 80)      
    LIGHT_GRAY = (200, 200, 200) 
    DARKRED = (200, 50, 0)      
    RED = (220, 30, 30)
    WHITE = (255, 255, 255)

    pygame.init()
    screen = pygame.display.set_mode((WIDTH, HEIGHT))
    pygame.display.set_caption("Grafo - Jogo de Anuncio Fake")
    font = pygame.font.SysFont(None, 28)
    clock = pygame.time.Clock()

    xs = [x for x, y in pos.values()]
    ys = [y for x, y in pos.values()]
    min_x, max_x = min(xs), max(xs)
    min_y, max_y = min(ys), max(ys)
    def norm(p):
        x, y = p
        nx_ = int((x - min_x) / (max_x - min_x) * (WIDTH - 2*BOSS_RADIUS) + BOSS_RADIUS)
        ny_ = int((y - min_y) / (max_y - min_y) * (HEIGHT - 2*BOSS_RADIUS) + BOSS_RADIUS)
        return nx_, ny_

    running = True
    clicked_node = None
    while running:
        screen.fill(BLACK)

        vizinhos = list(grafo.neighbors(casaAtual))
        valor_atual = grafo.nodes[casaAtual]['valor']

        for u, v in grafo.edges():
            # Se a aresta conecta a casaAtual com um vizinho, pinta de LIGHT_GRAY
            if (u == casaAtual and v in vizinhos) or (v == casaAtual and u in vizinhos):
                pygame.draw.line(screen, LIGHT_GRAY, norm(pos[u]), norm(pos[v]), 6)
            else:
                pygame.draw.line(screen, GRAY, norm(pos[u]), norm(pos[v]), 4)

        # Classifica vizinhos possíveis de matar
        vizinhos_possiveis = [v for v in vizinhos if grafo.nodes[v]['valor'] < valor_atual]
        vizinhos_impossiveis = [v for v in vizinhos if grafo.nodes[v]['valor'] >= valor_atual]

        for n in grafo.nodes:
            p = norm(pos[n])
            valor = grafo.nodes[n]['valor']
            mx, my = pygame.mouse.get_pos()
            radius = BOSS_RADIUS if n == 14 else NODE_RADIUS
            is_hover = (mx - p[0])**2 + (my - p[1])**2 < (radius + 8)**2
            draw_radius = radius + 8 if is_hover else radius

            if n == casaAtual:
                color = BLUE
            elif n == 14:
                color = RED
            elif n in vizinhos_possiveis:
                color = ORANGE
            elif n in vizinhos_impossiveis:
                color = DARKRED
            else:
                color = LIGHT_BLUE

            pygame.draw.circle(screen, color, p, draw_radius)
            # Valor do nó ou "BOSS"
            if n == 14:
                text = font.render("BOSS", True, WHITE)
            else:
                text = font.render(str(valor), True, BLACK)
            text_rect = text.get_rect(center=p)
            screen.blit(text, text_rect)

        pygame.display.flip()

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                exit()
            elif event.type == pygame.MOUSEBUTTONDOWN:
                mx, my = event.pos
                for n in grafo.nodes:
                    p = norm(pos[n])
                    radius = BOSS_RADIUS if n == 14 else NODE_RADIUS
                    if (mx - p[0])**2 + (my - p[1])**2 < radius**2:
                        if n != casaAtual and n in vizinhos_possiveis:
                            clicked_node = n
                            running = False
                        break
        clock.tick(FPS)

    if clicked_node is not None:
        return grafo.nodes[clicked_node]['valor']
    return None

def gerarValoresCasas():
    global grafo
    distancias = nx.single_source_shortest_path_length(grafo, 0)
    valores = {}
    while True:
        for node in grafo.nodes:
            distancia = distancias[node]
            if distancia == 0 or node == 0:
                valor = 5  # Agora o nó 0 sempre recebe 5
                valores[node] = valor
            elif node < 14:
                minimo = (distancia * 10) - 10
                if minimo == 0:
                    minimo = 3
                maximo = distancia * 10
                valor = random.randint(minimo, maximo)
                valores[node] = valor
            elif node == 14:
                valores[node] = 100
        # Garante que pelo menos um vizinho de 0 tenha valor < 4
        vizinhos_0 = list(grafo.neighbors(0))
        vizinhos_validos = [v for v in vizinhos_0 if v != 14]
        if vizinhos_validos:
            vizinho_especial = vizinhos_validos[0]
        else:
            vizinho_especial = None
        valores[vizinho_especial] = 3
        soma = sum(val for node, val in valores.items() if node != 14)
        if soma > 100:
            break
    nx.set_node_attributes(grafo, valores, 'valor')

def main():
    global grafo
    grafo = nx.Graph()
    while True:
        grafo = nx.erdos_renyi_graph(15, 0.4)
        if all(grafo.degree(node) % 2 == 0 for node in grafo.nodes) and nx.is_connected(grafo):
            break
    CasaAtual = 0
    gerarValoresCasas()
    print("Jogo de Anuncio Fake")
    posicoes = posicoes_personalizadas()
    while True:
        volta = mostrar_grafo(CasaAtual, posicoes)
        if volta is None:
            print("Selecione um vizinho possível para continuar.")
            continue
        valor_atual = grafo.nodes[CasaAtual]['valor']
        vizinhos = list(grafo.neighbors(CasaAtual))
        valores_vizinhos = [grafo.nodes[v]['valor'] for v in vizinhos]
        if not vizinhos:
            print("Você perdeu! Não há mais conexões possíveis a partir da casa atual.")
            break
        # NOVO: verifica se não há vizinho possível de matar
        vizinhos_possiveis = [v for v in vizinhos if grafo.nodes[v]['valor'] < valor_atual]
        if not vizinhos_possiveis:
            print("Você perdeu! Não há vizinhos que você possa derrotar.")
            break
        if volta > valor_atual:
            print("Interação inválida! O valor do vizinho é maior que o valor da casa atual. Tente novamente.")
            continue
        else:
            novo_no = [n for n, v in grafo.nodes(data='valor') if v == volta][0]
            novo_valor = grafo.nodes[novo_no]['valor'] + valor_atual
            vizinhos_novo = list(grafo.neighbors(novo_no))
            grafo.remove_node(CasaAtual)
            grafo = nx.relabel_nodes(grafo, {novo_no: 0})
            grafo.nodes[0]['valor'] = novo_valor
            CasaAtual = 0
            if 14 not in grafo.nodes:
                mensagens = [
                    "O nó 14 foi removido. Fim de jogo!",
                    "Você chegou ao fim do jogo! Parabéns!",
                    f"Valor da casa atual antes de remover o nó 14: {grafo.nodes[0]['valor']}",
                    f"Parabens Voce matou o BOSS com: {grafo.nodes[0]['valor'] - 100}"
                ]
    mostrar_mensagem_pygame(screen, font, mensagens)
    # Reinicia o jogo
    main()
    return
if __name__ == "__main__":
    main()