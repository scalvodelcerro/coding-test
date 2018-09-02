import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ComplementaryPairTest {

	@Test
	void givenNullArrayReturnEmpty() {
		int[][] expected = {};
		assertArrayEquals(expected, ComplementaryPair.findKComplementaryPairs(null, 0));
	}
	
	@Test
	void givenNoComplementaryPairsReturnEmpty() {
		int k = 3;
		int[] numbers = {2,4,6,8};
		int[][] expected = {};
		int[][] result = ComplementaryPair.findKComplementaryPairs(numbers, k);
		
		checkResult(expected, result);
	}

	
	@Test
	void givenComplementaryPairsReturnArrayWithPairs() {
		int k = 10;
		int[] numbers = {0,2,4,6,8,10};
		int[][] expected = {
			{0,5},
			{1,4},
			{2,3},
			{3,2},
			{4,1},
			{5,0}
		};
		int[][] result = ComplementaryPair.findKComplementaryPairs(numbers, k);
		
		checkResult(expected, result);
	}
	

	@Test
	void givenMultiplePairsForNumberReturnArrayWithAllPairs() {
		int k = 10;
		int[] numbers = {2,8,8,8,3,7,8,7};
		int[][] expected = {
			{0,1}, 
			{0,2},
			{0,3},
			{0,6},
			{1,0},
			{2,0},
			{3,0},
			{4,5},
			{4,7},
			{5,4},
			{6,0},
			{7,4}
		};
		int[][] result = ComplementaryPair.findKComplementaryPairs(numbers, k);
		
		checkResult(expected, result);
	}
	
	@Test
	void givenSameNumbersReturnArrayAllPairs() {
		int k = 10;
		int[] numbers = {5,5,5,5};
		int[][] expected = {
			{0,1}, 
			{0,2},
			{0,3},
			{1,0},
			{1,2},
			{1,3},
			{2,0},
			{2,1},
			{2,3},
			{3,0},
			{3,1},
			{3,2}
		};
		int[][] result = ComplementaryPair.findKComplementaryPairs(numbers, k);
		
		checkResult(expected, result);
	}
	
	private void checkResult(int[][] expected, int[][] result) {
		assertEquals(expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i][0], result[i][0]);
			assertEquals(expected[i][1], result[i][1]);			
		}
	}
}
