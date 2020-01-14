package syntax.identifier;

import core.Visitor;

public class ArrVarIdentifier implements Identifier {
    private String arr, varIndex;

    public ArrVarIdentifier(String arr, String varIndex) {
        this.arr = arr;
        this.varIndex = varIndex;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public String getVarIndex() {
        return varIndex;
    }

    public void setVarIndex(String pid2) {
        this.varIndex = varIndex;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
