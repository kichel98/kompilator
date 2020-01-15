package core;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import syntax.declarations.Declaration;
import syntax.declarations.VarDeclaration;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private Map<String, Pair<Declaration, Long>> memory; // takie średnie to, może inny coś zamiast Declaration
    private Long startIndex, endIndex = 1L;

    public enum Register {
        P0
    }

    public Memory() {
        memory = new HashMap<>();
    }

    public void addVarToMemory(VarDeclaration decl) {
        memory.put(decl.getName(), new MutablePair<>(decl, endIndex++));
    }
    public Long getIndexOfIdentifier(String name) {
        // TODO only for var, make it generic (somehow xd)
        return memory.get(name).getRight();
    }

    public void moveEndIndex() {
        endIndex++;
    }

    public void moveEndIndex(Long offset) {
        endIndex += offset;
    }
}
