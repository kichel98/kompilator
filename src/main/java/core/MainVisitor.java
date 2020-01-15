package core;

import syntax.Program;
import syntax.command.ReadCommand;
import syntax.command.WriteCommand;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.VarDeclaration;
import syntax.identifier.VarIdentifier;
import syntax.value.IdentValue;
import syntax.value.NumValue;
import syntax.value.Value;

import java.io.PrintWriter;

import static core.AssemblerPrinter.*;

public class MainVisitor implements Visitor {

    private Memory memory = new Memory();
    private Long temporaryVarPosition = 0L; // ostatnio obliczony indeks pamięci

    public MainVisitor(PrintWriter writer) {
        new AssemblerPrinter(writer);
    }

    @Override
    public void visit(Program p) {
        HALT();
    }

    @Override
    public void visit(VarDeclaration varDeclaration) {
        memory.addVarToMemory(varDeclaration);
    }

    @Override
    public void visit(ArrDeclaration arrDeclaration) {
        // TODO adding array to memory
    }

    @Override
    public void visit(ReadCommand readCommand) {
        GET();
        STORE(temporaryVarPosition);
    }

    @Override
    public void visit(WriteCommand writeCommand) {
        LOAD(temporaryVarPosition);
        PUT();
    }

    @Override
    public void visit(VarIdentifier varIdentifier) {
        /*
            Wyższy element w drzewie (np. WriteCommand) potrzebuje informacji od
            niższego elementu w drzewie (np. Identifier), więc sposobem (słabym)
            na to jest ustawienie zmiennej w Visitorze przez niższy element,
            która chwilę później zostanie odczytana przez element wyżej.

            Ten visit ew. mógłby zamiast ustawiać zmienną po prostu wypisywać swój adres,
            ale później może to trochę utrudnić.
         */
        temporaryVarPosition = memory.getIndexOfIdentifier(varIdentifier.getName());
    }

}
