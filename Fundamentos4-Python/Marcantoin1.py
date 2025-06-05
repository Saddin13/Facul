import os

def epsilon():
    e = 1.0
    while 1.0 + e > 1.0:
        e /= 2
    return e * 2

e = epsilon()
os.system('cls' if os.name == 'nt' else 'clear')
print(f"O menor número positivo x tal que 1 + x > 1 é: {e}")