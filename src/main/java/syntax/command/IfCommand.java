package syntax.command;

import core.Visitor;
import syntax.condition.Condition;

import java.util.List;

public class IfCommand implements Command {
    private Condition cond;
    private List<Command> ifCmdList;

    public IfCommand(Condition cond, List<Command> ifCmdList) {
        this.cond = cond;
        this.ifCmdList = ifCmdList;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public List<Command> getIfCmdList() {
        return ifCmdList;
    }

    public void setIfCmdList(List<Command> ifCmdList) {
        this.ifCmdList = ifCmdList;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
