package core;

import syntax.AddExpression;
import syntax.Program;

public interface Visitor {
    void visit(Program p);

    void visit(AddExpression expr);
}
