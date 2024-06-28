import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] input1 = {};
        int[] expected1 = {};
        testMySort(input1, expected1);
        
        int[] input2 = {1, 2, 3, 4, 5};
        int[] expected2 = {1, 2, 3, 4, 5};
        testMySort(input2, expected2);

        int[] input3 = {5, 3, 2, 5, 1, 2};
        int[] expected3 = {1, 2, 2, 3, 5, 5};
        testMySort(input3, expected3);

        int[] input4 = {-3, 2, -1, 0, 5, 8, 3, 1, 6, -4, 4, -2, 7};
        int[] expected4 = {-4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        testMySort(input4, expected4);
    }

    public static void testMySort(int[] input, int[] expected) {
        System.out.println("Input: " + Arrays.toString(input));
        int[] sorted = bogusSort(input);
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual: " + Arrays.toString(sorted));
        if (Arrays.equals(sorted, expected)) {
            System.out.println("Test Passed\n");
        } else {
            System.out.println("Test Failed\n");
        }
    }
    
    public static boolean isSorted(int[] nums, int currIndex) {
        // Checks if an array is sorted if current index is greater than the previous
        // Eliminates the need to check the whole array since index is generated 1 by 1
        // Always assumes first element is sorted
        return !(currIndex > 0 && nums[currIndex] < nums[currIndex - 1]);
    }
    
    public static int genRandIndex(int len, ArrayList<Integer> usedIndicies, Random rand, int currIndex) {
        if (currIndex == 0) usedIndicies.clear(); // If current idex was reset, clear used indicies
        int randIndex = 0; // Holds random index from nums
        // Generate random indicies until an unused one is found
        do randIndex = rand.nextInt(len);
        while (usedIndicies.contains(randIndex));
        usedIndicies.add(randIndex); // Add to the list of known indecies
        return randIndex; // Return unused generated index
    }

    public static int[] bogusSort(int[] nums) {
        Random rand = new Random(); // Instansiates object rand from Random()
        ArrayList<Integer> usedIndicies = new ArrayList<>(); // Stores used indicies
        
        int[] numsSorted = new int[nums.length]; // Stores sorted version of nums
        int currIndex = 0; // Tracks current position of numsSorted

        // While the current index of numsSorted is less than the length
        while (currIndex < nums.length) {
            // Stores random index from nums to numsSorted at current index
            numsSorted[currIndex] = nums[genRandIndex(nums.length, usedIndicies, rand, currIndex)];
            
            // If current index is greater than previous, increment and keep going, else, reset
            currIndex = isSorted(numsSorted, currIndex) ? ++currIndex : 0;
        }
        return numsSorted; // Return sorted array
    }
    
}