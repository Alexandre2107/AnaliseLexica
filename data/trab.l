%{
    #include <stdio.h>
%}

%option noyywrap

%{
#define T_PROGRAMA 1
#define T_INICIO 2
#define T_FIM 3
#define T_LEIA 4
#define T_ESCREVA 5
#define T_SE 6
#define T_ENTAO 7
#define T_SENAO 8
#define T_FIMSE 9
#define T_ENQUANTO 10
#define T_FACA 11
#define T_FIMENQUANTO 12
#define T_MAIS 13
#define T_MENOS 14
#define T_VEZES 15
#define T_DIV 16
#define T_MAIOR 17
#define T_MENOR 18
#define T_IGUAL 19
#define T_E 20
#define T_OU 21
#define T_NAO 22
#define T_ATRIB 23
#define T_ABRE 24
#define T_FECHA 25
#define T_INTEIRO 26
#define T_LOGICO 27
#define T_V 28
#define T_F 29
#define T_IDENTIF 30
#define T_NUMERO 31
#define ERRO_LEXICO 32

%}

identificador [a-zA-Z]([a-zA-Z0-9])*
numero [0-9]+
espaco [ \t]+
novalinha [\n]
%x comentario

%%

programa { printf("%11s:_palavra_reservada: T_PROGRAMA;\n",yytext); return T_PROGRAMA; }
inicio { printf("%11s:_palavra_reservada: T_INICIO;\n",yytext); return T_INICIO; }
fimprograma { printf("%11s:_palavra_reservada: T_FIM;\n",yytext); return T_FIM; }

leia { printf("%11s:_palavra_reservada: T_LEIA;\n",yytext); return T_LEIA; }
escreva { printf("%11s:T_ESCREVA;\n",yytext); return T_ESCREVA; }

se { printf("%11s:_palavra_reservada: T_SE;\n",yytext); return T_SE; }
entao { printf("%11s:_palavra_reservada: T_ENTAO;\n",yytext); return T_ENTAO; }
senao { printf("%11s:_palavra_reservada: T_SENAO;\n",yytext); return T_SENAO; }
fimse { printf("%11s:_palavra_reservada: T_FIMSE;\n",yytext); return T_FIMSE; }

enquanto { printf("%11s:_palavra_reservada: T_ENQUANTO;\n",yytext); return T_ENQUANTO; }
faca { printf("%11s:_palavra_reservada: T_FACA;\n",yytext); return T_FACA; }
fimenquanto { printf("%11s:_palavra_reservada: T_FIMENQUANTO;\n",yytext); return T_FIMENQUANTO; }

"+" { printf("%11s:_operador_aritimetico: T_MAIS;\n",yytext); return T_MAIS; }
"-" { printf("%11s:_operador_aritimetico: T_MENOS;\n",yytext); return T_MENOS; }
"*" { printf("%11s:_operador_aritimetico: T_VEZES;\n",yytext); return T_VEZES; }
div { printf("%11s:_operador_aritimetico: T_DIV;\n",yytext); return T_DIV; }

">" { printf("%11s:_operador_relacional: T_MAIOR;\n",yytext); return T_MAIOR; }
"<" { printf("%11s:_operador_relacional: T_MENOR;\n",yytext); return T_MENOR; }
"=" { printf("%11s:_operador_relacional: T_IGUAL;\n",yytext); return T_IGUAL; }

e { printf("%11s:_operador_logico: T_E;\n",yytext); return T_E; }
ou { printf("%11s:_operador_logico: T_OU;\n",yytext); return T_OU; }
nao { printf("%11s:_operador_logico: T_NAO;\n",yytext); return T_NAO; }

"<-" { printf("%11s:_operador_de_atribuicao: T_ATRIB;\n",yytext); return T_ATRIB; }
"(" { printf("%11s:_simbolo_fecha_parenteses_esquerdo: T_ABRE;\n",yytext); return T_ABRE; }
")" { printf("%11s:_simbolo_fecha_parenteses_direito: T_FECHA;\n",yytext); return T_FECHA; }

inteiro { printf("%11s:_palavra_reservada: T_INTEIRO;\n",yytext); return T_INTEIRO; }
logico { printf("%11s:_palavra_reservada: T_LOGICO;\n",yytext); return T_LOGICO; }
V { printf("%11s:_constante_logica_de_verdade: T_V;\n",yytext); return T_V; }
F { printf("%11s:_constante_logica_de_falsidade: T_F;\n",yytext); return T_F; }

