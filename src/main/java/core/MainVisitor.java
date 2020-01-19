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

import java.io.PrintWriter;

import static core.AssemblerPrinter.*;
import static core.Memory.Register.*;

public class MainVisitor extends Visitor {

    private Memory memory;

    public MainVisitor(PrintWriter writer, Memory memory) {
        new AssemblerPrinter(writer);
        this.memory = memory;
    }

    @Override
    public void preVisit(Program p) {
        setOneRegister();
        setMinusOneRegister();
    }

    @Override
    public void postVisit(Program p) {
        HALT();
    }

    @Override
    public void visit(VarDeclaration varDeclaration) {
        memory.addVarToMemory(varDeclaration);
    }

    @Override
    public void visit(ArrDeclaration arrDeclaration) {
        memory.addArrToMemory(arrDeclaration);
    }

    @Override
    public void visit(VarIdentifier varIdentifier) {
        Long index = memory.getIndexOfVar(varIdentifier.getName());
        generateConstant(index);
    }

    @Override
    public void visit(ArrConstIdentifier arrConstIdentifier) {
        Long index = memory.getIndexOfArrayElement(arrConstIdentifier);
        generateConstant(index);
    }

    @Override
    public void visit(ArrVarIdentifier arrVarIdentifier) {
        Long arrayIndex = memory.getIndexOfArrayStart(arrVarIdentifier);
        generateConstant(arrayIndex);
        STORE(TMP1);
        Long offsetIndex = memory.getIndexOfVar(arrVarIdentifier.getVarIndex());
        LOAD(offsetIndex);
        ADD(TMP1);
    }

    @Override
    public void visit(IdentValue identValue) {
        LOADI(ACC);
    }

    @Override
    public void visit(NumValue numValue) {
        generateConstant(numValue.getNumConst());
    }

    @Override
    public void visit(ReadCommand readCommand) {
        STORE(TMP1);
        GET();
        STOREI(TMP1);
    }

    @Override
    public void visit(WriteCommand writeCommand) {
        PUT();
    }

    @Override
    public void inVisit(AssignCommand assignCommand) {
        STORE(TMP3);
    }

    @Override
    public void postVisit(AssignCommand assignCommand) {
        STOREI(TMP3);
    }

    @Override
    public String inVisit(IfCommand ifCommand) {
        String contentLabel = "ifcntnt" + getLabelCounterAndThenIncrement();
        JPOS(contentLabel);
        String outsideLabel = "ifout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(contentLabel);
        return outsideLabel;
    }

    @Override
    public void postVisit(IfCommand ifCommand, String outsideLabel) {
        printLabel(outsideLabel);
    }

    @Override
    public String firstInVisit(IfElseCommand ifElseCommand) {
        String contentLabel = "ifelcntnt" + getLabelCounterAndThenIncrement();
        JPOS(contentLabel);
        return contentLabel;
    }

    @Override
    public String secondInVisit(IfElseCommand ifElseCommand, String contentLabel) {
        String outsideLabel = "ifelout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(contentLabel);
        return outsideLabel;
    }

    @Override
    public void postVisit(IfElseCommand ifElseCommand, String outsideLabel) {
        printLabel(outsideLabel);
    }

    @Override
    public String[] preVisit(WhileCommand whileCommand) {
        String conditionLabel = "whilecnd" + getLabelCounterAndThenIncrement();
        JUMP(conditionLabel);
        String contentLabel = "whilecntnt" + getLabelCounterAndThenIncrement();
        printLabel(contentLabel);
        return new String[] {conditionLabel, contentLabel};
    }

    @Override
    public String inVisit(WhileCommand whileCommand, String[] labels) {
        printLabel(labels[0]);
        return labels[1];
    }

    @Override
    public void postVisit(WhileCommand whileCommand, String label) {
        JPOS(label);
    }

    @Override
    public String preVisit(DoWhileCommand doWhileCommand) {
        String contentLabel = "dowhilecntnt" + getLabelCounterAndThenIncrement();
        printLabel(contentLabel);
        return contentLabel;
    }

    @Override
    public void postVisit(DoWhileCommand doWhileCommand, String label) {
        JPOS(label);
    }

    @Override
    public void firstInVisit(ForToCommand forToCommand) {
        Long endIndex = memory.getIndexOfVar(forToCommand.getCounter()) + 1;
        STORE(endIndex);
    }

    @Override
    public String[] secondInVisit(ForToCommand forToCommand) {
        Long startIndex = memory.getIndexOfVar(forToCommand.getCounter());
        STORE(startIndex);
        String conditionLabel = "forcond" + getLabelCounterAndThenIncrement();
        JUMP(conditionLabel);
        String contentLabel = "forcntnt" + getLabelCounterAndThenIncrement();
        printLabel(contentLabel);
        INC();
        return new String[] {conditionLabel, contentLabel};
    }

