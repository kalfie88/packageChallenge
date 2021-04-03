package com.mobiquity.utils;

import com.mobiquity.entities.Pack;
import com.mobiquity.exception.APIException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class FileProcessor {

    public List<Pack> processInput(String file) throws APIException {
        List<Pack> packs = new ArrayList<>();

        try {
            String content = Files.readString(Paths.get(file));
        } catch (IOException e) {
            //log.error();

        }

        return null;
    }

    public String processOutput(List<List<Integer>> result) {
        StringBuilder output = new StringBuilder();

        if (result.isEmpty() || result.get(0).isEmpty())
            return "-";

        result.forEach(s -> output.append(StringUtils.join(s, ",")).append("\n"));

        return output.toString();
    }
}
