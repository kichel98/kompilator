package core;

import syntax.value.NumValue;
import syntax.Program;
import syntax.command.WriteCommand;

import java.io.PrintWriter;

public class MainVisitor implements Visitor {
    private PrintWriter writer;

    public MainVisitor(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void visit(Program p) {
        writer.println("# poczatek");
    }

    @Override
    public void preVisit(WriteCommand cmd) {
        writer.print("PUT ");
    }

    @Override
    public void postVisit(WriteCommand cmd) {
        writer.println();
    }

    @Override
    public void visit(NumValue val) {
        writer.print(val.getNumConst());
    }

}
