package syntax;

import core.Visitor;

public interface SyntaxElement {
    void accept(Visitor visitor);
}
