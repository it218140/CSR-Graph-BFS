package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Functions {

    private ArrayList<Elements> graph = new ArrayList<>();

    public ArrayList<Elements> getGraph() {
        return graph;
    }

    public void loadFileWithNodes(String[] args) {
        try {
            String path = args[0];
            BufferedReader objReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = objReader.readLine()) != null) {
                if (line.charAt(0) == '#')
                    continue;

                String[] t = line.split("\\s");
                int Source = Integer.parseInt(t[0]);
                int Target = Integer.parseInt(t[1]);
                Elements el = new Elements(Source, Target);
                this.graph.add(el);  //creating an element and adding it to my arraylist

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchInGraph(Functions functions, HashMap<Integer, Integer> idToIndex,
                              ArrayList<Integer> array_of_targets, ArrayList<Integer> prefix_sum_array) {
        long startTime = System.currentTimeMillis();
        Scanner myScanner = new Scanner(System.in);

        int source, target, initial_target;
        while(true) {
            System.out.println("source : ");
            source = myScanner.nextInt();
            while (!idToIndex.containsKey(source)) {
                System.out.println("the value you inserted is not included as a source in the graph");
                source = myScanner.nextInt();
            }
            System.out.println("target : ");
            target = myScanner.nextInt();
            while (!array_of_targets.contains(target)) {
                System.out.println("the value you inserted is not included as a target in the graph");
                target = myScanner.nextInt();
            }

            initial_target = target;

            int pathSize = 0;
            while (target != source) {
                target = functions.BFS(source, target, array_of_targets, prefix_sum_array, idToIndex);
                if (target == -1) {
                    System.out.println("There is no path between source : " + source + " and target : " + initial_target);
                    return;
                }
                pathSize++;
            }
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;

            System.out.println("THERE IS A PATH BETWEEN " + source + " --> "+ initial_target);
            System.out.println("THE SIZE OF THE PATH IS  : " + pathSize);
            System.out.println("TIME ELAPSED IN MILLISECONDS : " + timeElapsed);
            System.out.println();
        }
    }

    public ArrayList<Integer> get_neighbors(int id, ArrayList<Integer> targets, ArrayList<Integer> prefixSum, HashMap<Integer, Integer> hashMap) {
        ArrayList<Integer> neighbor_list = new ArrayList<>();
        if (hashMap.containsKey(id))
        {
            int key = hashMap.get(id);
            for (int n = prefixSum.get(key); n < prefixSum.get(key + 1); n++) {
                neighbor_list.add(targets.get(n));
            }
        }
        return neighbor_list;
    }

    public int BFS(int s, int target, ArrayList<Integer> targets, ArrayList<Integer> prefixSum, HashMap<Integer, Integer> hashMap) {
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>(); // Create a queue for bfs

        visited.add(s);
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();

            for (int i : get_neighbors(s,targets,prefixSum,hashMap)) {
                if (i == target)
                    return s;
                if (!visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return -1;
    }
}
