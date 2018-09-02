
public class Palindrome {

	/**
	 * Check if a string is a palindrome (the reverse of the string matches the original string)
	 * 
	 * @param s The original string
	 * @return true if it's a palindrome, false otherwise
	 */
	public static boolean isPalindrome(String s) {
		if (s == null) return false;
		for (int i = 0, j = s.length() - 1; i < s.length() / 2; i++, j--) {
			if (s.charAt(i) != s.charAt(j))
				return false;
		}
		return true;
	}
}
