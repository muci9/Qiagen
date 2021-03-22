package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "anagramCheckerData", parallel = true)
    public static Object[][] getAnagramCheckerData() {
        return new Object[][] {
                // firstString, secondString, expected result
                {"      ", "", true},
                {"Ca    b", "bAc", true},
                {"A Rope Ends It", "Desperation", true},
                {"anagram", "not an anagram", false}
        };
    }

    @DataProvider(name = "smallestDistanceNeighbourNumbersData", parallel = true)
    public static Object[][] getSmallestDistanceNeighbourData() {
        int[][] arrayNumbersData = {
                {1},
                {1, 1001},
                {4, 8, 6, 1, 2, 9, 4},
                {5, 10, 15, 20},
                {20, 10, 15, 5, 0}
        };

        return new Object[][] {
                // input array, expected result index
                {arrayNumbersData[0], -1},
                {arrayNumbersData[1], 0},
                {arrayNumbersData[2], 3},
                {arrayNumbersData[3], 0},
                {arrayNumbersData[4], 3}
        };
    }
}
