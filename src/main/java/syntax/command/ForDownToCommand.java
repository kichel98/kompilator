package syntax.command;

import core.Visitor;
import syntax.value.Value;

import java.util.List;

public class ForDownToCommand implements Command {
    private String counter;
    private Value startIndex, endIndex;
    private List<Command> cmdList;

    public ForDownToCommand(String counter, Value startIndex, Value endIndex, List<Command> cmdList) {
        this.counter = counter;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.cmdList = cmdList;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public Value getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Value startIndex) {
        this.startIndex = startIndex;
    }

    public Value getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Value endIndex) {
        this.endIndex = endIndex;
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
