package syntax;

import core.Visitor;

public class NumValue implements Value {
    private int num;

    public NumValue(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
