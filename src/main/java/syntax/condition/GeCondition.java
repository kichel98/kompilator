package syntax.condition;

import core.Visitor;
import syntax.value.Value;

public class GeCondition extends Condition {
    public GeCondition(Value val1, Value val2, int type) {
        super(val1, val2, type);
    }

    @Override
    public void accept(Visitor visitor) {
        super.accept(visitor);
        val1.accept(visitor);
        visitor.inVisit(this);

    }
}
