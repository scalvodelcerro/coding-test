package documentrelevance.parser;

import java.util.List;
import java.util.Set;

/**
 * Configuration options for the document relevance processing
 * 
 * @author scalvo
 *
 */
public class Options {
	private final String directory;
	private final Set<String> terms;
	private final Integer resultsNumber;

	public Options(String directory, Set<String> terms, Integer resultsNumber) {
		super();
		this.directory = directory;
		this.terms = terms;
		this.resultsNumber = resultsNumber;
	}

	public String getDirectory() {
		return directory;
	}

	public Set<String> getTerms() {
		return terms;
	}

	public Integer getResultsNumber() {
		return resultsNumber;
	}
}
