package syntax.declarations;

import core.Visitor;

public class ArrDeclaration extends Declaration {
    private Long startIndex, endIndex;

    public ArrDeclaration(String arrName, Long startIndex, Long endIndex) {
        super(arrName);
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Long endIndex) {
        this.endIndex = endIndex;
    }
}
