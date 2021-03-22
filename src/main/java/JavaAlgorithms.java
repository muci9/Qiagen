import java.util.HashMap;

public class JavaAlgorithms {


    /**
     *
     * @param firstString - input string that will be checked if it's an anagram of secondString, represents a phrase
     *                    or word made out of letters from the English alphabet and whitespaces
     * @param secondString - input string that will be checked if it's an anagram of firstString, represents a phrase
     *                    or word made out of letters from the English alphabet and whitespaces
     * @return true if the input strings are anagrams of each other, false
     * otherwise
     *
     * This method determines if two strings are anagrams of each other. The algorithm will calculate the
     * frequency of the letters used in each string and compare those. If they match, the strings are anagrams
     * of each other. Whitespaces and the capitalization of letters is ignored.
     * and
     */
    public static boolean isAnagram(String firstString, String secondString) {
        // initialize the arrays used for frequency counting
        // each position corresponds to a letter from the English alphabet
        // e.g. 0 - a, 1 - b, 2 - c etc.
        int[] firstStringLetterFrequencies = new int[26];
        int[] secondStringLetterFrequencies = new int[26];

        for (int index = 0; index < 26; index++) {
            firstStringLetterFrequencies[index] = 0;
            secondStringLetterFrequencies[index] = 0;
        }

        // count the frequency of each letter in the first string, capitalization and whitespaces are ignored
        char[] firstStringChars = firstString.toCharArray();
        for (char firstStringChar : firstStringChars) {
            if (!Character.isWhitespace(firstStringChar)) {
                int currentCharacterIndex = Character.toLowerCase(firstStringChar) - 97;
                firstStringLetterFrequencies[currentCharacterIndex]++;
            }
        }

        // count the frequency of each letter in the second string, capitalization and whitespaces are ignored
        char[] secondStringChars = secondString.toCharArray();
        for (char secondStringChar : secondStringChars) {
            if (!Character.isWhitespace(secondStringChar)) {
                int currentCharacterIndex = Character.toLowerCase(secondStringChar) - 97;
                secondStringLetterFrequencies[currentCharacterIndex]++;
            }
        }

        // compare the frequency arrays to determine if the strings are anagrams of each other
        for (int index = 0; index < 26; index++) {
            if (firstStringLetterFrequencies[index] != secondStringLetterFrequencies[index])
                return false;
        }
        return true;
    }

    /**
     *
     * @param inputArray - array of integers
     * @return resultIndex, index of the first number in the inputArray, -1 if the array consists of only one element
     * or less (there are no neighbouring numbers)
     *
     * This method determines the index of the first number of two neighbouring numbers with the smallest distance between
     * them. E.g. In the sequence 4 8 6 1 2 9 4 the minimum distance is 1 (between 1 and 2). The function return the
     * index 3 (of number 1)
     *
     */
    public static int getIndexOfSmallestDistanceNeighbouringNumbers(int[] inputArray) {
        int resultIndex = -1, resultIndexDistance, currentIndexDistance;

        // if there is only one number or no numbers there are no neighbouring numbers
        if (inputArray.length <= 1) {
            return resultIndex;
        }

        // the resultIndex is calculated by traversing the inputArray and verifying for the smallest distance between
        // neighbouring number(smallest absolute difference)
        resultIndex = 0;
        resultIndexDistance = Math.abs(inputArray[resultIndex] - inputArray[resultIndex + 1]);
        for (int index = 1; index < inputArray.length - 1; index++) {
            currentIndexDistance = Math.abs(inputArray[index] - inputArray[index + 1]);
            // if a smaller distance is found between neighbouring numbers, the resultIndex and resultIndexDistance are updated
            if (currentIndexDistance < resultIndexDistance) {
                resultIndexDistance = Math.abs(inputArray[resultIndex] - inputArray[resultIndex + 1]);
                resultIndex = index;
            }
        }
        return resultIndex;
    }


}
