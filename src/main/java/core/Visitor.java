package core;

import syntax.Program;
import syntax.command.ReadCommand;
import syntax.command.WriteCommand;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.VarDeclaration;
import syntax.identifier.VarIdentifier;
import syntax.value.IdentValue;

public interface Visitor {
    void visit(Program p);

    void visit(VarDeclaration varDeclaration);
    void visit(ArrDeclaration arrDeclaration);

    void visit(ReadCommand readCommand);
    void visit(WriteCommand writeCommand);

    void visit(VarIdentifier varIdentifier);

}
