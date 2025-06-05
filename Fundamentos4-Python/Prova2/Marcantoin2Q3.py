import numpy as np # type: ignore
import os

def jacobi_method(A, B, x0, tol=1e-5, max_iter=1000):
    n = len(A)
    x = x0.copy()
    x_new = x0.copy()
    iteration = 0
    
    print("Iteração 0:", x0)
    
    while iteration < max_iter:
        for i in range(n):
            sum = 0
            for j in range(n):
                if i != j:
                    sum += A[i][j] * x[j]
            x_new[i] = (B[i] - sum) / A[i][i]
        
        error = np.linalg.norm(x_new - x)
        x = x_new.copy()
        iteration += 1
        
        print(f"Iteração {iteration}: {x}, Erro: {error}")
        
        if error < tol:
            return x, iteration
    
    return x, iteration

def get_matrix_input(n):
    print(f"\nDigite os elementos da matriz A ({n}x{n}):")
    A = np.zeros((n, n))
    for i in range(n):
        for j in range(n):
            A[i][j] = float(input(f"A[{i+1}][{j+1}] = "))
    return A

def get_vector_input(n):
    print(f"\nDigite os elementos do vetor b ({n}x1):")
    B = np.zeros(n)
    for i in range(n):
        B[i] = float(input(f"B[{i+1}] = "))
    return B

def main():
    os.system('cls' if os.name == 'nt' else 'clear')
    
    print("Sistema linear padrao\n")
    print("    10  3 -2          57")
    print("A =  2  8 -1      B = 20")
    print("     1  1  5          -4")
    print("\nPonto inicial x0 = 0")
    print("Tolerancia: 1e-5")

    escolha = input("Deseja executar o sistema linear da padrao? (s/n): ")
    if escolha == 'n':
        print("Método de Jacobi para Sistemas Lineares")
            
        # Tamanho do sistema
        n = int(input("Digite o tamanho do sistema (n): "))
        
        # Obter matriz A
        A = get_matrix_input(n)
        
        # Obter vetor b
        B = get_vector_input(n)
        
        # Ponto inicial x0 = 0
        x0 = np.zeros(n)
        
        # Tolerância
        tol = float(input("\nDigite a tolerância (ex: 1e-5): "))
        
    else:
        A = np.array([[10, 3, -2], [2, 8, -1], [1, 1, 5]])
        B = np.array([57, 20, -4])
        tol = 1e-5
        x0 = np.zeros(3)

    print("\nSistema Linear:")
    print("Matriz A:")
    print(A)
    print("\nVetor b:")
    print(B)
    print("\nPonto inicial x0:", x0)
    print("Tolerância:", tol)
    print("\nResolvendo pelo método de Jacobi:\n")
    
    x, iterations = jacobi_method(A, B, x0, tol)
    
    print("\nResultado final:")
    print(f"Solução: {x}")
    print(f"Número de iterações: {iterations}")
    print(f"Verificação (Ax - B): {np.dot(A, x) - B}")
    

if __name__ == "__main__":
    main()