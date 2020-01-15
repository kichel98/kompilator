package syntax.declarations;

import core.Visitor;

public class ArrDeclaration extends Declaration {
    private Long startIndex, startName;

    public ArrDeclaration(String arrName, Long startIndex, Long startName) {
        super(arrName);
        this.startIndex = startIndex;
        this.startName = startName;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getStartName() {
        return startName;
    }

    public void setStartName(Long startName) {
        this.startName = startName;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
