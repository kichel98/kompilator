package syntax;

import core.Visitor;
import syntax.command.Command;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.Declaration;
import syntax.declarations.VarDeclaration;

import java.util.List;
import java.util.stream.Collectors;

public class Program implements SyntaxElement {
    private List<Declaration> declarations;
    private List<Command> commands;

    public Program(List<Declaration> declarations, List<Command> commands) {
        this.declarations = declarations;
        this.commands = commands;
    }

    public Program(List<Command> commands) {
        this.commands = commands;
    }


    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void accept(Visitor v) {
        if (declarations != null) {
            List<VarDeclaration> varDeclarations = declarations.stream()
                    .filter(declaration -> (declaration instanceof VarDeclaration))
                    .map(declaration -> (VarDeclaration) declaration).collect(Collectors.toList());
            List<ArrDeclaration> arrDeclarations = declarations.stream()
                    .filter(declaration -> (declaration instanceof ArrDeclaration))
                    .map(declaration -> (ArrDeclaration) declaration).collect(Collectors.toList());
            varDeclarations.forEach(varDeclaration -> {
                varDeclaration.accept(v);
            });
            arrDeclarations.forEach(arrDeclaration -> {
                arrDeclaration.accept(v);
            });
        }
        v.preVisit(this);
        commands.forEach(command -> {
            command.accept(v);
        });
        v.postVisit(this);
    }

}
