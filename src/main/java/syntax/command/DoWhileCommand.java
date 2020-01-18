package syntax.command;

import core.Visitor;
import syntax.condition.Condition;

import java.util.List;

public class DoWhileCommand implements Command {
    private List<Command> cmdList;
    private Condition cond;

    public DoWhileCommand(List<Command> cmdList, Condition cond) {
        this.cmdList = cmdList;
        this.cond = cond;
    }

    public List<Command> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Command> cmdList) {
        this.cmdList = cmdList;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    @Override
    public void accept(Visitor visitor) {
        String label = visitor.preVisit(this);
        cmdList.forEach(cmd -> cmd.accept(visitor));
        cond.accept(visitor);
        visitor.postVisit(this, label);
    }
}
