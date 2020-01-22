package syntax.expression;

import core.Visitor;
import syntax.value.Value;

public class ModExpression implements Expression {
    private Value a, b;
    public ModExpression(Value a, Value b) {
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
        v.preVisit(this);
        b.accept(v);
        v.inVisit(this);
        a.accept(v);
        v.postVisit(this);
    }
}
