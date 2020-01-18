package syntax.expression;

import core.Visitor;
import syntax.value.Value;

public class AddExpression implements Expression {
    private Value a, b;
    public AddExpression(Value a, Value b) {
        this.a = a;
        this.b = b;
    }

    public Value getA() {
        return a;
    }

    public void setA(Value a) {
        this.a = a;
    }

    public Value getB() {
        return b;
    }

    public void setB(Value b) {
        this.b = b;
    }

    @Override
    public void accept(Visitor v) {
        b.accept(v);
        v.inVisit(this);
        a.accept(v);
        v.postVisit(this);
    }
}
