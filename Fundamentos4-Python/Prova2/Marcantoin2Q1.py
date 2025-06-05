import cmath
import os
import decimal
from decimal import Decimal
decimal.getcontext().prec = 60

os.system('cls' if os.name == 'nt' else 'clear')
print("Calculadora de Equação do Segundo Grau (ax² + bx + c)")
print("coeficientes padrão:  1e-8x² - 0.8x + 1e-8= 0")
escolha = input("Deseja usar os coeficientes padrão? (s/n): ")

if escolha == 's':
    # Entrada dos coeficientes padrão
    a = Decimal('1e-8')
    b = Decimal('-0.8')
    c = Decimal('1e-8')
else:
    # Entrada dos coeficientes
    a = Decimal(input("Digite o valor de a: "))
    b = Decimal(input("Digite o valor de b: "))
    c = Decimal(input("Digite o valor de c: "))

delta = b**2 - 4*a*c

if a == 0:
    print("Não é uma equação do segundo grau! 'a' não pode ser zero.")
elif delta < 0:
    print("A equação não possui raízes reais.")
    print(f"o valor de delta é {delta}")
elif delta == 0:
    raiz = -b / (2*a)
    print(f"A equação possui uma raiz real: x = {raiz}")
else:
    raiz1 = (-b + delta.sqrt()) / (2*a)
    raiz2 = (-b - delta.sqrt()) / (2*a)
    print(f"A equação possui duas raízes reais:")
    print(f"x1 = {raiz1}")
    print(f"x2 = {raiz2}")