import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

class PalindromeTest {

	@Test
	void givenNullReturnFalse() {
		assertFalse(Palindrome.isPalindrome(null));
	}
	
	@Test
	void givenEmptyStringReturnTrue() {
		assertTrue(Palindrome.isPalindrome(""));
	}
	
	@Test
	void givenEvenCharsNotPalyndromeStringReturnFalse() {		
		assertFalse(Palindrome.isPalindrome("aaabbb"));
	}
	
	@Test
	void givenOddCharsNotPalyndromeStringReturnFalse() {		
		assertFalse(Palindrome.isPalindrome("aaabbb"));
	}
	
	@Test
	void givenEvenCharsPalyndromeStringReturnTrue() {
		assertTrue(Palindrome.isPalindrome("abccba"));
	}
	
	@Test
	void givenOddCharsPalyndromeStringReturnTrue() {
		assertTrue(Palindrome.isPalindrome("abcdcba"));
	}

}
