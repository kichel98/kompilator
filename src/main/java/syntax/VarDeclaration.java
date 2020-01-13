package syntax;

import core.Visitor;

public class VarDeclaration implements Declaration {
    private String pid;

    public VarDeclaration(String pid) {
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
