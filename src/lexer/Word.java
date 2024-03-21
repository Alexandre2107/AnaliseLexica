package lexer;

public class Word extends Token {

    public String lexeme = "";

    public Word(String s, int tag) {
        super(tag);
        lexeme = s;
    }

    public String toString() {
        return lexeme;
    }

    public static final Word and = new Word("e", Tag.AND), or = new Word("ou", Tag.OR),
            le = new Word("<", Tag.LE), g = new Word(">", Tag.GREATER),
            minus = new Word("-", Tag.MINUS),
            True = new Word("v", Tag.TRUE),
            False = new Word("f", Tag.FALSE),
            temp = new Word("t", Tag.TEMP),
            plus = new Word("+", Tag.PLUS),
            mult = new Word("*", Tag.MULT),
            lparen = new Word("(", Tag.LPAREN),
            rparen = new Word(")", Tag.RPAREN),
            atribuicao = new Word("<-", Tag.ATRIBUICAO),
            equals = new Word("=", Tag.EQ);
}
