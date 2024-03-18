identificador [a-zA-Z]([a-zA-Z0-9])*
numero [0-9]+
espaco [ \t]+
novalinha [\n]
%x comentario

%%

programa printf("%11s:_palavra_reservada: T_PROGRAMA;\n",yytext);
inicio printf("%11s:_palavra_reservada: T_INICIO;\n",yytext);
fimprograma printf("%11s:_palavra_reservada: T_FIM;\n",yytext);

leia printf("%11s:_palavra_reservada: T_LEIA;\n",yytext);
escreva printf("%11s:T_ESCREVA;\n",yytext);

se printf("%11s:_palavra_reservada: T_SE;\n",yytext);
entao printf("%11s:_palavra_reservada: T_ENTAO;\n",yytext);
senao printf("%11s:_palavra_reservada: T_SENAO;\n",yytext);
fimse printf("%11s:_palavra_reservada: T_FIMSE;\n",yytext);

enquanto printf("%11s:_palavra_reservada: T_ENQUANTO;\n",yytext);
faca printf("%11s:_palavra_reservada: T_FACA;\n",yytext);
fimenquanto printf("%11s:_palavra_reservada: T_FIMENQUANTO;\n",yytext);

"+" printf("%11s:_operador_aritimetico: T_MAIS;\n",yytext);
"-" printf("%11s:_operador_aritimetico: T_MENOS;\n",yytext);
"*" printf("%11s:_operador_aritimetico: T_VEZES;\n",yytext);
div printf("%11s:_operador_aritimetico: T_DIV;\n",yytext);

">" printf("%11s:_operador_relacional: T_MAIOR;\n",yytext);
"<" printf("%11s:_operador_relacional: T_MENOR;\n",yytext);
"=" printf("%11s:_operador_relacional: T_IGUAL;\n",yytext);

e printf("%11s:_operador_logico: T_E;\n",yytext);
ou printf("%11s:_operador_logico: T_OU;\n",yytext);
nao printf("%11s:_operador_logico: T_NAO;\n",yytext);

"<-" printf("%11s:_operador_de_atribuicao: T_ATRIB;\n",yytext);
"(" printf("%11s:_simbolo_fecha_parenteses_esquerdo: T_ABRE;\n",yytext);
")" printf("%11s:_simbolo_fecha_parenteses_direito: T_FECHA;\n",yytext);

inteiro printf("%11s:_palavra_reservada: T_INTEIRO;\n",yytext);
logico printf("%11s:_palavra_reservada: T_LOGICO;\n",yytext);
V printf("%11s:_constante_logica_de_verdade: T_V;\n",yytext);
F printf("%11s:_constante_logica_de_falsidade: T_F;\n",yytext);

{identificador} printf("%11s:_identificador: T_IDENTIF;\n",yytext);
{numero} printf("%11s:_numero: T_NUMERO;\n",yytext);
{espaco} /*nao faz nada*/
{novalinha} /*nao faz nada*/
"//".* /* comentario de linha */ printf("%11s: /*comentario de linha*/\n",yytext);
"/*" BEGIN(comentario);
<comentario>"*/" BEGIN(INITIAL);
<comentario>.    /* nao faz nada */
. printf("%11s:_ERRO_SIMBOLO_NAO_RECONHECIDO!: ERRO LEXICO\n",yytext);

%%

int yywrap(){
    return 1;
}

int main(int argc, char *argv[]){
    yyin = fopen(argv[1], "r");
    yylex();
    fclose(yyin);
    return 0;
}

