package syntax.identifier;

import core.Visitor;

public class VarIdentifier extends Identifier {

    public VarIdentifier(String var) {
        super(var);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
