digit [0-9]
letter [A-Za-z]
%{
    int count;
%}
%%
{letter}({letter}|{digit})* count++;
%%

int yywrap(void){
    return 1;
}

int main(int argc, char *argv[]) {
    yyin = fopen(argv[1], "r");
    yylex();
    printf("number of identifiers = %d\n", count);
    fclose(yyin);
    return 0;
}