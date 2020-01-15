package syntax.declarations;

import syntax.SyntaxElement;

public abstract class Declaration implements SyntaxElement {
    protected String name;

    public Declaration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
