package syntax.command;

import core.Visitor;
import syntax.expression.Expression;
import syntax.identifier.Identifier;

public class AssignCommand implements Command {
    private Identifier id;
    private Expression expr;

    public AssignCommand(Identifier id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
