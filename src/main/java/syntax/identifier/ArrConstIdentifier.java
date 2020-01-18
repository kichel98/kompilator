package syntax.identifier;

import core.Visitor;

public class ArrConstIdentifier extends Identifier {
    private Long numIndex;

    public ArrConstIdentifier(String arr, Long numIndex) {
        super(arr);
        this.numIndex = numIndex;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Long getNumIndex() {
        return numIndex;
    }

    public void setNumIndex(Long numIndex) {
        this.numIndex = numIndex;
    }


}
