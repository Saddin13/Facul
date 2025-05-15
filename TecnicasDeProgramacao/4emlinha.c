#include <stdio.h>
#include <stdlib.h>

void matriz(char marca[6][7])
{
    int i = 0 , j = 0;
    printf("\t1\t2\t3\t4\t5\t6\t7\n\n");
    while(i < 6)
    {
        while(j < 7)
        {
            printf("\t%c", marca[i][j]);
            j++;
        }
        printf("\n");
        i++;
        j=0;
    }
}

void jogador1(char marca[6][7])
{
    int i , j = 5;
    printf("\n\tqual a coluna a qual o JOGADOR1 quer jogar??\t");
    scanf("%d" , &i);
    printf("\n");
    i = i-1;
    if(i < 0 || i > 6)
    {
        printf("\tjogue em uma coluna aceitavel! ENTRE 0 E 6??\t");
        scanf("%d" , &i);
        printf("\n");
        i = i-1;
    }
    else
    {
        while(j > -1)
            if(marca[j][i] != '-')
            {
                --j;
                if (j == 0)
                {
                    printf("\tjogue em uma coluna aceitavel! ENTRE 0 E 6??\t");
                    scanf("%d" , &i);
                    printf("\n");
                    i = i-1;
                    j = -1;
                }
            }
            else
            {
                marca[j][i] = '1';
                j=-1;
            }
    }
}

void jogador2(char marca[6][7])
{
    int i , j = 5;
    printf("\n\tqual a coluna a qual o JOGADOR2 quer jogar??\t");
    scanf("%d" , &i);
    printf("\n");
    i = i - 1;
    if(i < 0 || i > 6)
    {
        printf("\tjogue em uma coluna aceitavel! ENTRE 0 E 6??\t");
        scanf("%d" , &i);
        printf("\n");
        i = i - 1;
    }
    else
    {
        while(j > -2)
            if(marca[j][i] != '-')
            {
                --j;
                if (j == -1)
                {
                    printf("\tjogue em uma coluna aceitavel! ENTRE 0 E 6??\t");
                    scanf("%d" , &i);
                    printf("\n");
                    i = i - 1;
                    j = -1;
                }
            }
            else
            {
                marca[j][i] = '2';
                j=-1;
            }
    }
}

int verificador(char marcador[6][7])
{
    int i , j;
    for ( i = 0; i < 6; i++)
    {
        for ( j = 0; j < 4; j++)
        {
            if (marcador[i][j] == '1' &&
                marcador[i][j + 1] == '1' &&
                marcador[i][j + 2] == '1' &&
                marcador[i][j + 3] == '1')
            {
                return 1;
            }
            if (marcador[i][j] == '2' &&
                marcador[i][j + 1] == '2' &&
                marcador[i][j + 2] == '2' &&
                marcador[i][j + 3] == '2')
            {
                return 2;
            }
        }
    }

    for ( i = 0; i < 3; i++)
    {
        for ( j = 0; j < 7; j++)
        {
            if (marcador[i][j] == '1' &&
                marcador[i + 1][j] == '1' &&
                marcador[i + 2][j] == '1' &&
                marcador[i + 3][j] == '1')
            {
                return 1;
            }
            if (marcador[i][j] == '2' &&
                marcador[i + 1][j] == '2' &&
                marcador[i + 2][j] == '2' &&
                marcador[i + 3][j] == '2')
            {
                return 2;
            }
        }
    }

    for ( i = 0; i < 3; i++)
    {
        for ( j = 0; j < 4; j++)
        {
            if (marcador[i][j] == '1' &&
                marcador[i + 1][j + 1] == '1' &&
                marcador[i + 2][j + 2] == '1' &&
                marcador[i + 3][j + 3] == '1')
            {
                return 1;
            }
            if (marcador[i][j] == '2' &&
                marcador[i + 1][j + 1] == '2' &&
                marcador[i + 2][j + 2] == '2' &&
                marcador[i + 3][j + 3] == '2')
            {
                return 2;
            }
        }
    }

    for ( i = 0; i < 3; i++)
    {
        for ( j = 3; j < 7; j++)
        {
            if (marcador[i][j] == '1' &&
                marcador[i + 1][j - 1] == '1' &&
                marcador[i + 2][j - 2] == '1' &&
                marcador[i + 3][j - 3] == '1')
            {
                return 1;
            }
            if (marcador[i][j] == '2' &&
                marcador[i + 1][j - 1] == '2' &&
                marcador[i + 2][j - 2] == '2' &&
                marcador[i + 3][j - 3] == '2')
            {
                return 2;
            }
        }
    }

    return 0;
}

int main()
{
    char marcador[6][7];
    
    int i = 0;
    int j = 0;
    
    for (i = 0; i < 6; i++)
    {
        for (j = 0; j < 7; j++)
        {
            marcador[i][j] = '-';
        }
    }

    matriz(marcador);
    int jogador = 1;
    int vitoria = 0;

    while (vitoria == 0)
    {
        if (jogador == 1)
        {
            jogador1(marcador);
            jogador = 2;
        }
        else
        {
            jogador2(marcador);
            jogador = 1;
        }

        system("cls");
        matriz(marcador);
        vitoria = verificador(marcador);

        if (vitoria == 1)
        {
            printf("\nJogador 1 venceu!\n");
        }
        if (vitoria == 2)
        {
            printf("\nJogador 2 venceu!\n");
        }
    }
}
