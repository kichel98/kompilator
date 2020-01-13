package syntax;

import core.Visitor;

public class VarIdentifier implements Identifier {
    private String pid;

    public VarIdentifier(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
