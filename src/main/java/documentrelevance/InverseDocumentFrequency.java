package documentrelevance;

import java.util.Collection;

/**
 * Utils for calculating the inverse document frequency of a set of documents
 * 
 * @author scalvo
 *
 */
public class InverseDocumentFrequency {

	public static double calculateIdfForTerm(String term, Collection<DocumentTermCounter> counters) {
		int totalDocumentNumber = counters.size();
		long documentsContainingTerm = counters.stream().filter(document -> document.containsTerm(term)).count();
		
		if (documentsContainingTerm == 0) return 0D;
		return Math.log((double) totalDocumentNumber / documentsContainingTerm);
	}
}
