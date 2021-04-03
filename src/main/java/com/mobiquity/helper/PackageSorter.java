package com.mobiquity.helper;

import com.mobiquity.entities.Item;
import com.mobiquity.entities.Pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackageSorter {

    private int items;
    private int capacity;
    private int[][] matrix;
    private List<Integer> values;
    private List<Double> weights;


    public List<Integer> fillPackage(Pack pack) {
        initializeValues(pack);
        fillSolutionMatrix();

        return getItems();
    }

    private void initializeValues(Pack pack) {
        capacity = pack.getCapacity();
        items = pack.getItemsToChoose().size();
        matrix = new int[items + 1][capacity + 1];
        values = pack.getItemsToChoose()
                .stream()
                .map(Item::getValue)
                .collect(Collectors.toList());
        weights = pack.getItemsToChoose()
                .stream()
                .map(Item::getWeight)
                .collect(Collectors.toList());
    }

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

    private List<Integer> getItems() {
        int maxValue = matrix[items][capacity];
        int weightCapacity = capacity;
        List<Integer> results = new ArrayList<>();

        for (int i = items; i > 0 && maxValue > 0; i--) {
            if (maxValue != matrix[i - 1][weightCapacity]) {
                results.add(i);
                maxValue -= values.get(i - 1);
                weightCapacity -= weights.get(i - 1).intValue();
            }
        }

        return results;
    }
}
