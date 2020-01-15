package syntax.declarations;

import core.Visitor;

public class VarDeclaration extends Declaration {
    public VarDeclaration(String varName) {
        super(varName);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
