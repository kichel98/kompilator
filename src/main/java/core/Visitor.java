package core;

import syntax.Program;
import syntax.command.*;
import syntax.condition.*;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.VarDeclaration;
import syntax.expression.*;
import syntax.identifier.ArrConstIdentifier;
import syntax.identifier.ArrVarIdentifier;
import syntax.identifier.VarIdentifier;
import syntax.value.IdentValue;
import syntax.value.NumValue;

public abstract class Visitor {
    public void preVisit(Program p) {}
    public void postVisit(Program p) {}

    public void visit(VarDeclaration varDeclaration) {}
    public void visit(ArrDeclaration arrDeclaration) {}

    public void visit(VarIdentifier varIdentifier) {}
    public void visit(ArrConstIdentifier arrConstIdentifier) {}
    public void visit(ArrVarIdentifier arrVarIdentifier) {}

    public void visit(IdentValue identValue) {}
    public void visit(NumValue identValue) {}

    public void visit(ReadCommand readCommand) {}
    public void visit(WriteCommand writeCommand) {}
    public void inVisit(AssignCommand assignCommand) {}
    public void postVisit(AssignCommand assignCommand) {}
    public String inVisit(IfCommand ifCommand) { return null; }
    public void postVisit(IfCommand ifCommand, String label) {}
    public String firstInVisit(IfElseCommand ifElseCommand) { return null; }
    public String secondInVisit(IfElseCommand ifElseCommand, String label) { return null; }
    public void postVisit(IfElseCommand ifElseCommand, String label) {}
    public String[] preVisit(WhileCommand whileCommand) { return null; }
    public String inVisit(WhileCommand whileCommand, String[] labels) { return null; }
    public void postVisit(WhileCommand whileCommand, String label) {}
    public String preVisit(DoWhileCommand doWhileCommand) { return null; }
    public void postVisit(DoWhileCommand doWhileCommand, String label) {}
    public void firstInVisit(ForToCommand forToCommand) {}
    public String[] secondInVisit(ForToCommand forToCommand) { return null; }
    public void postVisit(ForToCommand forToCommand, String[] labels) {}
    public void firstInVisit(ForDownToCommand forDownToCommand) {}
    public String[] secondInVisit(ForDownToCommand forDownToCommand) { return null; }
    public void postVisit(ForDownToCommand forDownToCommand, String[] labels) {}


    public void inVisit(AddExpression addExpression) {}
    public void postVisit(AddExpression addExpression) {}
    public void inVisit(SubExpression subExpression) {}
    public void postVisit(SubExpression subExpression) {}
    public void preVisit(MulExpression mulExpression) {}
    public void inVisit(MulExpression mulExpression) {}
    public void postVisit(MulExpression mulExpression) {}
    public void preVisit(DivExpression divExpression) {}
    public void inVisit(DivExpression divExpression) {}
    public void postVisit(DivExpression divExpression) {}
    public void preVisit(ModExpression modExpression) {}
    public void inVisit(ModExpression modExpression) {}
    public void postVisit(ModExpression modExpression) {}

    public void preVisit(Condition condition) {}
    public void inVisit(Condition condition) {}
    public void inVisit(EqCondition condition) {}
    public void inVisit(NeqCondition condition) {}
    public void inVisit(GeCondition condition) {}
    public void inVisit(GeqCondition condition) {}
    public void inVisit(LeCondition condition) {}
    public void inVisit(LeqCondition condition) {}


}
