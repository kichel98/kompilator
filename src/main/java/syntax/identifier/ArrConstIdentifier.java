package syntax.identifier;

import core.Visitor;

public class ArrConstIdentifier implements Identifier {
    private String arr;
    private Long numIndex;

    public ArrConstIdentifier(String arr, Long numIndex) {
        this.arr = arr;
        this.numIndex = numIndex;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public Long getNumIndex() {
        return numIndex;
    }

    public void setNumIndex(Long num) {
        this.numIndex = numIndex;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
