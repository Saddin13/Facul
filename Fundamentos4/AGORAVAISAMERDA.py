import numpy as np
import os 

def f(x):
    return 0.5 * (x[0] - 3)**2 + (x[1] - 1)**2
def grad_f(x):
    return np.array([x[0] - 3, 2 * (x[1] - 1)])
def golden_section_search(f_alpha, a, b, tol=1e-4):
    gr = (np.sqrt(5) + 1) / 2
    c = b - (b - a) / gr
    d = a + (b - a) / gr
    while abs(c - d) > tol:
        if f_alpha(c) < f_alpha(d):
            b = d
        else:
            a = c
        c = b - (b - a) / gr
        d = a + (b - a) / gr
    return (b + a) / 2
def aurea(x0, tolerancia):
    x = x0.copy()
    x_saida = np.zeros_like(x0)
    print(f"\nIniciando método de Cauchy (seção áurea)")
    print("Iter |        x[0]        |        x[1]        |      f(x)      |    grad[0]    |    grad[1]    |    d[0]    |    d[1]    |   alpha   |   norma   ")
    print("-"*110)
    i=0
    while True:
        grad = grad_f(x)
        d = -grad  # Direção de descida
        f_alpha = lambda alpha: f(x + alpha * d)
        alpha = golden_section_search(f_alpha, 0, 2, tolerancia)
        x_new = x + alpha * d
        norma = np.linalg.norm(x_new - x)

        # Print todos os dados em uma linha só
        print(f"{i+1:4d} | {x_new[0]:16.8f} | {x_new[1]:16.8f} | {f(x_new):12.6f} | {grad[0]:12.6f} | {grad[1]:12.6f} | {d[0]:9.6f} | {d[1]:9.6f} | {alpha:9.6f} | {norma:9.6f}")
        
        if norma < tolerancia:
            return x_new, i + 1, False
        i+=1
        if np.array_equal(x_saida, x_new) or np.array_equal(x_saida, x):
            return x_new, i + 1, True
        x_saida = x
        x = x_new
    
def fixed(x0, tolerancia):
    x = x0.copy()
    x_saida = np.zeros_like(x0)
    print(f"\nIniciando método de Cauchy (passo fixo)")
    print("Iter |        x[0]        |        x[1]        |      f(x)      |    grad[0]    |    grad[1]    |    d[0]    |    d[1]    |   alpha   |   norma   ")
    print("-"*110)
    i=0
    while True:
        grad = grad_f(x)
        d = -grad  # Direção de descida
        alpha = 1.0
        x_new = x + alpha * d
        norma = np.linalg.norm(x_new - x)

        # Print todos os dados em uma linha só
        print(f"{i+1:4d} | {x_new[0]:16.8f} | {x_new[1]:16.8f} | {f(x_new):12.6f} | {grad[0]:12.6f} | {grad[1]:12.6f} | {d[0]:9.6f} | {d[1]:9.6f} | {alpha:9.6f} | {norma:9.6f}")
        
        if norma < tolerancia:
            return x_new, i + 1, False
        i+=1
        if np.array_equal(x_saida, x_new) or np.array_equal(x_saida, x):
            return x_new, i + 1, True
        x_saida = x
        x = x_new
def main():
    os.system('cls' if os.name == 'nt' else 'clear')
    x0 = np.array([1.0, 0.0])
    tolerancia = 1e-6
    saidaA, iteracoesA, divergenteA= aurea(x0, tolerancia)
    print("\n" + "-"*15)
    print("\nResultados finais:")
    if divergenteA == True:
        print("O método não converge")
    else:
        print(f"Solução ótima aproximada: x1 = {saidaA[0]:.8f}, x2 = {saidaA[1]:.8f}")
        print(f"Em {iteracoesA} iterações")
    print("\n" + "-"*110)
    saida,iteracoes,divergente = fixed(x0, tolerancia)
    print("\n" + "-"*15)
    print("\nResultados finais:")
    if divergente == True:
        print("O método não converge")
    else:
        print(f"Solução ótima aproximada: x1 = {saida[0]:.8f}, x2 = {saida[1]:.8f}")
        print(f"Em {iteracoes} iterações")
    
    input("\n\n\n\n\n\n\n\n")

if __name__ == "__main__":
    main()