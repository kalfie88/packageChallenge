package com.mobiquity.utils;

import com.mobiquity.entities.Item;
import com.mobiquity.entities.Pack;
import com.mobiquity.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FileProcessor {

    /**
     * Method to process the input, so reads the file line by line,
     * and then maps the packs on the list.
     *
     * @param path absolute path to a file
     * @return list of packs to sort
     */
    public List<Pack> processInput(Path path) {
        try (Stream<String> stream = Files.lines(path)) {

            List<String> list = stream.collect(Collectors.toList());

            return mapPacks(list);

        } catch (IOException e) {
            log.error("An error occurred when reading the input file", e);

        }

        return Collections.emptyList();
    }

    /**
     * This method will split the list we got from the file
     * and map the values to the Pack object
     *
     * @param list list of lines from the file
     * @return list of Packs
     */
    @NotNull
    private List<Pack> mapPacks(List<String> list) {
        List<Pack> packs = new ArrayList<>();

        if (list.isEmpty())
            return Collections.emptyList();

        for (String line : list) {
            String[] splitLine = line.split(":"); // 0 -> capacity 1-> rest
            String temp = splitLine[1].trim();
            String[] items = temp.split(" "); //each item within ()

            Pack newPack = Pack.builder()
                    .capacity(Integer.parseInt(splitLine[0].trim()))
                    .itemsToChoose(mapItems(items))
                    .build();

            packs.add(newPack);
        }

        return packs;
    }

    /**
     * This method will decompose and clean the items to be able
     * to map them to the object Item
     *
     * @param items each item within ()
     * @return list of clean Items
     */
    @NotNull
    private List<Item> mapItems(String[] items) {
        List<Item> itemList = new ArrayList<>();
        if (items.length == 0)
            return Collections.emptyList();

        for (String item : items) {
            item = item.replaceAll("[()|€]", " "); //Without () and €
            String[] splitItem = item.split(","); //clean items

            Item newItem = Item.builder()
                    .id(Integer.parseInt(splitItem[0].trim()))
                    .weight(Double.parseDouble(splitItem[1].trim()))
                    .cost(Integer.parseInt(splitItem[2].trim()))
                    .build();

            itemList.add(newItem);
        }

        return itemList;
    }

    /**
     * This method will map the output from the list
     * to the String that will contain the results
     *
     * @param result solution of the packageSorter
     * @return output of the API
     */
    public String processOutput(@NotNull List<List<String>> result) {
        StringBuilder output = new StringBuilder();

        if (result.isEmpty())
            return "";

        result.stream()
                .filter(Objects::nonNull)
                .forEach(index -> {
                    if (index.isEmpty()) {
                        output.append("-").append("\n");
                    } else {
                        output.append(StringUtils.join(index, ",")).append("\n");
                    }
                });

        return output.toString();
    }
}
