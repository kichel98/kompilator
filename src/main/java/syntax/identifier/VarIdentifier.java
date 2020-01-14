package syntax.identifier;

import core.Visitor;

public class VarIdentifier implements Identifier {
    private String var;

    public VarIdentifier(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
