package syntax;

import core.Visitor;

public class AddExpression implements Expression {
    public Value a, b;
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

    }
}
