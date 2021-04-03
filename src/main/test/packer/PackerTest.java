package packer;

import com.mobiquity.entities.Item;
import com.mobiquity.entities.Pack;
import com.mobiquity.exception.APIException;
import com.mobiquity.helper.PackageSorter;
import com.mobiquity.packer.Packer;
import com.mobiquity.utils.FileProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
class PackerTest {

    @Mock
    PackageSorter packageSorter;

    @Mock
    FileProcessor fileProcessor;

    private Pack pack;
    private Pack pack1;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        //(1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74)
        // (8,93.18,€35) (9,89.95,€78)
        Item i = Item.builder().id(1).weight(85.31).value(29).build();
        Item i1 = Item.builder().id(2).weight(14.55).value(74).build();
        Item i2 = Item.builder().id(3).weight(3.98).value(16).build();
        Item i3 = Item.builder().id(4).weight(26.24).value(55).build();
        Item i4 = Item.builder().id(5).weight(63.69).value(52).build();
        Item i5 = Item.builder().id(6).weight(76.25).value(75).build();
        Item i6 = Item.builder().id(7).weight(60.02).value(74).build();
        Item i7 = Item.builder().id(8).weight(93.18).value(35).build();
        Item i8 = Item.builder().id(9).weight(89.95).value(78).build();

        List<Item> list = Arrays.asList(i, i1, i2, i3, i4, i5, i6, i7, i8);
        pack = Pack.builder().capacity(75).itemsToChoose(list).build();

        //(1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
        Item j = Item.builder().id(1).weight(53.38).value(45).build();
        Item j1 = Item.builder().id(2).weight(88.62).value(98).build();
        Item j2 = Item.builder().id(3).weight(78.48).value(3).build();
        Item j3 = Item.builder().id(4).weight(72.30).value(76).build();
        Item j4 = Item.builder().id(5).weight(30.18).value(9).build();
        Item j5 = Item.builder().id(6).weight(46.34).value(48).build();

        List<Item> list1 = Arrays.asList(j, j1, j2, j3, j4, j5);
        pack1 = Pack.builder().capacity(81).itemsToChoose(list1).build();

    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Packer run successful")
    @Test
    void pack() throws APIException {
        String path = "example_input";
        String expectedOutput = "example";
        List<Integer> result = Arrays.asList(7,2);
        List<Integer> result1 = Collections.singletonList(4);
        List<Pack> listPacks = Arrays.asList(pack, pack1);
        List<List<Integer>> listResults = Arrays.asList(result, result1);

        when(fileProcessor.processInput(path)).thenReturn(listPacks);
        when(packageSorter.fillPackage(pack)).thenReturn(result);
        when(fileProcessor.processOutput(listResults)).thenReturn(expectedOutput);
        String output = Packer.pack(path);

        assertEquals(output, expectedOutput);
    }
}