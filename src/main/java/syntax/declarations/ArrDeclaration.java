package syntax.declarations;

import core.Visitor;

public class ArrDeclaration implements Declaration {
    private String arrName;
    private Long startIndex, startName;

    public ArrDeclaration(String arrName, Long startIndex, Long startName) {
        this.arrName = arrName;
        this.startIndex = startIndex;
        this.startName = startName;
    }

    public String getArrName() {
        return arrName;
    }

    public void setArrName(String arrName) {
        this.arrName = arrName;
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
