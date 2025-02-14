package core;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import syntax.declarations.ArrDeclaration;
import syntax.declarations.Declaration;
import syntax.declarations.VarDeclaration;
import syntax.identifier.ArrConstIdentifier;
import syntax.identifier.ArrVarIdentifier;
import syntax.identifier.Identifier;
import syntax.identifier.VarIdentifier;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private Map<String, Pair<Declaration, Long>> memory;
    private Long endIndex = (long) Register.values().length; // pierwszy niepusty element


    public enum Register {
        ACC(0L),
        TMP1(1L),
        TMP2(2L),
        TMP3(3L),
        ONE(4L),
        MINUSONE(5L),
        MUL1(6L),
        MUL2(7L),
        MUL3(8L),
        DIV1(9L),
        DIV2(10L);

        Long idx;

        private Register(Long idx) {
            this.idx = idx;
        }
    }

    public Memory() {
        memory = new HashMap<>();
    }

    public void addVarToMemory(VarDeclaration decl) {
        memory.put(decl.getName(), new MutablePair<>(decl, endIndex++));
    }
    public void addLocalIteratorToMemory(String counterName) {
        VarDeclaration counterDecl = new VarDeclaration(counterName);
        if (!memory.containsKey(counterName)) { // jeśli taka sama nazwa już była, to nic nie musimy robić, jakby "nadpisujemy"
            memory.put(counterName, new MutablePair<>(counterDecl, endIndex));
            endIndex += 2; // robimy miejsce na zapisanie końca iteracji (value po "TO")
        }
    }
    public void addArrToMemory(ArrDeclaration decl) {
        memory.put(decl.getName(), new MutablePair<>(decl, endIndex));
        endIndex += decl.getEndIndex() - decl.getStartIndex() + 1;
    }


    public Long getIndexOfVar(String varName) {
        return memory.get(varName).getRight();
    }
    public Long getIndexOfArrayElement(ArrConstIdentifier id) {
        Long index = memory.get(id.getName()).getRight();
        // tab(stała) w pamięci będzie na miejscu tab.idx + stała - tab.pierwszy_index
        return index + id.getNumIndex() - ((ArrDeclaration) (memory.get(id.getName()).getLeft())).getStartIndex();
    }

    public Long getIndexOfArrayStart(ArrVarIdentifier id) {
        Long index = memory.get(id.getName()).getRight();
        return index - ((ArrDeclaration) (memory.get(id.getName()).getLeft())).getStartIndex();
    }

}
