import numpy as np
import os

os.system('cls' if os.name == 'nt' else 'clear')

def F(x):
    return np.array([
        x[0]**2 + x[1]**2 - 1,
        x[0]**2 - x[1]**2 - 1
    ])

def jacobian(x):
    return np.array([
        [2*x[0], 2*x[1]],
        [2*x[0], -2*x[1]]
    ])

def newton_method(x0, tol=1e-5, max_iter=100):
    x = x0.copy()
    iteration = 0
    
    print(f"Iteração {iteration}: x = [{x[0]:.10f}, {x[1]:.10f}], F(x) = [{F(x)[0]:.10f}, {F(x)[1]:.10f}]")
    
    while iteration < max_iter:
        Fx = F(x)
        Jx = jacobian(x)
        
        try:
            s = np.linalg.solve(Jx, -Fx)
        except np.linalg.LinAlgError:
            print("Matriz Jacobiana singular!")
            return None, iteration
        
        x = x + s
        iteration += 1
        error = np.linalg.norm(s)
        
        print(f"Iteração {iteration}: x = [{x[0]:.10f}, {x[1]:.10f}], F(x) = [{F(x)[0]:.10f}, {F(x)[1]:.10f}], Erro = {error:.10f}")
        
        if error < tol:
            return x, iteration
    
    return x, iteration

def main():
    print("Sistema Não Linear:")
    print("f₁(x₁,x₂) = x₁² + x₂² - 1 = 0")
    print("f₂(x₁,x₂) = x₁² - x₂² - 1 = 0")
    
    escolha = input("\nUsar valores padrão? (x₀ = [1.0, 1.0], tol = 1e-5) [S/n]: ")
    
    if escolha.lower() != 'n':
        x0 = np.array([1.0, 1.0])
        tol = 1e-5
    else:
        print("\nDigite o ponto inicial x₀:")
        x1 = float(input("x₁ = "))
        x2 = float(input("x₂ = "))
        x0 = np.array([x1, x2])
        tol = float(input("\nDigite a tolerância (ex: 1e-5): "))
    
    print("\nPonto inicial x₀:", [f"{x:.10f}" for x in x0])
    print("Tolerância:", f"{tol:.10f}")
    print("\nResolvendo pelo método de Newton:\n")
    
    x, iterations = newton_method(x0, tol)
    
    if x is not None:
        print("\nResultado final:")
        print(f"Solução: x = [{x[0]:.10f}, {x[1]:.10f}]")
        print(f"F(x) = [{F(x)[0]:.10f}, {F(x)[1]:.10f}]")
        print(f"Número de iterações: {iterations}")

if __name__ == "__main__":
    main()