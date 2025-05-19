#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>

struct jogador
{
    int posicao;
    int dinheiro;
    int preso;
};

struct casa
{
    char nome[17];
    int dono;
    int valor;
};

int comprar(int compra , int jogador ,struct jogador j1 , struct jogador j2 , struct casa tab[40])
{
    if(jogador == 1)
    {
        if (compra == 1)
        {
            if (j1.dinheiro >= 100)
            {
                printf("parabens pela aquisicao");
                return 1;
            }
            else
            {
                printf("voce nao tem o dinheiro suficiente para comprar esta propriedade");
                return 0;
            }
        }
        else
        {
            return 0;
        }
    }
    else
    {
        if (compra == 1)
        {
            if (j2.dinheiro >= 100)
            {
                printf("parabens pela aquisicao");
                return 1;
            }
            else
            {
                printf("voce nao tem o dinheiro suficiente para comprar esta propriedade");
                return 0;
            }
        }
        else
        {
            return 0;
        }
    }
}

int evolucao(int jogador  , struct jogador j1 , struct jogador j2 , struct casa tab[40])
{
    int local;
    if(jogador == 1)
    {
        local = j1.posicao;
        if(j1.dinheiro >= tab[local].valor * 4)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    else
    {
        local = j2.posicao;
        if(j2.dinheiro >= tab[local].valor * 4)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}

int pagamento(int jogador  , struct jogador j1 , struct jogador j2 , struct casa tab[40])
{
    int local;
    if(jogador == 1)
    {
        local = j1.posicao / 3;
        if(j1.dinheiro >= tab[local].valor)
        {
            return 0;
        }
        else
        {
            return 2;
        }
    }
    else
    {
        local = (j2.posicao - 2) / 3;
        if(j2.dinheiro >= tab[local].valor)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
int randomizador() {
    srand((unsigned int)time(NULL));
    return rand() % 6 + 1;
}

void tabuleiro(char casa[119] , struct casa tab[40] , struct jogador j1 , struct jogador j2)
{
    printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n" , tab[0].nome, tab[1].nome, tab[2].nome, tab[3].nome, tab[4].nome,tab[5].nome, tab[6].nome, tab[7].nome, tab[8].nome, tab[9].nome,tab[10].nome);
    printf("%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\n" , casa[0] , casa[1] , casa[2] , casa[3], casa[4], casa[5], casa[6], casa[7], casa[8], casa[9], casa[10], casa[11], casa[12], casa[13], casa[14], casa[15], casa[16], casa[17], casa[18], casa[19], casa[20], casa[21], casa[22], casa[23], casa[24], casa[25], casa[26], casa[27], casa[28], casa[29], casa[30], casa[31], casa[32]);
    printf("\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t\n\n", tab[1].valor, tab[2].valor, tab[3].valor, tab[4].valor, tab[5].valor,tab[6].valor, tab[7].valor, tab[8].valor, tab[9].valor);
    printf("%s\t\t\t\t\t\t\t\t\t\t%s\n", tab[39].nome, tab[11].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" ,   casa[117], casa[118], casa[119], casa[33], casa[34], casa[35]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[39].valor, tab[11].valor);
    printf("%s\t\t\t\t\t\t\t\t\t\t%s\n", tab[38].nome, tab[12].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" , casa[114] , casa[115] , casa[116], casa[36], casa[37], casa[38]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[38].valor, tab[12].valor);
    printf("%s\t\t\t\t\t\t\t\t\t\t%s\n", tab[37].nome, tab[13].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" , casa[111] , casa[112] , casa[113] ,  casa[39] , casa[40] , casa[41] );
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[37].valor, tab[13].valor);
    printf("%s\t\t\t\t\t\t\t\t\t\t%s\n", tab[36].nome, tab[14].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" ,  casa[108] , casa[109] ,   casa[110] ,casa[42] , casa[43] , casa[44]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[36].valor, tab[14].valor);
    printf("%s\t\t\t\t\t\t\t\t\t%s  \n", tab[35].nome, tab[15].nome);
    printf("%c%c%c\t\t\t   MONOPOLY DA TITIA CARMINHA   \t\t\t%c%c%c\n" ,  casa[105] , casa[106] ,   casa[107] ,casa[45], casa[46] , casa[47]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[35].valor, tab[15].valor);
    printf("%s\t\t\t\t\t\t\t\t\t\t%s\n", tab[36].nome, tab[16].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" ,   casa[102] , casa[103] , casa[104] ,  casa[48] , casa[49] , casa[50]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[34].valor, tab[16].valor);
    printf("%s\t\t\t\t\t\t\t\t%s    \n", tab[33].nome, tab[17].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" , casa[99] , casa[100] , casa[101] , casa[51] , casa[52] , casa[53]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[33].valor, tab[17].valor);
    printf("%s\t\t\t\t\t\t\t\t\t%s  \n", tab[32].nome, tab[18].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" ,  casa[96] , casa[97] , casa[98] , casa[54] , casa[55] , casa[56]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[32].valor, tab[18].valor);
    printf("%s\t\t\t\t\t\t\t\t\t\t%s\n", tab[31].nome, tab[19].nome);
    printf("%c%c%c\t\t\t\t\t\t\t\t\t\t%c%c%c\n" , casa[93], casa[94], casa[95] ,  casa[57] , casa[58] , casa[59]);
    printf("%d$\t\t\t\t\t\t\t\t\t\t%d$\n", tab[31].valor, tab[19].valor);
    printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n" , tab[30].nome , tab[29].nome , tab[28].nome , tab[27].nome , tab[26].nome , tab[25].nome , tab[24].nome , tab[23].nome , tab[22].nome , tab[21].nome , tab[20].nome );
    printf("%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\t%c%c%c\n" , casa[90], casa[91], casa[92], casa[87], casa[88], casa[89], casa[84], casa[85], casa[86], casa[81], casa[82], casa[83], casa[78], casa[79], casa[80], casa[75], casa[76], casa[77], casa[72], casa[73], casa[74], casa[69], casa[70], casa[71], casa[66], casa[67], casa[68], casa[63], casa[64], casa[65], casa[60], casa[61], casa[62]);
    printf("\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t%d$\t", tab[29].valor , tab[28].valor , tab[27].valor , tab[26].valor , tab[25].valor , tab[24].valor , tab[23].valor , tab[22].valor , tab[21].valor);
    //AQUI TERMINOU O TABULEIRO, A PARTIR DAQUI FICA A DESCRICAO DO DINHEIRO
    printf("\n\n\n\t\tJOGADOR 1\t\t\t\tJOGADOR 2");
    printf("\n\t\tDinheiro:%d$ \t\t\t\tDinheiro:%d$", j1.dinheiro , j2.dinheiro );
}

int main()
{
    struct casa tab[40];
    {
    strcpy(tab[ 0].nome, "Inicio");
    tab[ 1].dono = 0;
    tab[ 1].valor = 0;
    strcpy(tab[ 1].nome, "Goiania");
    tab[ 1].dono = 0;
    tab[ 1].valor = 100;
    strcpy(tab[ 2].nome, "Vitoria");
    tab[ 2].dono = 0;
    tab[ 2].valor = 100;
    strcpy(tab[ 3].nome, "Manaus");
    tab[ 3].dono = 0;
    tab[ 3].valor = 100;
    strcpy(tab[ 4].nome, "Vitoria");
    tab[ 4].dono = 0;
    tab[ 4].valor = 100;
    strcpy(tab[ 5].nome, "Belem");
    tab[ 5].dono = 0;
    tab[ 5].valor = 100;
    strcpy(tab[ 6].nome, "Aracaju");
    tab[ 6].dono = 0;
    tab[ 6].valor = 100;
    strcpy(tab[ 7].nome, "Natal");
    tab[ 7].dono = 0;
    tab[ 7].valor = 100;
    strcpy(tab[ 8].nome, "Cuiaba");
    tab[ 8].dono = 0;
    tab[ 8].valor = 100;
    strcpy(tab[ 9].nome, "Palmas");
    tab[ 9].dono = 0;
    tab[ 9].valor = 100;
    strcpy(tab[10].nome, "COLOCA UM NOME DE UMA CIDADE DO BRASIL AI FABIO");
    tab[10].dono = 0;
    tab[10].valor = 100;
    strcpy(tab[11].nome, "Brasilia");
    tab[11].dono = 0;
    tab[11].valor = 100;
    strcpy(tab[12].nome, "Cidade do Panama");
    tab[12].dono = 0;
    tab[12].valor = 100;
    strcpy(tab[13].nome, "Buenos Aires");
    tab[13].dono = 0;
    tab[13].valor = 100;
    strcpy(tab[14].nome, "Lima");
    tab[14].dono = 0;
    tab[14].valor = 100;
    strcpy(tab[15].nome, "Santiago");
    tab[15].dono = 0;
    tab[15].valor = 100;
    strcpy(tab[16].nome, "Havana");
    tab[16].dono = 0;
    tab[16].valor = 100;
    strcpy(tab[17].nome, "Quito");
    tab[17].dono = 0;
    tab[17].valor = 100;
    strcpy(tab[18].nome, "Montevideu");
    tab[18].dono = 0;
    tab[18].valor = 100;
    strcpy(tab[19].nome, "Bogota");
    tab[19].dono = 0;
    tab[19].valor = 100;
    strcpy(tab[20].nome, "CAPITAL DA AMERICA LATINA FABIO (PODE SER GUATEMALA SEI LA, ESCOLHE)");
    tab[20].dono = 0;
    tab[20].valor = 100;
    strcpy(tab[21].nome, "Dubai");
    tab[21].dono = 0;
    tab[21].valor = 100;
    strcpy(tab[22].nome, "Milao");
    tab[22].dono = 0;
    tab[22].valor = 100;
    strcpy(tab[23].nome, "Nice");
    tab[23].dono = 0;
    tab[23].valor = 100;
    strcpy(tab[24].nome, "Veneza");
    tab[24].dono = 0;
    tab[24].valor = 100;
    strcpy(tab[25].nome, "Detroit");
    tab[25].dono = 0;
    tab[25].valor = 100;
    strcpy(tab[26].nome, "Meca");
    tab[26].dono = 0;
    tab[26].valor = 100;
    strcpy(tab[27].nome, "Miami");
    tab[27].dono = 0;
    tab[27].valor = 100;
    strcpy(tab[28].nome, "Cancun");
    tab[28].dono = 0;
    tab[28].valor = 100;
    strcpy(tab[29].nome, "Orlando");
    tab[29].dono = 0;
    tab[29].valor = 100;
    strcpy(tab[30].nome, "MAIORES CIDADES NAO CAPITAIS");
    tab[30].dono = 0;
    tab[30].valor = 100;
    strcpy(tab[31].nome, "Toquio");
    tab[31].dono = 0;
    tab[31].valor = 100;
    strcpy(tab[32].nome, "Sao Paulo");
    tab[32].dono = 0;
    tab[32].valor = 100;
    strcpy(tab[33].nome, "Cidade do Mexico");
    tab[33].dono = 0;
    tab[33].valor = 100;
    strcpy(tab[34].nome, "Nova Iorque");
    tab[34].dono = 0;
    tab[34].valor = 100;
    strcpy(tab[35].nome, "Rio de Janeiro");
    tab[35].dono = 0;
    tab[35].valor = 100;
    strcpy(tab[36].nome, "Moscou");
    tab[36].dono = 0;
    tab[36].valor = 100;
    strcpy(tab[37].nome, "Paris");
    tab[37].dono = 0;
    tab[37].valor = 100;
    strcpy(tab[38].nome, "Seul");
    tab[38].dono = 0;
    tab[38].valor = 100;
    strcpy(tab[39].nome, "Londres");
    tab[39].dono = 0;
    tab[39].valor = 100;
    }
    struct jogador j1;
    {
    j1.posicao = 0;
    j1.dinheiro = 3000;
    j1.preso = 0;
    }
    struct jogador j2;
    {
        j2.posicao = 2;
        j2.dinheiro = 3000;
        j2.preso = 0;
    }

    //setando as casas iniciais do tabuleiro
    char casa[119];
    casa[0] = '$';
    casa[1] = '-';
    casa[2] = '&';

    //setando as casas restantes do tabuleiro
    for (int k = 3; k < 120; k++) {
        casa[k] = '-';
    }


    //sets para o ajuste da andada no tabuleiro
    int vitoria = 0;
    int jogador = 1;
    int dado1 = 0  , dado2 = 0 , resultadodado = 0;
    int jogador1 = 0;
    int jogador2 = 0;
    int evolucao1 = 0 , compra1 = 0 , compras1 = 0;
    int evolucao2 = 0 , compra2 = 0 , compras2 = 0;

    //sistema de movimento no tabuleiro
    //jogador 1 = $
    //jogador 2 = &


    while(vitoria == 0)
    {
        if(jogador ==  1)
        {
            printf("aperte enter Jogador 1\n\n");
            tabuleiro(casa,tab,j1,j2);
            getchar();system("cls");;
            dado1=randomizador();
            dado2=randomizador();
            resultadodado = (dado1+dado2) * 3;


            printf("O valor do primeiro da do foi de %d \nO valor do do segundo dado foi %d\nE o resultado da sua jogada foi de %d\n" , dado1 , dado2 , resultadodado/3);
            tabuleiro(casa,tab,j1,j2);
            getchar();system("cls");
            printf("\n\n\n");
            tabuleiro(casa,tab,j1,j2);
            getchar();system("cls");

            jogador1 = resultadodado + j1.posicao;// resultado da casa aonde o jogador ira parar
            if(jogador1 > 117){jogador1 = jogador1 - 120;} // caso a volta no tabuleira seja dada
            while(j1.posicao != jogador1) // quando a jogada for igual a casa aonde ele deve parar para de rodar o codigo
            {
                //caso recomece o tabuleiro
                if(j1.posicao == 117)
                {
                    casa[j1.posicao] = '-';
                    j1.posicao = 0;
                    casa[j1.posicao] = '$';
                    printf("\n\n\n");
                    tabuleiro(casa,tab,j1,j2);
                    getchar();system("cls");
                }
                //jogada normal
                else
                {
                    casa[j1.posicao] = '-';
                    j1.posicao = j1.posicao + 3;
                    casa[j1.posicao] = '$';
                    printf("\n\n\n");
                    tabuleiro(casa,tab,j1,j2);
                    getchar();system("cls");
                }
                if(casa[0] == '$')
                {
                    printf("\n\nvoce foi recompensado em 200$, por dar uma volta ao tabuleiro");
                    tabuleiro(casa,tab,j1,j2);
                    getchar();system("cls");
                    j1.dinheiro = j1.dinheiro + 200;
                }
            }
            system("cls");
            if(casa[j1.posicao + 1] == '-')//compra
            {
                voltar1:
                printf("voce deseja comprar a propriedade em q voce esta localizado?\n\n");
                tabuleiro(casa,tab,j1,j2);
                printf("\n\n\nRESPOSTA: ");
                scanf("%d" , &compra1);
                if(compra1 > 1 || compra1 < 0)
                {
                    goto voltar1;
                }
                compras1 = comprar(compra1 , jogador , j1 ,j2,tab);
                if(compras1 == 1)
                {
                    j1.dinheiro = j1.dinheiro - 100;
                    casa[j1.posicao+1] = '$';
                    tab[j1.posicao / 3].valor = tab[j1.posicao / 3].valor / 2;
                }
                tabuleiro(casa,tab,j1,j2);
            }
            else if(casa[j1.posicao + 1] == '$') //evolucao
            {
                voltar2:
                printf("voce deseja evoluir a sua propriedade?\nela custara 4x o valor da sua propriedade e a propriedade dobrara de preco\n1=SIM / 0=NAO");
                tabuleiro(casa,tab,j1,j2);
                printf("\n\n\nRESPOSTA: ");
                scanf("%d" , &evolucao1);
                if( evolucao1 > 1 || evolucao1 < 0)
                {
                    system("cls");
                    goto voltar2;
                }
                evolucao1 = evolucao(jogador,j1,j2,tab);
                if(evolucao1 == 1)
                {
                    j1.dinheiro = j1.dinheiro - tab[j1.posicao / 3].valor * 4;
                    tab[j1.posicao / 3].valor = tab[j1.posicao / 3].valor * 2;
                }
            }
            else//pagamento 4 outro jogador
            {
                printf("voce tera que fazer o pagamento do aluguel para outro jogador\n\n");
                tabuleiro(casa,tab,j1,j2);
                getchar();
                vitoria = pagamento(jogador,j1,j2,tab);
                j1.dinheiro = j1.dinheiro - tab[j1.posicao].valor;
            }
            jogador++;
        }
        else // jogador 2
        {
            printf("aperte enter Jogador 2\n\n");
            tabuleiro(casa,tab,j1,j2);
            getchar();system("cls");
            dado1=randomizador();
            dado2=randomizador();
            resultadodado = (dado1+dado2) * 3;


            printf("O valor do primeiro da do foi de %d \nO valor do do segundo dado foi %d\nE o resultado da sua jogada foi de %d" , dado1 , dado2 , resultadodado/3);
            getchar();system("cls");
            printf("\n\n\n");
            tabuleiro(casa,tab,j1,j2);
            getchar();system("cls");

            jogador2 = resultadodado + j2.posicao;// resultado da casa aonde o jogador ira parar
            if(jogador2 > 119){jogador2 = jogador2 - 120;} // caso a volta no tabuleira seja dada
            while(j2.posicao != jogador2) // quando a jogada for igual a casa aonde ele deve parar para de rodar o codigo
            {
                //caso recomece o tabuleiro
                if(j2.posicao == 119)
                {
                    casa[j2.posicao] = '-';
                    j2.posicao = 2;
                    casa[j2.posicao] = '&';
                    printf("\n\n\n");
                    tabuleiro(casa,tab,j1,j2);
                    getchar();system("cls");
                }
                //jogada normal
                else
                {
                    casa[j2.posicao] = '-';
                    j2.posicao = j2.posicao + 3;
                    casa[j2.posicao] = '&';
                    printf("\n\n\n");
                    tabuleiro(casa,tab,j1,j2);
                    getchar();system("cls");
                }
                if(casa[2] == '&')
                {
                    printf("voce foi recompensado em 200$, por dar uma volta ao tabuleiro\n\n");
                    tabuleiro(casa,tab,j1,j2);
                    getchar();system("cls");
                    j2.dinheiro = j2.dinheiro + 200;
                }
            }
            system("cls");
            if(casa[j2.posicao - 1] == '-')//compra
            {
            voltar3:
                printf("voce deseja comprar a propriedade em q voce esta localizado?\n\n");
                tabuleiro(casa,tab,j2,j2);
                printf("\n\n\nRESPOSTA: ");
                scanf("%d" , &compra2);
                if(compra2 > 1 || compra2 < 0)
                {
                    goto voltar3;
                }
                compras2 = comprar(compra2 , jogador , j1 ,j2,tab);
                if(compras2 == 1)
                {
                    j2.dinheiro = j2.dinheiro - 100;
                    casa[j2.posicao-1] = '&';
                    tab[j2.posicao / 3].valor = tab[j2.posicao / 3].valor / 2;
                }
                tabuleiro(casa,tab,j1,j2);
            }
            else if(casa[j2.posicao - 1] == '&') //evolucao
            {
            voltar4:
                printf("voce deseja evoluir a sua propriedade?\nela custara 4x o valor da sua propriedade e a propriedade dobrara de preco\n1=SIM / 0=NAO");
                tabuleiro(casa,tab,j1,j2);
                printf("\n\n\nRESPOSTA: ");
                scanf("%d" , &evolucao2);
                if( evolucao2 > 1 || evolucao2 < 0)
                {
                    system("cls");
                    goto voltar4;
                }
                evolucao2 = evolucao(jogador,j1,j2,tab);
                if(evolucao2 == 1)
                {
                    j2.dinheiro = j2.dinheiro - tab[j2.posicao / 3].valor * 4;
                    tab[j2.posicao / 3].valor = tab[j2.posicao / 3].valor * 2;
                }
            }
            else//pagamento 4 outro jogador
            {
                printf("voce tera que fazer o pagamento do aluguel para outro jogador\n\n");
                tabuleiro(casa,tab,j1,j2);
                getchar();
                vitoria = pagamento(jogador,j2,j2,tab);
                j2.dinheiro = j2.dinheiro - tab[j2.posicao].valor;
            }
            jogador--;
        }
    }
    if(vitoria == 1)
    {
        printf("parbens pela vitoria jogador 1\n\n");
        tabuleiro(casa,tab,j1,j2);
    }
    else
    {
        printf("parbens pela vitoria jogador 2\n\n");
        tabuleiro(casa,tab,j1,j2);
    }
}
