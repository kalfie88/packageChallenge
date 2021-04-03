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

    private static FileProcessor fileProcessor;
    private static PackageSorter packageSorter;

    public static String pack(String filePath) throws APIException {
        List<Pack> pack = fileProcessor.processInput(filePath);
        List<List<Integer>> result = new ArrayList<>();

        pack.forEach(p -> result.add(packageSorter.fillPackage(p)));

        return fileProcessor.processOutput(result);
    }

}
