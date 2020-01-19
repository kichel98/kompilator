package core;

import syntax.command.ForDownToCommand;
import syntax.command.ForToCommand;

public class ForVisitor extends Visitor {
    private Memory memory;

    public ForVisitor(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void firstInVisit(ForToCommand forToCommand) {
        memory.addLocalIteratorToMemory(forToCommand.getCounter());
    }

    @Override
    public void firstInVisit(ForDownToCommand forDownToCommand) {
        memory.addLocalIteratorToMemory(forDownToCommand.getCounter());
    }
}
