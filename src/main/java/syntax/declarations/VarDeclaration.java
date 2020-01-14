package syntax.declarations;

import core.Visitor;

public class VarDeclaration implements Declaration {
    private String varName;

    public VarDeclaration(String varName) {
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
