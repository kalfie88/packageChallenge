package com.mobiquity.packer;

import com.mobiquity.entities.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.helper.PackageSorter;
import com.mobiquity.utils.FileProcessor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Packer {

    private static final FileProcessor fileProcessor = new FileProcessor();
    private static final PackageSorter packageSorter = new PackageSorter();

    /**
     * Entry of the solution, this method is in charge of processing the inputs
     * and outputs and call the class responsible of running the algorithm
     *
     * @param filePath test file in UTF-8 format
     * @return solution as a String
     * @throws APIException if fileName is incorrect
     */
    public static String pack(String filePath) throws APIException {
        if (filePath == null || filePath.isEmpty())
            throw new APIException("File name should not be empty");

        List<List<String>> result = new ArrayList<>();
        List<Pack> packs = fileProcessor.processInput(filePath);
        packs.forEach(pack -> result.add(packageSorter.fillPackage(pack)));

        return fileProcessor.processOutput(result);
    }

}
