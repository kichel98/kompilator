package syntax;

import core.Visitor;

public class AddExpression extends Expression {
    public int a, b;
    public AddExpression(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
