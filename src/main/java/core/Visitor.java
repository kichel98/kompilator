package core;

import syntax.Program;
import syntax.command.ReadCommand;
import syntax.command.WriteCommand;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.VarDeclaration;
import syntax.identifier.ArrConstIdentifier;
import syntax.identifier.ArrVarIdentifier;
import syntax.identifier.VarIdentifier;
import syntax.value.IdentValue;
import syntax.value.NumValue;

public interface Visitor {
    void preVisit(Program p);
    void postVisit(Program p);

    void visit(VarDeclaration varDeclaration);
    void visit(ArrDeclaration arrDeclaration);

    void visit(VarIdentifier varIdentifier);
    void visit(ArrConstIdentifier arrConstIdentifier);
    void visit(ArrVarIdentifier arrVarIdentifier);

    void visit(IdentValue identValue);
    void visit(NumValue identValue);

    void visit(ReadCommand readCommand);
    void visit(WriteCommand writeCommand);

}
