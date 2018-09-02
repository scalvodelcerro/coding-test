package documentrelevance.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import documentrelevance.parser.Options;
import documentrelevance.parser.OptionsParser;

class OptionsParserTest {

	@Test
	void givenEmptyArgsReturnEmptyOptions() {
		String[] args = {};
		Options expected = new Options(null, null, null);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);

	}

	@Test
	void givenNoDirectoryArgReturnNullDirectoryOption() {
		Integer expectedResultsNumber = 2;
		Set<String> expectedTerms = new HashSet<String>(Arrays.asList("aaa", "bbb", "ccc"));

		String resultsNumberArg = expectedResultsNumber.toString();
		String termsArg = String.join(" ", expectedTerms);

		String[] args = { "-n", resultsNumberArg, "-t", termsArg };
		Options expected = new Options(null, expectedTerms, expectedResultsNumber);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);
	}

	@Test
	void givenNoTermsArgReturnEmptyTermsOption() {
		String expectedDirectory = "c:/tmp";
		Integer expectedResultsNumber = 2;

		String resultsNumberArg = expectedResultsNumber.toString();

		String[] args = { "-n", resultsNumberArg, "-d", expectedDirectory };
		Options expected = new Options(expectedDirectory, null, expectedResultsNumber);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);
	}

	@Test
	void givenNoResultsNumberArgReturnNullResultsNumberOption() {
		String expectedDirectory = "c:/tmp";
		Set<String> expectedTerms = new HashSet<String>(Arrays.asList("aaa", "bbb", "ccc"));

		String termsArg = String.join(" ", expectedTerms);
		String[] args = { "-d", expectedDirectory, "-t", termsArg };

		Options expected = new Options(expectedDirectory, expectedTerms, null);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);
	}

	@Test
	void givenDirectoryTermsAndResultsNumberReturnOptions() {
		String expectedDirectory = "c:/tmp";
		Set<String> expectedTerms = new HashSet<String>(Arrays.asList("aaa", "bbb", "ccc"));
		Integer expectedResultsNumber = 2;

		String termsArg = String.join(" ", expectedTerms);
		String resultsNumberArg = expectedResultsNumber.toString();
		String[] args = { "-d", expectedDirectory, "-t", termsArg, "-n", resultsNumberArg };

		Options expected = new Options(expectedDirectory, expectedTerms, expectedResultsNumber);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);
	}

	@Test
	void givenTermsDirectoryAndResultsNumberReturnOptions() {
		String expectedDirectory = "c:/tmp";
		Set<String> expectedTerms = new HashSet<String>(Arrays.asList("aaa", "bbb", "ccc"));
		Integer expectedResultsNumber = 2;

		String termsArg = String.join(" ", expectedTerms);
		String resultsNumberArg = expectedResultsNumber.toString();
		String[] args = { "-t", termsArg, "-d", expectedDirectory, "-n", resultsNumberArg };

		Options expected = new Options(expectedDirectory, expectedTerms, expectedResultsNumber);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);
	}

	@Test
	void givenTermsResultsNumberAndDirectoryReturnOptions() {
		String expectedDirectory = "c:/tmp";
		Set<String> expectedTerms = new HashSet<String>(Arrays.asList("aaa", "bbb", "ccc"));
		Integer expectedResultsNumber = 2;

		String termsArg = String.join(" ", expectedTerms);
		String resultsNumberArg = expectedResultsNumber.toString();
		String[] args = { "-t", termsArg, "-n", resultsNumberArg, "-d", expectedDirectory };

		Options expected = new Options(expectedDirectory, expectedTerms, expectedResultsNumber);

		Options result = OptionsParser.parse(args);

		checkResult(expected, result);
	}

	private void checkResult(Options expected, Options result) {
		assertEquals(expected.getDirectory(), result.getDirectory());
		assertIterableEquals(expected.getTerms(), result.getTerms());
		assertEquals(expected.getResultsNumber(), result.getResultsNumber());
	}

}