{identificador} { printf("%11s:_identificador: T_IDENTIF;\n",yytext); return T_IDENTIF; }
{numero} { printf("%11s:_numero: T_NUMERO;\n",yytext); return T_NUMERO; }
{espaco} /* não faz nada */
{novalinha} /* não faz nada */
"//".* /*comentário de linha*/
"/*" { BEGIN(comentario); }
<comentario>"*/" { BEGIN(INITIAL); }
<comentario>.    /* não faz nada */
. { printf("%11s:_ERRO_SIMBOLO_NAO_RECONHECIDO!: ERRO LEXICO\n",yytext); return ERRO_LEXICO; }
%%

int main(int argc, char *argv[]){
    FILE *yyin;
    int token;
    
    if (argc != 2) {
        printf("Uso: %s <arquivo.txt>\n", argv[0]);
        return 1;
    }

    yyin = fopen(argv[1], "r");
    if (!yyin) {
        printf("Erro ao abrir o arquivo de entrada.\n");
        return 1;
    }
    
    yyrestart(yyin);
    
    while ((token = yylex()) != 0) {
        switch(token) {
            case T_PROGRAMA:
                printf("Tag T_PROGRAMA: %d\n", T_PROGRAMA);
                break;
            case T_INICIO:
                printf("Tag T_INICIO: %d\n", T_INICIO);
                break;
            case T_FIM:
                printf("Tag T_FIM: %d\n", T_FIM);
                break;
            case T_LEIA:
                printf("Tag T_LEIA: %d\n", T_LEIA);
                break;
            case T_ESCREVA:
                printf("Tag T_ESCREVA: %d\n", T_ESCREVA);
                break;
            case T_SE:
                printf("Tag T_SE: %d\n", T_SE);
                break;
            case T_ENTAO:
                printf("Tag T_ENTAO: %d\n", T_ENTAO);
                break;
            case T_SENAO:
                printf("Tag T_SENAO: %d\n", T_SENAO);
                break;
            case T_FIMSE:
                printf("Tag T_FIMSE: %d\n", T_FIMSE);
                break;
            case T_ENQUANTO:
                printf("Tag T_ENQUANTO: %d\n", T_ENQUANTO);
                break;
            case T_FACA:
                printf("Tag T_FACA: %d\n", T_FACA);
                break;
            case T_FIMENQUANTO:
                printf("Tag T_FIMENQUANTO: %d\n", T_FIMENQUANTO);
                break;
            case T_MAIS:
                printf("Tag T_MAIS: %d\n", T_MAIS);
                break;
            case T_MENOS:
                printf("Tag T_MENOS: %d\n", T_MENOS);
                break;
            case T_VEZES:
                printf("Tag T_VEZES: %d\n", T_VEZES);
                break;
            case T_DIV:
                printf("Tag T_DIV: %d\n", T_DIV);
                break;
            case T_MAIOR:
                printf("Tag T_MAIOR: %d\n", T_MAIOR);
                break;
            case T_MENOR:
                printf("Tag T_MENOR: %d\n", T_MENOR);
                break;
            case T_IGUAL:
                printf("Tag T_IGUAL: %d\n", T_IGUAL);
                break;
            case T_E:
                printf("Tag T_E: %d\n", T_E);
                break;
            case T_OU:
                printf("Tag T_OU: %d\n", T_OU);
                break;
            case T_NAO:
                printf("Tag T_NAO: %d\n", T_NAO);
                break;
            case T_ATRIB:
                printf("Tag T_ATRIB: %d\n", T_ATRIB);
                break;
            case T_ABRE:
                printf("Tag T_ABRE: %d\n", T_ABRE);
                break;
            case T_FECHA:
                printf("Tag T_FECHA: %d\n", T_FECHA);
                break;
            case T_INTEIRO:
                printf("Tag T_INTEIRO: %d\n", T_INTEIRO);
                break;
            case T_LOGICO:
                printf("Tag T_LOGICO: %d\n", T_LOGICO);
                break;
            case T_V:
                printf("Tag T_V: %d\n", T_V);
                break;
            case T_F:
                printf("Tag T_F: %d\n", T_F);
                break;
            case T_IDENTIF:
                printf("Tag T_IDENTIF: %d\n", T_IDENTIF);
                break;
            case T_NUMERO:
                printf("Tag T_NUMERO: %d\n", T_NUMERO);
                break;
            case ERRO_LEXICO:
                printf("Tag ERRO_LEXICO: %d\n", ERRO_LEXICO);
                break;
            default:
                printf("Token não reconhecido\n");
                break;
        }
    }

    fclose(yyin);
    return 0;
}
