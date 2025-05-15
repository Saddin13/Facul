import numpy as np
import matplotlib.pyplot as plt
import os
from decimal import Decimal, getcontext


# input off prova 3.8
# Clear screen
os.system('cls' if os.name == 'nt' else 'clear')

# Set precision for decimal calculations
getcontext().prec = 100

# Get coefficients from user
while True:
    print("coeficientes padrão: ax⁴ - 12x³ + 47x² - 60x = 0")
    escolha = input("Deseja usar os coeficientes padrão? (s/n): ")
    if escolha == 's':
        a = 1
        b = -12
        c = 47
        d = -60
        e = 0
        break
    print("Entre com os coeficientes: ax⁴ + bx³ + cx² + dx + e = 0")
    while True:
        try:
            a = float(input("Entre com o coeficiente a: "))
            b = float(input("Entre com o coeficiente b: "))
            c = float(input("Entre com o coeficiente c: "))
            d = float(input("Entre com o coeficiente d: "))
            e = float(input("Entre com o coeficiente e: "))
            break
        except ValueError:
            print("entre com valores validos")
        
def f(x):
    """Function f(x) = ax^4 + bx^3 + cx^2 + dx"""
    return a*x**4 + b*x**3 + c*x**2 + d*x + e

def df(x):
    """Derivative f'(x) = 4ax^3 + 3bx^2 + 2cx + d"""
    return 4*a*x**3 + 3*b*x**2 + 2*c*x + d + e

def newton_raphson(x0, tolerance=1e-10, max_iter=100):
    """Newton-Raphson method implementation"""
    x = Decimal(str(x0))
    iterations = 0
    
    for i in range(max_iter):
        fx = Decimal(str(float(f(float(x)))))
        dfx = Decimal(str(float(df(float(x)))))
        
        if abs(dfx) < tolerance:
            print("derivada proxima de 0.")
            return None, iterations
            
        x_new = x - fx/dfx
        iterations += 1
        
        if abs(x_new - x) < tolerance:
            return float(x_new), iterations
            
        x = x_new
    
    print("maximo de iteracoes sem convergencia.")
    return float(x), iterations

x = np.linspace(-1, 6, 1000)
y = f(x)

plt.figure(figsize=(10, 6))
plt.plot(x, y, 'b-', label='f(x)')
plt.axhline(y=0, color='k', linestyle='-', alpha=0.3)
plt.axvline(x=0, color='k', linestyle='-', alpha=0.3)
plt.grid(True, alpha=0.3)
plt.title('f(x) = x⁴ - 12x³ + 47x² - 60x')
plt.xlabel('x')
plt.ylabel('f(x)')
plt.legend()
plt.show()

print("\nEntre com os pontos iniciais (x0) um por um. Digite 'F' quando tiver digitado todos.")
x0_values = []
while True:
    x0_input = input("Entre com o ponto inicial (ou 'F' para finalizar): ") 
    if x0_input.upper() == 'F': 
        break
    try:
        x0_values.append(float(x0_input))
    except ValueError:
        print("Entre com um número válido") 

print("\nCalculando as raízes para cada ponto inicial...")  
for x0 in x0_values:
    root, iterations = newton_raphson(x0)
    if root is not None:
        print(f"x0 = {x0} | Raiz: {root:.10f} | Iterações: {iterations}") 
    else:
        print("Raiz não encontrada.") 