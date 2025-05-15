import math

def f(x1, x2):
    return 1/2 * ((x1 - 2) ** 2) + ((x2 - 1) ** 2)

def gradiente(x):
    return [(x[0] - 2), 2*(x[1] - 1)]


def norma(vetor):
    return math.sqrt(sum([i**2 for i in vetor]))

def dot_product(v1:list, v2:list):
    res = 0

    for i in range(len(v1)):
        res += v1[i] * v2[i]

    return res

def secao_aurea(x, d, a=0, b=2, toler=1e-4):
    k = 0
    
    while True:
        t1 = a + 0.38*(b-a)
        t2 = a + 0.62*(b-a)
        if f(x[0] + t1 * d[0], x[1] + t1 * d[1]) < f(x[0] + t2 * d[0], x[1] + t2 * d[1]):
            b = t2
        else:
            a = t1

        k += 1

        if b - a <= 2 * toler:
            break
    

    t = (a + b) / 2
    return t


def cauchy(X0:list, toler):
    x = X0[:]
    k = 0
    
    print("\nMétodo de Cauchy")
    print("k".center(5) + "x1".center(15) + "x2".center(15) + "f(x)".center(15) + 
          "g1".center(15) + "g2".center(15) + "d1".center(15) + "d2".center(15) + "tk".center(15))
    print("-" * 120)
    
    # Print initial point
    f_value = f(x[0], x[1])
    g = gradiente(x)
    d = [-g[0], -g[1]]
    print(f"{k:^5}{x[0]:^15.8f}{x[1]:^15.8f}{f_value:^15.8f}{g[0]:^15.8f}{g[1]:^15.8f}{d[0]:^15.8f}{d[1]:^15.8f}{'---':^15}")
    
    while True:
        dk = [-i for i in gradiente(x)]
        tk = secao_aurea(x, dk)
        f_value = f(x[0], x[1])
        g = gradiente(x)
        
        if norma(dk) <= toler:
            break
        
        x = [x[i] + tk * dk[i] for i in range(len(x))]
        k += 1
        print(f"{k:^5}{x[0]:^15.8f}{x[1]:^15.8f}{f_value:^15.8f}{g[0]:^15.8f}{g[1]:^15.8f}{dk[0]:^15.8f}{dk[1]:^15.8f}{tk:^15.8f}")
    
    return x, k

def main():
    x = [1,0]
    toler = 1e-6

    ans, k = cauchy(x, toler)
    
    print("\nResultados finais:")
    print(f"Solução ótima aproximada: x1 = {ans[0]:.8f}, x2 = {ans[1]:.8f}")
    print(f"Número de iterações: {k}")
    print("-" * 120)

if __name__ == "__main__":
    main()