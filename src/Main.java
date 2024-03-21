
import java.io.*;
import lexer.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Lexer lex = new Lexer();
        Token tok = new Token(0);
        while (tok.tag != 278) {
            tok = lex.scan();

            if (tok.tag == 277 || tok.tag == 283 || tok.tag == 279 || tok.tag == 276 || tok.tag == 278 || tok.tag == 280
                    || tok.tag == 281 || tok.tag == 265 || tok.tag == 284 || tok.tag == 260 || tok.tag == 259
                    || tok.tag == 275) {

                switch (tok.tag) {
                    case 277:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_PROGRAMA ");
                        break;
                    case 283:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_INICIO ");
                        break;
                    case 279:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_FIMENQUANTO ");
                        break;
                    case 276:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_FIMSE ");
                        break;
                    case 278:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_FIM ");
                        break;
                    case 280:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_LEIA ");
                        break;
                    case 281:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_ESCREVA ");
                        break;
                    case 265:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_IF ");
                        break;
                    case 284:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_THEN ");
                        break;
                    case 260:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_ELSE ");
                        break;
                    case 259:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_DO ");
                        break;
                    case 275:
                        System.out.println("\nPalavra Reservada: " + tok.toString() + " / Token: T_WHILE ");
                        break;
                    default:
                        break;
                }

            } else if (tok.tag == 287 || tok.tag == 268 || tok.tag == 291 || tok.tag == 292 || tok.tag == 263
                    || tok.tag == 267
                    || tok.tag == 256 || tok.tag == 271 || tok.tag == 285 || tok.tag == 282 || tok.tag == 40
                    || tok.tag == 41 || tok.tag == 261) {
                switch (tok.tag) {
                    case 287:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_PLUS");
                        break;
                    case 268:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_MINUS");
                        break;
                    case 291:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_MULT");
                        break;
                    case 292:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_DIV");
                        break;
                    case 263:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_GREATER");
                        break;
                    case 267:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_LE");
                        break;
                    case 256:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_AND");
                        break;
                    case 271:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_OR");
                        break;
                    case 285:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_NOT");
                        break;
                    case 282:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_ATRIBUICAO");
                        break;
                    case 40:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_LPAREN");
                        break;
                    case 41:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_RPAREN");
                        break;
                    case 261:
                        System.out.println("\nOperador: " + tok.toString() + " / Token: T_EQUAL");
                    default:
                        break;
                }

            } else if (tok.tag == 264) {
                System.out.println("\nIdentificador: " + tok.toString() + " / Token: T_ID");

            } else if (tok.tag == 270) {
                System.out.println("\nNumero: " + tok.toString() + " / Token: T_NUMERO");

            } else if (tok.tag == 274 || tok.tag == 262) {
                if (tok.tag == 274) {
                    System.out.println("\nConstante: " + tok.toString() + " / Token: T_TRUE");
                } else {
                    System.out.println("\nConstante: " + tok.toString() + " / Token: T_FALSE");
                }

            } else if (tok.toString() == "inteiro" || tok.toString() == "logico") {
                if (tok.toString() == "inteiro") {
                    System.out.println("\nTipo: " + tok.toString() + " / Token: T_INTEIRO");
                } else {
                    System.out.println("\nTipo: " + tok.toString() + " / Token: T_LOGICO");
                }
            }

            if (tok.tag != 13 && tok.tag != 32 && tok.tag != 10) {
                System.out.println("Tag: " + tok.tag);
            }
        }
        System.out.write('\n');
    }

}
