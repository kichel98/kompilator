package core;

import syntax.value.NumValue;
import syntax.Program;
import syntax.command.WriteCommand;

public interface Visitor {
    void visit(Program p);
    void preVisit(WriteCommand cmd);
    void postVisit(WriteCommand cmd);
    void visit(NumValue val);
}
