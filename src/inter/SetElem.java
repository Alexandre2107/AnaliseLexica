package inter;

import lexer.*;
import symbols.*;

public class SetElem extends Stmt {
    public Id array;
    public Expr index;
    public Expr expr;

    public SetElem(Access x, Expr y) {
        array = x.array;
        index = x.index;
        expr = y;
        if (check(x.type, expr.type) == null)
            error("type error");
    }

    public Type check(Type p1, Type p2) {
        if (Type.numeric(p1) && Type.numeric(p2))
            return p2;
        else if (p1 == Type.Bool && p2 == Type.Bool)
            return p2;
        else
            return null;
    }

    public void gen(int b, int a) {
        emit(array.toString() + " = " + expr.gen().toString());
    }
}
