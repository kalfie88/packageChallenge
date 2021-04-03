package com.mobiquity.helper;

import com.mobiquity.entities.Item;
import com.mobiquity.entities.Pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PackageSorter {

    private int items;
    private int capacity;
    private int[][] matrix;
    private List<Integer> values;
    private List<Double> weights;


    /**
     * Entry method, this will kick off the algorithm
     *
     * @param pack Pack to sort
     * @return List of the items allowed to go in the package
     */
    public List<String> fillPackage(Pack pack) {
        if (pack == null || pack.getItemsToChoose().isEmpty())
            return Collections.emptyList();

        initializeValues(pack);
        fillSolutionMatrix();

        return getItems();
    }

    /**
     * Method to fill out our variables
     *
     * @param pack Pack to be sorted
     */
    private void initializeValues(Pack pack) {
        capacity = pack.getCapacity();
        items = pack.getItemsToChoose().size();

        matrix = new int[items + 1][capacity + 1];
        values = pack.getItemsToChoose()
                .stream()
                .map(Item::getCost)
                .collect(Collectors.toList());
        weights = pack.getItemsToChoose()
                .stream()
                .map(Item::getWeight)
                .collect(Collectors.toList());
    }

    /**
     * First step of the algorithm, we are filling the matrix with the possible
     * solutions using button up dynamic programming approach O(n*C)
     */
    private void fillSolutionMatrix() {
        for (int i = 0; i <= capacity; i++)
            matrix[0][i] = 0;

        for (int i = 1; i <= items; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (weights.get(i - 1) > j)
                    matrix[i][j] = matrix[i - 1][j];
                else
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - weights.get(i - 1).intValue()] + values.get(i - 1));
            }
        }
    }

    /**
     * Second step on the algorithm, after we have our matrix filled
     * we need to traverse it so we can obtain the items we used to
     * get to that solution
     *
     * @return list of items that go in the package
     */
    private List<String> getItems() {
        int maxValue = matrix[items][capacity];
        int weightCapacity = capacity;
        List<String> results = new ArrayList<>();

        for (int i = items; i > 0 && maxValue > 0; i--) {
            if (maxValue != matrix[i - 1][weightCapacity]) {
                results.add(String.valueOf(i));
                maxValue -= values.get(i - 1);
                weightCapacity -= weights.get(i - 1).intValue();
            }
        }

        Collections.sort(results);
        return results;
    }
}
