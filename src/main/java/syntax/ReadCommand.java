package syntax;

import core.Visitor;

public class ReadCommand implements Command {
    private Identifier id;

    public ReadCommand(Identifier id) {
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
