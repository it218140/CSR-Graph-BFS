package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Summary {

    private HashMap<Integer, Integer> prefixMap = new HashMap<>();            // It will only count the number of targets so i can make the prefix_sum Array
    private ArrayList<Integer> prefix_sum_array = new ArrayList<>();         //1st array of CSR
    private HashMap<Integer, Integer> idToIndex = new HashMap<>();
    private ArrayList<Integer> array_of_targets = new ArrayList<>();         //2nd array of CSR

    public void runGraph(String[] args) {
        Functions functions = new Functions();
        functions.loadFileWithNodes(args);

        functions.getGraph().sort(Elements::compareTo);
        for (Elements e : functions.getGraph()) {
            this.prefixMap.putIfAbsent(e.getSource(), 0);
            prefixMap.put(e.getSource(), prefixMap.get(e.getSource()) + 1);
        }

        for (Elements e : functions.getGraph()) {
            int target = e.getTarget();
            this.array_of_targets.add(target);
        }

        this.prefix_sum_array.add(0, 0);

        int prefix_index = 1;
        for (Map.Entry<Integer, Integer> entry : prefixMap.entrySet()) {
            int source = entry.getKey();
            int value = entry.getValue();

            this.idToIndex.put(source, prefix_index -1);
            prefix_sum_array.add(prefix_index, prefix_sum_array.get(prefix_index - 1) + value);
            prefix_index++;
        }

        functions.searchInGraph(functions, idToIndex, array_of_targets, prefix_sum_array);
    }
}
