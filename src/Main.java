
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
                System.out.println("\nPalavra Reservada: " + tok.toString());
            } else if (tok.tag == 287 || tok.tag == 268 || tok.tag == 291 || tok.tag == 292 || tok.tag == 263
                    || tok.tag == 267
                    || tok.tag == 256 || tok.tag == 271 || tok.tag == 285 || tok.tag == 282 || tok.tag == 293
                    || tok.tag == 294) {
                System.out.println("\nOperador: " + tok.toString());
            } else if (tok.tag == 264) {
                System.out.println("\nIdentificador: " + tok.toString());
            } else if (tok.tag == 270) {
                System.out.println("\nNumero: " + tok.toString());

            } else if (tok.tag == 274 || tok.tag == 262) {
                System.out.println("\nConstante: " + tok.toString());

            } else if (tok.toString() == "inteiro" || tok.toString() == "logico") {
                System.out.println("\nTipo: " + tok.toString());
            }

            if (tok.tag != 13 && tok.tag != 32) {
                System.out.println("Tag: " + tok.tag);
            }
        }
        System.out.write('\n');
    }

}
