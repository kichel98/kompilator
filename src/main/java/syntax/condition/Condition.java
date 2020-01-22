package syntax.condition;

import core.Visitor;
import syntax.SyntaxElement;
import syntax.value.Value;

public abstract class Condition implements SyntaxElement {
    protected Value val1, val2;
    private int type;

    public Condition(Value val1, Value val2, int type) {
        this.val1 = val1;
        this.val2 = val2;
        this.type = type;
    }
    public Value getVal1() {
        return val1;
    }

    public void setVal1(Value val1) {
        this.val1 = val1;
    }

    public Value getVal2() {
        return val2;
    }

    public void setVal2(Value val2) {
        this.val2 = val2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void accept(Visitor visitor) {
        val2.accept(visitor);
        visitor.preVisit(this);
        val1.accept(visitor);
        visitor.inVisit(this);
    }
}
