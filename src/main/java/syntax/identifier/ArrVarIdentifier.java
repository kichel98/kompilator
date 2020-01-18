package syntax.identifier;

import core.Visitor;

public class ArrVarIdentifier extends Identifier {
    private String varIndex;

    public ArrVarIdentifier(String arr, String varIndex) {
        super(arr);
        this.varIndex = varIndex;
    }

    public String getVarIndex() {
        return varIndex;
    }

    public void setVarIndex(String varIndex) {
        this.varIndex = varIndex;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
