package syntax;

import core.Visitor;

public class IdentValue implements Value {
    private Identifier id;

    public IdentValue(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    @Override
    public void accept(Visitor visitor) {
        
    }
}
