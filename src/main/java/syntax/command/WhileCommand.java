package syntax.command;

import core.Visitor;
import syntax.condition.Condition;

import java.util.List;

public class WhileCommand implements Command {
    private Condition cond;
    private List<Command> cmdList;

    public WhileCommand(Condition cond, List<Command> cmdList) {
        this.cond = cond;
        this.cmdList = cmdList;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public List<Command> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Command> cmdList) {
        this.cmdList = cmdList;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
