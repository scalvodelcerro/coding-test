package documentrelevance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import documentrelevance.parser.Options;
import documentrelevance.parser.OptionsParser;

/**
 * Calculates the relevance of the given terms in a set of documents
 * 
 * @author scalvo
 *
 */
public class DocumentRelevance {
	private final Options options;
	private Collection<DocumentTermCounter> counters;
	private Map<String, Double> documentRelevances;

	public DocumentRelevance(Options options) {
		this.options = options;
	}

	/**
	 * Loads the documents contained in the configured directory path
	 * @throws IOException
	 */
	public void readDocuments() throws IOException {
		counters = Files.list(Paths.get(options.getDirectory())).filter(Files::isRegularFile)
				.map(file -> new DocumentTermCounter(file, options.getTerms())).collect(Collectors.toList());
	}

	/**
	 * Calculates de tf-idf (term frequency-inverse document frequency) for documents using the set of terms
	 */
	public void calculateDocumentRelevances() {
		Map<String, Double> documentRelevances = counters.stream()
				.collect(Collectors.toMap(counter -> counter.getDocumentName(), counter -> 0D));

		for (String term : options.getTerms()) {
			double idf = InverseDocumentFrequency.calculateIdfForTerm(term, counters);

			for (DocumentTermCounter counter : counters) {
				double tfIdf = counter.getFrequencyForTerm(term) * idf;
				double currentTfIdf = documentRelevances.get(counter.getDocumentName());
				documentRelevances.put(counter.getDocumentName(), currentTfIdf + tfIdf);
			}
		}
		this.documentRelevances = documentRelevances;
	}

	/**
	 * Prints the calculated relevance of documents using the standard output
	 */
	public void printResults() {
		System.out.println("---------------------------------------------------");
		this.documentRelevances.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(options.getResultsNumber()).forEachOrdered(
						e -> System.out.println(String.format("Document: %s, relevance %f", e.getKey(), e.getValue())));
	}
	
	/**
	 * Initializes a listener that checks the configured directory for new files
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void initNewDocumentWatcher() throws IOException, InterruptedException {
		Path rootDirectory = Paths.get(options.getDirectory());
		WatchService watchService = rootDirectory.getFileSystem().newWatchService();
		rootDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		
		while(true) {
			WatchKey watchKey = watchService.take();
			
			for (WatchEvent event : watchKey.pollEvents()) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					Path newDocumentPath = Paths.get(rootDirectory.toString(), event.context().toString());
					this.counters.add(new DocumentTermCounter(newDocumentPath, options.getTerms()));
					calculateDocumentRelevances();
					printResults();
				}
			}
			watchKey.reset();
		}
	}

	
	/**
	 * Main entry point
	 * 
	 * @param program options -d <directory> -n <count of top results> -t <set of terms>
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Options options = OptionsParser.parse(args);
		System.out.println(String.format("directory: %s, terms: %s, resultsNumber: %d", options.getDirectory(),
				String.join(",", options.getTerms()), options.getResultsNumber()));
		

		DocumentRelevance dr = new DocumentRelevance(options);
		dr.readDocuments();
		dr.calculateDocumentRelevances();
		dr.printResults();
		try {
			dr.initNewDocumentWatcher();
		} catch (InterruptedException e) {
			System.out.println(String.format("Error: %s", e.getMessage()));
		}
	}

}
