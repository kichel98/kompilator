package core;

import syntax.NumValue;
import syntax.Program;
import syntax.WriteCommand;

public interface Visitor {
    void visit(Program p);
    void preVisit(WriteCommand cmd);
    void postVisit(WriteCommand cmd);
    void visit(NumValue val);
}
