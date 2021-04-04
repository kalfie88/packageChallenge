package com.mobiquity.packer;

import com.mobiquity.entities.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.helper.PackageSorter;
import com.mobiquity.utils.FileProcessor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Packer {

    /**
     * Entry of the solution, this method is in charge of processing the inputs
     * and outputs and call the class responsible of running the algorithm
     *
     * @param filePath test file in UTF-8 format
     * @return solution as a String
     * @throws APIException if fileName is incorrect
     */
    public static String pack(String filePath) throws APIException {
        FileProcessor fileProcessor = new FileProcessor();
        PackageSorter packageSorter = new PackageSorter();
        List<List<String>> result = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (filePath.isEmpty() || !path.isAbsolute())
            throw new APIException("File path is invalid");

        List<Pack> packs = fileProcessor.processInput(path);
        packs.forEach(pack -> result.add(packageSorter.fillPackage(pack)));

        return fileProcessor.processOutput(result);
    }

}
