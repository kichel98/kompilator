package syntax;

import core.Visitor;

public class Program implements SyntaxElement {
    public Expression expr;
    public Program(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        expr.accept(v);
    }
}
