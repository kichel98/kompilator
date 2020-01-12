package core;

import syntax.AddExpression;
import syntax.Program;

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
    public void visit(AddExpression expr) {
        writer.println(expr.a + " ADD " + expr.b);
    }
}
