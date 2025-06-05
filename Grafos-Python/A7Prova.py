def reverse_edges(adj_matrix):
    n = len(adj_matrix)
    reversed_matrix = [[0 for _ in range(n)] for _ in range(n)]
    
    for i in range(n):
        for j in range(n):
            reversed_matrix[i][j] = adj_matrix[j][i]
    
    return reversed_matrix

def print_matrix(matrix):
    for row in matrix:
        print(row)

def main():
    verices = int(input("Digite o número de vértices: "))
    matrix = [[0 for _ in range(verices)] for _ in range(verices)]
    for i in range(verices):
        for j in range(verices):
            if i >= 1 and j <= verices:
                matrix[i][j] = 1
            else:
                matrix[i][j] = 0
    print("Matriz original:")
    print_matrix(matrix)
    
    reversed_matrix = reverse_edges(matrix)
    print("\nMatriz com arestas reversas:")
    print_matrix(reversed_matrix)

if __name__ == "__main__":
    main()                                                                                                                        # type: ignore