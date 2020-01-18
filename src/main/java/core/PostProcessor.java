package core;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class PostProcessor {
    private Map<String, Integer> labelToCounter;

    public PostProcessor() {
        labelToCounter = new HashMap<>();
    }
    /*
     * Algorytm:
     * 1. Podziel na linie
     * 2. Znajdź etykiety i dodaj je do mapy etykieta -> nr rozkazu (linie z dwukropkiem)
     * 3. Usuń etykiety (do dwukropka + spacja)
     * 4. Znajdź wszystkie JUMPy i zamień etykiety na indeksy z mapy
     */
    public String postProcess(String asmOutput) {
        List<String> lines = asmOutput.lines()
                .map(String::trim)
                .collect(Collectors.toList());
        addLabelToMapAndDeleteFromString(lines);
        replaceLabelsWithNumbers(lines);
        return StringUtils.join(lines, '\n');
    }

    private void addLabelToMapAndDeleteFromString(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (lines.get(i).contains(":")) {
                List<String> parts = new LinkedList<>(Arrays.asList(line.split(":")));
                labelToCounter.put(parts.get(0), i);
                lines.set(i, parts.get(1).trim());
            }
        }
    }

    private void replaceLabelsWithNumbers(List<String> lines) {
        List<String> jumps = Arrays.asList("JUMP", "JPOS", "JZERO", "JNEG");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (jumps.stream().anyMatch(line::contains)) {
                List<String> parts = Arrays.asList(line.split(" "));
                Integer idxOfCommand = labelToCounter.get(parts.get(1));
                parts.set(1, String.valueOf(idxOfCommand));
                lines.set(i, StringUtils.join(parts, " ").trim());
            }
        };
    }
}
