import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplementaryPair {

	/**
	 * Finds the pairs of numbers which sum equals a given number
	 * 
	 * @param numbers array of numbers
	 * @param k target number
	 * @return bidimensional array with pairs of indexes that match the criteria
	 */
	public static int[][] findKComplementaryPairs(int[] numbers, int k) {
		if (numbers == null) return new int[][]{};
		
		Map<Integer, List<Integer>> numberIndexes = mapNumberWithIndexes(numbers);		
		List<List<Integer>> complementaryPairs = findComplementaryPairs(numbers, k, numberIndexes);	
		
		return convertToArray(complementaryPairs);
	}

	/**
	 * Extracts the indexes associated with each number in the array.
	 * For example, the array [1,1,2,3,2] would return (1)->(0,1), (2)->(2,4), (3)->(3)
	 * @param numbers
	 * @return map with a number as key, and a list of indexes where said number appears as value
	 */
	private static Map<Integer, List<Integer>> mapNumberWithIndexes(int[] numbers) {
		Map<Integer, List<Integer>> numberIndexes = new HashMap<>();
		for (int i = 0; i < numbers.length; i++) {
			int n = numbers[i];
			if (!numberIndexes.containsKey(n)) {
				numberIndexes.put(n, new ArrayList<Integer>());
			}
			numberIndexes.get(n).add(i);
		}
		return numberIndexes;
	}
	
	/**
	 * Finds the complementary pairs using an indexes map to avoid searching the whole number array
	 * @param numbers
	 * @param k
	 * @param numberIndexes
	 * @return
	 */
	private static List<List<Integer>> findComplementaryPairs(int[] numbers, int k,
			Map<Integer, List<Integer>> numberIndexes) {
		List<List<Integer>> complementaryPairs = new ArrayList<List<Integer>>();
		for (int i = 0; i < numbers.length; i++) {
			int n = numbers[i];
			int complementary = k - n;
			List<Integer> complementaryIndexes = numberIndexes.getOrDefault(complementary, Collections.emptyList());
			for (Integer index : complementaryIndexes) {
				if (index != i) {
					complementaryPairs.add(Arrays.asList(i, index));
				}
			}
		}
		return complementaryPairs;
	}

	/**
	 * Converts the List of Lists data estructure into a bidimensional array
	 * @param complementaryPairs
	 * @return
	 */
	private static int[][] convertToArray(List<List<Integer>> complementaryPairs) {
		int[][] complementaryPairsArray = new int[complementaryPairs.size()][2];
		for (int i = 0; i < complementaryPairs.size(); i++) {
			List<Integer> pair = complementaryPairs.get(i);
			complementaryPairsArray[i][0] = pair.get(0);
			complementaryPairsArray[i][1] = pair.get(1);
		}
		return complementaryPairsArray;
	}
}
