import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataProviders;

import java.util.Arrays;

public class JavaAlgorithmsTests {

    @Test(groups = {"JavaAlgorithms"}, dataProvider = "anagramCheckerData", dataProviderClass = DataProviders.class)
    public void testAnagramChecker(String firstString, String secondString, boolean expectedResult) {
        boolean actualResult = JavaAlgorithms.isAnagram(firstString, secondString);
        Assert.assertEquals(actualResult, expectedResult, String.format("Result of anagram check on string = %s and string = %s " +
                "isn't as expected.", firstString, secondString));
    }

    @Test(groups = {"JavaAlgorithms"}, dataProvider = "smallestDistanceNeighbourNumbersData", dataProviderClass = DataProviders.class)
    public void testSmallestDistanceNeighbourNumbers(int[] inputArray, int expectedIndex) {
        int actualIndex = JavaAlgorithms.getIndexOfSmallestDistanceNeighbouringNumbers(inputArray);
        Assert.assertEquals(actualIndex, expectedIndex, String.format("Result of smallest distance between neighbour numbers " +
                "on array = %s  isn't as expected.", Arrays.toString(inputArray)));
    }

}
