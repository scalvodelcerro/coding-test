package documentrelevance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Aggregates the count of given terms within a document
 * 
 * @author scalvo
 *
 */
public class DocumentTermCounter {
	private String documentName;
	private Map<String, Long> termCounts;
	
	public DocumentTermCounter(Path documentPath, Set<String> terms) {
		this.documentName = documentPath.getFileName().toString();
		try(Stream<String> stream = Files.lines(documentPath)) {
			this.termCounts = stream.
					flatMap(line -> Arrays.stream(line.trim().split(" "))).
					filter(word -> terms.contains(word)).
					collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Gets the name of the document
	 * 
	 * @return name of the document
	 */
	public String getDocumentName() {
		return documentName;
	}
	
	/**
	 * Gets the frequency of a given term in the document
	 * 
	 * @param term
	 * @return number of appearances of the term in the document
	 */
	public Long getFrequencyForTerm(String term) {
		return termCounts.getOrDefault(term, 0L);
	}
	
	/**
	 * Checks if the given term appears in the document
	 * 
	 * @param term
	 * @return true if the document contains the term
	 */
	public boolean containsTerm(String term) {
		return termCounts.containsKey(term);
	}
	
	public static void main(String[] args) throws IOException {
		Path documentPath = Paths.get("c:/tmp/document_1.txt");
		Set<String> terms = new HashSet<String>(Arrays.asList("black", "white", "red", "orange"));
		DocumentTermCounter counter = new DocumentTermCounter(documentPath, terms);
		System.out.println(String.format("document name: %s", counter.getDocumentName()));
		for (String term : terms) {
			System.out.println(String.format("contains term %s: %b, frequency: %d",
					term, counter.containsTerm(term), counter.getFrequencyForTerm(term)));
		}
	}
	
}
