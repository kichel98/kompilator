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
import syntax.value.Value;

import java.io.PrintWriter;

import static core.AssemblerPrinter.*;

public class MainVisitor implements Visitor {

    private Memory memory = new Memory();

    public MainVisitor(PrintWriter writer) {
        new AssemblerPrinter(writer);
    }

    @Override
    public void preVisit(Program p) {
        setOneRegister();
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
        STORE(Memory.Register.TMP1);
        Long offsetIndex = memory.getIndexOfVar(arrVarIdentifier.getVarIndex());
        LOAD(offsetIndex);
        ADD(Memory.Register.TMP1);
    }

    @Override
    public void visit(IdentValue identValue) {
        LOADI(Memory.Register.ACC);
    }

    @Override
    public void visit(NumValue numValue) {
        generateConstant(numValue.getNumConst());
    }

    @Override
    public void visit(ReadCommand readCommand) {
        STORE(Memory.Register.TMP1);
        GET();
        STOREI(Memory.Register.TMP1);
    }

    @Override
    public void visit(WriteCommand writeCommand) {
        PUT();
    }


    private void setOneRegister() {
        SUB(0L);
        INC();
        STORE(Memory.Register.ONE);
    }


    private void generateConstant(Long constant) {
        LOAD(Memory.Register.ONE);
        Long asmValue = 1L;
        while (asmValue < constant) {
            SHIFT(Memory.Register.ONE);
            asmValue *= 2;
        }
        while (asmValue > constant) {
            DEC();
            asmValue--;
        }
    }
}