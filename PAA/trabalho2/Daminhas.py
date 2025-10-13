import os

def criar_matriz():
    while True:
        print("Digite o tamanho da matriz (n x n): ")
        n = int(input())
        os.system('cls' if os.name == 'nt' else 'clear')
        if n >= 4 and n <= 20:
            break
        else:
            os.system('cls' if os.name == 'nt' else 'clear')
            print("Tamanho inválido. O tamanho deve ser entre 4 e 20.")
    matriz = [["-" for _ in range(n)] for _ in range(n)]
    iniciaisQmax = [["-" for _ in range(n)] for _ in range(n)]
    return matriz, n, iniciaisQmax

def resetar_matriz(matriz, n):
    for i in range(n):
        for j in range(n):
            matriz[i][j] = "-"

def imprimir_matriz(matriz):
    n = len(matriz)
    print("  " + " ".join(str(i) for i in range(n)))
    for i in range(n):
        print(str(i) + " " + " ".join(matriz[i]))
    print()

def restanteDasDamas(matriz, n):
    valormaximo = 1
    for i in range(n):
        for j in range(n):
            if any(matriz[i][k] == "D" for k in range(n)):
                continue
            if any(matriz[k][j] == "D" for k in range(n)):
                continue
            if any(matriz[k][l] == "D" for k, l in zip(range(i-min(i,j), n), range(j-min(i,j), n))):
                continue
            if any(matriz[k][l] == "D" for k, l in zip(range(i-min(i,n-1-j), n), range(j+min(i,n-1-j), -1, -1))):
                continue
            matriz[i][j] = "D"
            valormaximo += 1
    return valormaximo

def primeiradama(matriz, n,iniciaisQmax):
    Qmax = 0
    valormaximo = 0
    for i in range(n):
        matriz[i][0] = "D"
        Qdamas = restanteDasDamas(matriz, n)
        if valormaximo < Qdamas:
            valormaximo = Qdamas
            Qmax = 1
        if valormaximo == Qdamas:
            Qmax += 1
            iniciaisQmax[i][0] = "M"
            imprimir_matriz(matriz)
        resetar_matriz(matriz, n)
    for i in range(n):
        matriz[0][i] = "D"
        Qdamas = restanteDasDamas(matriz, n) 
        if valormaximo < Qdamas:
            valormaximo = Qdamas
            Qmax = 0
        if valormaximo == Qdamas:
            Qmax += 1
            iniciaisQmax[0][i] = "M"
            imprimir_matriz(matriz)
        resetar_matriz(matriz, n)
    for i in range(n):
        matriz[i][i] = "D"
        Qdamas = restanteDasDamas(matriz, n) 
        if valormaximo < Qdamas:
            valormaximo = Qdamas
            Qmax = 0
        if valormaximo == Qdamas:
            Qmax += 1
            iniciaisQmax[i][i] = "M"
            imprimir_matriz(matriz)
        resetar_matriz(matriz, n)
    return valormaximo, Qmax,iniciaisQmax

matriz, n, iniciaisQmax = criar_matriz()
valormaximo,Qmax,iniciaisQmax  = primeiradama(matriz, n,iniciaisQmax)
print("Número máximo de damas que podem ser colocadas:", valormaximo)
print("Número de soluções com o número máximo de damas:", Qmax)
print("\nPosições iniciais das damas nas soluções com o número máximo de damas (M):")
imprimir_matriz(iniciaisQmax)
print("\n\nOBS:O codigo nao testa casas que nao estao na primeira linha, coluna ou diagonal principal, \npois elas estariam somente criando matrizes iguais a estas.\n")