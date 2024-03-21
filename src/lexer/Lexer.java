package lexer;

import java.io.*;
import java.util.*;
import symbols.*;

public class Lexer {

    public static int line = 0;
    char peek = ' ';

    Hashtable words = new Hashtable();

    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    public Lexer() {
        reserve(new Word("se", Tag.IF));
        reserve(new Word("e", Tag.AND));
        reserve(new Word("ou", Tag.OR));
        reserve(new Word("nao", Tag.NOT));
        reserve(new Word("senao", Tag.ELSE));
        reserve(new Word("fimse", Tag.FIMSE));
        reserve(new Word("entao", Tag.THEN));
        reserve(new Word("enquanto", Tag.WHILE));
        reserve(new Word("fimenquanto", Tag.FIMENQUANTO));
        reserve(new Word("faca", Tag.DO));
        reserve(new Word("programa", Tag.PROGRAMA));
        reserve(new Word("inicio", Tag.INICIO));
        reserve(new Word("fimprograma", Tag.FIM));
        reserve(new Word("leia", Tag.LEIA));
        reserve(new Word("escreva", Tag.ESCREVA));
        reserve(new Word("logico", Tag.LOGICO));
        reserve(new Word("inteiro", Tag.INTEIRO));
        reserve(new Word("<-", Tag.ATRIBUICAO));
        reserve(new Word("V", Tag.TRUE));
        reserve(new Word("F", Tag.FALSE));
        reserve(new Word("div", Tag.DIV));
        reserve(new Word("(", Tag.LPAREN));
        reserve(new Word(")", Tag.RPAREN));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);
    }

    void readch() throws IOException {
        peek = (char) System.in.read();
    }

    boolean readch(char c) throws IOException {
        readch();
        if (peek != c) {
            return false;
        }
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        for (;; readch()) {
            if (peek == ' ' || peek == '\t') {
                continue;
            } else if (peek == '\n') {
                line = line + 1;
            } else {
                break;
            }
        }
        switch (peek) {
            case '+':
                if (readch(' ')) {
                    return Word.plus;
                } else {
                    return new Token('+');
                }
            case '-':
                if (readch(' ')) {
                    return Word.minus;
                } else {
                    return new Token('-');
                }
            case '<':
                if (readch(' ')) {
                    return Word.le;
                } else {
                    return new Token('<');
                }
            case '>':
                if (readch(' ')) {
                    return Word.g;
                } else {
                    return new Token('>');
                }
            case '*':
                if (readch(' ')) {
                    return Word.mult;
                } else {
                    return new Token('*');
                }
            case '/':
                if (readch('*')) {
                    // inicio comentario de bloco ignorar tudo ate o fim do comentario e nao ler
                    // nada e retornar nada
                    while (true) {
                        readch();
                        if (peek == '*') {
                            if (readch('/')) {
                                break;
                            }
                        }
                    }
                } else if (readch('/')) {
                    // inicio comentario de linha ignorar tudo ate o fim da linha e nao ler nada e retornar nada
                    while (true) {
                        readch();
                        if (peek == '\n' || peek == '\r' || peek == '\0' || peek == '\f' || peek == '\b' || peek == '\t') {
                            break;
                        }
                    }
                }
        }
        if (Character.isDigit(peek))

        {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));
            if (peek != '.') {
                return new Num(v);
            }
            float x = v;
            float d = 10;
            for (;;) {
                readch();
                if (!Character.isDigit(peek)) {
                    break;
                }
                x = x + Character.digit(peek, 10) / d;
                d = d * 10;
            }
            return new Real(x);
        }
        if (Character.isLetter(peek)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));
            String s = b.toString();
            Word w = (Word) words.get(s);
            if (w != null) {
                return w;
            }
            w = new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }
        Token tok = new Token(peek);
        peek = ' ';
        return tok;
    }
}
