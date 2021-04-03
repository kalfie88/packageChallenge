package utils;

import com.mobiquity.utils.FileProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileProcessorTest {

    private String input;
    private StringBuilder expectedOutputOne;
    private StringBuilder expectedOutputTwo;

    private List<List<Integer>> packageResultOne;
    private List<List<Integer>> packageResultTwo;
    private List<List<Integer>> packageResultNone;


    @InjectMocks
    FileProcessor fileProcessor;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        input = "example_input";

        List<Integer> result = Collections.singletonList(4);
        List<Integer> result1 = Arrays.asList(2,7);
        List<Integer> result2 = Collections.singletonList(0);

        packageResultOne = Collections.singletonList(result);
        packageResultTwo = Arrays.asList(result, result1);
        packageResultNone = Collections.singletonList(result2);

        expectedOutputOne = new StringBuilder("4").append("\n");
        expectedOutputTwo = new StringBuilder("4");
        expectedOutputTwo.append("\n").append("2,7").append("\n");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processInput() {
    }

    @DisplayName("Process output with 2 results -> successful")
    @Test
    void processOutput_with_two_results() {
       String output = fileProcessor.processOutput(packageResultTwo);
       assertEquals(output, expectedOutputTwo.toString());
    }

    @DisplayName("Process output with 1 results -> successful")
    @Test
    void processOutput_with_one_result() {
        String output = fileProcessor.processOutput(packageResultOne);
        assertEquals(output, expectedOutputOne.toString());
    }

    @DisplayName("Process output with NO results -> successful")
    @Test
    void processOutput_with_no_results() {
        String output = fileProcessor.processOutput(packageResultNone);
        assertEquals("-", output);
    }

}