    @Override
    public void postVisit(ForToCommand forToCommand, String[] labels) {
        Long startIndex = memory.getIndexOfVar(forToCommand.getCounter());
        LOAD(startIndex);
        INC();
        STORE(startIndex);
        printLabel(labels[0]);
        Long endIndex = startIndex + 1;
        SUB(endIndex);
        DEC();
        JNEG(labels[1]);
    }

    @Override
    public void firstInVisit(ForDownToCommand forDownToCommand) {
        Long endIndex = memory.getIndexOfVar(forDownToCommand.getCounter()) + 1;
        STORE(endIndex);
    }

    @Override
    public String[] secondInVisit(ForDownToCommand forDownToCommand) {
        Long startIndex = memory.getIndexOfVar(forDownToCommand.getCounter());
        STORE(startIndex);
        String conditionLabel = "forcond" + getLabelCounterAndThenIncrement();
        JUMP(conditionLabel);
        String contentLabel = "forcntnt" + getLabelCounterAndThenIncrement();
        printLabel(contentLabel);
        DEC();
        return new String[] {conditionLabel, contentLabel};
    }

    @Override
    public void postVisit(ForDownToCommand forDownToCommand, String[] labels) {
        Long startIndex = memory.getIndexOfVar(forDownToCommand.getCounter());
        LOAD(startIndex);
        DEC();
        STORE(startIndex);
        printLabel(labels[0]);
        Long endIndex = startIndex + 1;
        SUB(endIndex);
        INC();
        JPOS(labels[1]);
    }

    @Override
    public void inVisit(AddExpression addExpression) {
        STORE(TMP2);
    }

    @Override
    public void postVisit(AddExpression addExpression) {
        ADD(TMP2);
    }

    @Override
    public void inVisit(SubExpression subExpression) {
        STORE(TMP2);
    }

    @Override
    public void postVisit(SubExpression subExpression) {
        SUB(TMP2);
    }

    @Override
    public void preVisit(Condition condition) {
        STORE(TMP2);
    }

    @Override
    public void inVisit(Condition condition) {
        SUB(TMP2);
    }

    @Override
    public void inVisit(EqCondition condition) {
        String setLabel = "eqset" + getLabelCounterAndThenIncrement();
        JZERO(setLabel);
        SUB(0L);
        String outsideLabel = "eqout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(setLabel);
        INC();
        printLabel(outsideLabel);
    }

    @Override
    public void inVisit(NeqCondition condition) {
        String outsideLabel = "neqout" + getLabelCounterAndThenIncrement();
        JZERO(outsideLabel);
        LOAD(ONE);
        printLabel(outsideLabel);
    }

    @Override
    public void inVisit(LeCondition condition) {
        String setLabel = "leset" + getLabelCounterAndThenIncrement();
        JNEG(setLabel);
        SUB(0L);
        String outsideLabel = "leout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(setLabel);
        LOAD(ONE);
        printLabel(outsideLabel);
    }

    @Override
    public void inVisit(GeCondition condition) {
        String setLabel = "geset" + getLabelCounterAndThenIncrement();
        JPOS(setLabel);
        SUB(0L);
        String outsideLabel = "geout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(setLabel);
        LOAD(ONE);
        printLabel(outsideLabel);
    }

    @Override
    public void inVisit(LeqCondition condition) {
        String setLabel = "leqset" + getLabelCounterAndThenIncrement();
        JPOS(setLabel);
        SUB(0L);
        String outsideLabel = "leqout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(setLabel);
        LOAD(ONE);
        printLabel(outsideLabel);
    }

    @Override
    public void inVisit(GeqCondition condition) {
        String setLabel = "geqset" + getLabelCounterAndThenIncrement();
        JNEG(setLabel);
        INC();
        String outsideLabel = "geqout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(setLabel);
        SUB(0L);
        printLabel(outsideLabel);
    }


    private void setOneRegister() {
        SUB(0L);
        INC();
        STORE(ONE);
    }

    private void setMinusOneRegister() {
        SUB(0L);
        DEC();
        STORE(MINUSONE);
    }


    private void generateConstant(Long constant) {
        if (constant > 0) {
            LOAD(ONE);
            Long asmValue = 1L;
            while (asmValue < constant) {
                SHIFT(ONE);
                asmValue *= 2;
            }
            while (asmValue > constant) {
                DEC();
                asmValue--;
            }
        } else if (constant < 0) {
            LOAD(MINUSONE);
            Long asmValue = -1L;
            while (asmValue > constant) {
                SHIFT(ONE);
                asmValue *= 2;
            }
            while (asmValue < constant) {
                INC();
                asmValue++;
            }
        } else {
            SUB(0L);
        }

    }
}