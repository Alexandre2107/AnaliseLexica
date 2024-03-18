package parse;

import java.io.*;
import lexer.*;
import symbols.*;
import inter.*;

public class Parse {
    private Lexer lex;
    private Token look;
    Env top = null;
    int used = 0;

    public Parse(Lexer l) throws IOException {
        lex = l;
        move();
    }

    void move() throws IOException {
        look = lex.scan();
    }

    void error(String s) {
        throw new Error("near line " + lex.line + "+s");
    }

    void match(int t) throws IOException {
        if (look.tag == t)
            move();
        else
            error("syntax error");
    }

    public void program() throws IOException {
        Stmt s = block();
        int begin = s.newlabel();
        int after = s.newlabel();
        s.emitlabel(begin);
        s.gen(begin, after);
        s.emitlabel(after);
    }

    Stmt block() throws IOException {
        match('{');
        Env savedEnv = top;
        top = new Env(top);
        decls();
        Stmt s = stmts();
        match('{');
        top = savedEnv;
        return s;
    }

    private void decls() throws IOException {
        while (look.tag == Tag.BASIC) {
            Type p = type();
            Token tok = look;
            match(Tag.ID);
            match(';');
            Id id = new Id((Word) tok, p, used);
            top.put(tok, id);
            used = used + p.width;
        }
    }

    Type type() throws IOException {
        Type p = type();
        match(Tag.BASIC);
        if (look.tag != '[')
            return p;
        else
            return dims(p);
    }

    Type dims(Type p) throws IOException {
        match('[');
        Token tok = look;
        match(Tag.NUM);
        match(']');
        if (look.tag == '[')
            p = dims(p);
        return new Array(((Num) tok).value, p);
    }

    Stmt stmts() throws IOException {
        if (look.tag == '}')
            return Stmt.Null;
        else
            return new Seq(stmts(), stmts());
    }
}
