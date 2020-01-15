package syntax.command;

import core.Visitor;
import syntax.value.Value;

public class WriteCommand implements Command {
    private Value value;

    public WriteCommand(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        value.accept(visitor);
        visitor.visit(this);
    }
}
