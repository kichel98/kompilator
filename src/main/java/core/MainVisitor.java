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
        generateConstantFaster(index);
    }

    @Override
    public void visit(ArrConstIdentifier arrConstIdentifier) {
        Long index = memory.getIndexOfArrayElement(arrConstIdentifier);
        generateConstantFaster(index);
    }

    @Override
    public void visit(ArrVarIdentifier arrVarIdentifier) {
        Long arrayIndex = memory.getIndexOfArrayStart(arrVarIdentifier);
        generateConstantFaster(arrayIndex);
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
        generateConstantFaster(numValue.getNumConst());
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
        return new String[]{conditionLabel, contentLabel};
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
        return new String[]{conditionLabel, contentLabel};
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
        return new String[]{conditionLabel, contentLabel};
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
    public void preVisit(MulExpression mulExpression) {
        SUB(0L);
        STORE(MUL1);
        STORE(MUL2);
    }

    @Override
    public void inVisit(MulExpression mulExpression) {
        STORE(TMP2);
    }

    @Override
    public void postVisit(MulExpression mulExpression) {
        /*
            n - TMP1
            m - TMP2
            ans - MUL1
            count - MUL2
         */
        STORE(TMP1);
        LOAD(TMP2);
//        STORE(TMP2);

        String whileCnd = "whilecnd" + getLabelCounterAndThenIncrement();
        String whileCntnt = "whilecntnt" + getLabelCounterAndThenIncrement();
        JUMP(whileCnd);
        printLabel(whileCntnt);
        /*
         środek pętli
        */
            LOAD(TMP2);
            SHIFT(MINUSONE);
            SHIFT(ONE);
            SUB(TMP2);

            String ifCntnt = "ifcntnt" + getLabelCounterAndThenIncrement();
            JNEG(ifCntnt);
            String ifOutside = "ifout" + getLabelCounterAndThenIncrement();
            JUMP(ifOutside);
            printLabel(ifCntnt);
                /* if start */
                LOAD(TMP1);
                SHIFT(MUL2);
                ADD(MUL1);
                STORE(MUL1);
                /* if end*/
            printLabel(ifOutside);

            LOAD(MUL2);
            INC();
            STORE(MUL2);
            LOAD(TMP2);
            SHIFT(MINUSONE);
            STORE(TMP2);

        /*
        koniec pętli
         */
        printLabel(whileCnd);
        JPOS(whileCntnt);

        LOAD(MUL1);

    }

    @Override
    public void preVisit(DivExpression divExpression) {
        SUB(0L);
        STORE(MUL1);
        STORE(MUL2);
    }

    @Override
    public void inVisit(DivExpression divExpression) {
        STORE(TMP2);
    }

    @Override
    public void postVisit(DivExpression divExpression) {
        executeDivisionAlgorithm();

    }

    @Override
    public void preVisit(ModExpression modExpression) {
        SUB(0L);
        STORE(MUL1);
        STORE(MUL2);
    }

    @Override
    public void inVisit(ModExpression modExpression) {
        STORE(TMP2);
    }

    @Override
    public void postVisit(ModExpression modExpression) {
        executeDivisionAlgorithm();
        LOAD(TMP1);
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
        LOAD(ONE);
        String outsideLabel = "leqout" + getLabelCounterAndThenIncrement();
        JUMP(outsideLabel);
        printLabel(setLabel);
        SUB(0L);
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

    @Deprecated
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

    private void generateConstantFaster(Long constant) {
        String binConst = Long.toBinaryString(constant);

        SUB(0L);

        char[] charArray = binConst.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '1') {
                INC();
            }
            if (i != charArray.length - 1)
                SHIFT(ONE);
        }
    }

    private void executeDivisionAlgorithm() {
        STORE(TMP1);

        LOAD(TMP2);
        String divZero = "divzero" + getLabelCounterAndThenIncrement();
        JZERO(divZero);

        String bSgnCntnt = "bsgncntnt" + getLabelCounterAndThenIncrement();
        JPOS(bSgnCntnt);
        STORE(DIV2);
        SUB(0L);
        SUB(DIV2);
        STORE(TMP2);
        LOAD(MINUSONE);
        STORE(DIV2);
        String outBSgn = "outbsgn" + getLabelCounterAndThenIncrement();
        JUMP(outBSgn);
        printLabel(bSgnCntnt);
        LOAD(ONE);
        STORE(DIV2);
        printLabel(outBSgn);

        LOAD(TMP1);
        String aSgnCntnt = "asgncntnt" + getLabelCounterAndThenIncrement();
        JPOS(aSgnCntnt);
        STORE(DIV1);
        SUB(0L);
        SUB(DIV1);
        STORE(TMP1);
        LOAD(MINUSONE);
        STORE(DIV1);
//        STORE(TMP2);
        String outASgn = "outasgn" + getLabelCounterAndThenIncrement();
        JUMP(outASgn);
        printLabel(aSgnCntnt);
        LOAD(ONE);
        STORE(DIV1);
        printLabel(outASgn);




        String whileCnd = "whilecnd" + getLabelCounterAndThenIncrement();
        JUMP(whileCnd);
        String whileCntnt = "whilecntnt" + getLabelCounterAndThenIncrement();
        printLabel(whileCntnt);
        INC();
        LOAD(MUL1);
        INC();
        STORE(MUL1);
        printLabel(whileCnd);
        LOAD(TMP2);
        SHIFT(MUL1);
        SUB(TMP1);
        DEC();
        JNEG(whileCntnt);

        LOAD(MUL1);
        DEC();
        STORE(MUL1);
        String forCnd = "forcnd" + getLabelCounterAndThenIncrement();
        JUMP(forCnd);
        String forCntnt = "forcntnt" + getLabelCounterAndThenIncrement();
        printLabel(forCntnt);
        LOAD(TMP2);
        SHIFT(MUL1);
        STORE(MUL3);
        LOAD(TMP1);
        SUB(MUL3);
        String ifCntnt = "ifcntnt" + getLabelCounterAndThenIncrement();
        INC();
        JPOS(ifCntnt);
        DEC();
        LOAD(MUL2);
        SHIFT(ONE);
        STORE(MUL2);
        String ifOut = "ifout" + getLabelCounterAndThenIncrement();
        JUMP(ifOut);
        printLabel(ifCntnt);
        DEC();
        STORE(TMP1);
        LOAD(MUL2);
        INC();
        SHIFT(ONE);
        STORE(MUL2);
        printLabel(ifOut);
        LOAD(MUL1);
        DEC();
        STORE(MUL1);
        printLabel(forCnd);
        INC();
        JPOS(forCntnt);

        // naprawa po ostatnim obiegu pętli
        LOAD(MUL2);
        SHIFT(MINUSONE);
        STORE(MUL2);

        LOAD(DIV1);
        String div1PosCntnt = "div1poscntnt" + getLabelCounterAndThenIncrement();
        JPOS(div1PosCntnt);
            /* zewnętrzny else */
            LOAD(DIV2);
            String div2PosCntnt2 = "div2poscntnt2" + getLabelCounterAndThenIncrement();
            JPOS(div2PosCntnt2);
                /* wewnętrzny else */
                LOAD(TMP1);
                SUB(0L);
                SUB(TMP1);
                STORE(TMP1);

            String div2Out2 = "div2out2" + getLabelCounterAndThenIncrement();
            JUMP(div2Out2);
            printLabel(div2PosCntnt2);
                /* wewnętrzny if */
                LOAD(MUL2);
                SUB(0L);
                SUB(MUL2);
                DEC();
                STORE(MUL2);

                LOAD(TMP2);
                SUB(TMP1);
                STORE(TMP1);

            printLabel(div2Out2);
        String div1Out = "div1out" + getLabelCounterAndThenIncrement();
        JUMP(div1Out);
        printLabel(div1PosCntnt);
            /* zewnętrzny if */
            LOAD(DIV2);
            String div2PosCntnt1 = "div2PosCntnt1" + getLabelCounterAndThenIncrement();
            JPOS(div2PosCntnt1);
                /* wewnętrzny else */
                LOAD(MUL2);
                SUB(0L);
                SUB(MUL2);
                DEC();
                STORE(MUL2);

                LOAD(TMP2);
                SUB(TMP1);
                STORE(TMP1);
                SUB(0L);
                SUB(TMP1);
                STORE(TMP1);
            String div2Out1 = "div2out1" + getLabelCounterAndThenIncrement();
            JUMP(div2Out1);
            printLabel(div2PosCntnt1);
                /* wewnętrzny if */

            printLabel(div2Out1);

        printLabel(div1Out);


        LOAD(MUL2);


        String outDivZero = "outdivzero" + getLabelCounterAndThenIncrement();
        JUMP(outDivZero);
        printLabel(divZero);
        STORE(TMP1);
        STORE(MUL2);
        printLabel(outDivZero);
    }
}