numero digit [0-9]+
identificador letra [A-Za-z]([a-zA-Z0-9])*

%{
  int count = 0
%}

%%
 {identificador} printf ("cont = %d\n", ++count);
 %%

 int yywrap(void){
  return 1;
 }

 int main(int argc, char *argv[]) {
  //yyin = fopen(argv[1], "r");
  yylex();
  //fclose(yyin);
  return 0; 
 }