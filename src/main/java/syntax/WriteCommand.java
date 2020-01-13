package syntax;

import core.Visitor;

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
        visitor.preVisit(this);
        value.accept(visitor);
        visitor.postVisit(this);
    }
}
