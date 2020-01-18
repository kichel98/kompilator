package core;

import syntax.Program;
import syntax.command.*;
import syntax.condition.*;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.VarDeclaration;
import syntax.expression.AddExpression;
import syntax.expression.SubExpression;
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
    void inVisit(AssignCommand assignCommand);
    void postVisit(AssignCommand assignCommand);
    String inVisit(IfCommand ifCommand);
    void postVisit(IfCommand ifCommand, String label);
    String firstInVisit(IfElseCommand ifElseCommand);
    String secondInVisit(IfElseCommand ifElseCommand, String label);
    void postVisit(IfElseCommand ifElseCommand, String label);
    String[] preVisit(WhileCommand whileCommand);
    String inVisit(WhileCommand whileCommand, String[] labels);
    void postVisit(WhileCommand whileCommand, String label);
    String preVisit(DoWhileCommand doWhileCommand);
    void postVisit(DoWhileCommand doWhileCommand, String label);

    void inVisit(AddExpression addExpression);
    void postVisit(AddExpression addExpression);
    void inVisit(SubExpression subExpression);
    void postVisit(SubExpression subExpression);

    void preVisit(Condition condition);
    void inVisit(Condition condition);
    void inVisit(EqCondition condition);
    void inVisit(NeqCondition condition);
    void inVisit(GeCondition condition);
    void inVisit(GeqCondition condition);
    void inVisit(LeCondition condition);
    void inVisit(LeqCondition condition);


}
