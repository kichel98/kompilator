package syntax.command;

import core.Visitor;
import syntax.condition.Condition;

import java.util.List;

public class IfElseCommand implements Command {
    private Condition cond;
    private List<Command> ifCmdList;
    private List<Command> elseCmdList;

    public IfElseCommand(Condition cond, List<Command> ifCmdList, List<Command> elseCmdList) {
        this.cond = cond;
        this.ifCmdList = ifCmdList;
        this.elseCmdList = elseCmdList;
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

    public List<Command> getElseCmdList() {
        return elseCmdList;
    }

    public void setElseCmdList(List<Command> elseCmdList) {
        this.elseCmdList = elseCmdList;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
