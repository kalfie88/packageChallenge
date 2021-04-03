package utils;

import com.mobiquity.entities.Item;
import com.mobiquity.entities.Pack;
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

    private List<Pack> expectedPacks;

    private List<List<String>> packageResultOne;
    private List<List<String>> packageResultTwo;
    private List<List<String>> packageResultNone;


    @InjectMocks
    FileProcessor fileProcessor;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        //Input tests
        input = "example_input";

        Item i = Item.builder().id(1).weight(53.38).cost(45).build();
        Item i1 = Item.builder().id(2).weight(88.62).cost(98).build();
        Item i2 = Item.builder().id(3).weight(78.48).cost(3).build();
        Item i3 = Item.builder().id(4).weight(72.30).cost(76).build();
        Item i4 = Item.builder().id(5).weight(30.18).cost(9).build();
        Item i5 = Item.builder().id(6).weight(46.34).cost(48).build();

        List<Item> itemList = Arrays.asList(i, i1, i2, i3, i4, i5);
        Pack pack = Pack.builder()
                .capacity(81)
                .itemsToChoose(itemList)
                .build();

        Item j = Item.builder().id(1).weight(85.31).cost(29).build();
        Item j1 = Item.builder().id(2).weight(14.55).cost(74).build();
        Item j2 = Item.builder().id(3).weight(3.98).cost(16).build();
        Item j3 = Item.builder().id(4).weight(26.24).cost(55).build();
        Item j4 = Item.builder().id(5).weight(63.69).cost(52).build();
        Item j5 = Item.builder().id(6).weight(76.25).cost(75).build();
        Item j6 = Item.builder().id(7).weight(60.02).cost(74).build();
        Item j7 = Item.builder().id(8).weight(93.18).cost(35).build();
        Item j8 = Item.builder().id(9).weight(89.95).cost(78).build();

        List<Item> itemList1 = Arrays.asList(j, j1, j2, j3, j4, j5, j6, j7, j8);

        Pack pack1 = Pack.builder()
                .capacity(75)
                .itemsToChoose(itemList1)
                .build();

        Item k = Item.builder().id(1).weight(90.72).cost(13).build();
        Item k1 = Item.builder().id(2).weight(33.80).cost(40).build();
        Item k2 = Item.builder().id(3).weight(43.15).cost(10).build();
        Item k3 = Item.builder().id(4).weight(37.97).cost(16).build();
        Item k4 = Item.builder().id(5).weight(46.81).cost(36).build();
        Item k5 = Item.builder().id(6).weight(48.77).cost(79).build();
        Item k6 = Item.builder().id(7).weight(81.80).cost(45).build();
        Item k7 = Item.builder().id(8).weight(19.36).cost(79).build();
        Item k8 = Item.builder().id(9).weight(6.76).cost(64).build();

        List<Item> itemList2 = Arrays.asList(k, k1, k2, k3, k4, k5, k6, k7, k8);

        Pack pack2 = Pack.builder()
                .capacity(56)
                .itemsToChoose(itemList2)
                .build();

        Item m = Item.builder().id(1).weight(15.3).cost(34).build();

        Pack pack3 = Pack.builder()
                .capacity(8)
                .itemsToChoose(Collections.singletonList(m))
                .build();

        expectedPacks = Arrays.asList(pack, pack3, pack1, pack2);

        //Output tests
        List<String> result = Collections.singletonList("4");
        List<String> result1 = Arrays.asList("2", "7");
        List<String> result2 = Collections.emptyList();

        packageResultOne = Collections.singletonList(result);
        packageResultTwo = Arrays.asList(result, result1);
        packageResultNone = Collections.singletonList(result2);

        expectedOutputOne = new StringBuilder("4").append("\n");
        expectedOutputTwo = new StringBuilder("4").append("\n")
                .append("2,7").append("\n");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processInput() {
        List<Pack> packList = fileProcessor.processInput(input);
        assertEquals(expectedPacks, packList);
    }

    @DisplayName("Process output with 2 results -> successful")
    @Test
    void processOutput_with_two_results() {
        String output = fileProcessor.processOutput(packageResultTwo);
        assertEquals(expectedOutputTwo.toString(), output);
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
        String expected = "-" + "\n";
        String output = fileProcessor.processOutput(packageResultNone);
        assertEquals(expected, output);
    }

}