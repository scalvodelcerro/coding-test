package documentrelevance.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Parses an array of options and extracts the configuration options
 * 
 * @author scalvo
 *
 */
public class OptionsParser {
	
	final static String DIRECTORY_OPTION = "-d";
	final static String TERMS_OPTION = "-t";
	final static String NUM_RESULTS_OPTION = "-n";	
	
	/**
	 * Parses an array of options, the required options are:
	 * -d <directory>
	 * -n <count of top results>
	 * -t <set of terms>
	 * 
	 * @param args options
	 * @return An Options configuration object
	 */
	public static Options parse(String[] args) {
		String directory = null;
		Set<String> terms = null;
		Integer resultsNumber = null;
		
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			switch (arg) {
			case DIRECTORY_OPTION:
				directory = args[++i];
				break;
			case TERMS_OPTION:
				String termsArg = args[++i];
				terms = new HashSet<String>(Arrays.asList(termsArg.split(" ")));				
				break;
			case NUM_RESULTS_OPTION:
				String resultsNumberArg = args[++i];
				resultsNumber = Integer.parseInt(resultsNumberArg);
				break;
			default:
				break;
			}			
		}
		
		return new Options(directory, terms, resultsNumber);
	}
	

}
