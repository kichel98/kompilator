package syntax.value;

import core.Visitor;

public class NumValue implements Value {
    private Long numConst;

    public NumValue(Long numConst) {
        this.numConst = numConst;
    }

    public Long getNumConst() {
        return numConst;
    }

    public void setNumConst(Long numConst) {
        this.numConst = numConst;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
