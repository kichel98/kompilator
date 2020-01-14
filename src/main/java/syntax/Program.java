package syntax;

import core.Visitor;
import syntax.command.Command;
import syntax.declarations.Declaration;

import java.util.List;

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
        v.visit(this);
        commands.forEach(command -> {
            command.accept(v);
        });
    }

}
