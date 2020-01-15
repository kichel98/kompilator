package syntax.identifier;

import syntax.SyntaxElement;

public abstract class Identifier implements SyntaxElement {
    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
