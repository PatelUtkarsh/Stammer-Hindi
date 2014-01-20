/**
 *   
 * @author Utkarsh
 *
 */


/**REQUIREMENTS:
 *  
 *  following files to run this project in same directory
 *  
 *  1.dictionary = contain root hindi words
 *  
 *  2.suffix = contain hindi suffix
 *  
 */

package stammer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class demo {
	static boolean flagSuffix = false, flagWord = false;
	static ArrayList<String> suffix = new ArrayList<String>();
	static ArrayList<String> dictionary = new ArrayList<String>();
	final static String SUFFIX_FILE="suffix";
	final static String DICTIONARY_FILE="dictionary";
	

	public static void main(String[] args) {
		String input = "जानकारी	NN.n.f.sg.3.d	जानकारी.0";
		String output = "";
		String arr[] = input.split("\t", 0);
		try {
			if (!arr[1].contains("unk")) {
				output = steam(arr[0]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(output);
		
		System.out.println(match(output));
	}

	static String match(String searchString) {
		String out = "";
		if (!flagWord) {
			loadDictionary();
			flagWord = true;
		}

		int min = Integer.MAX_VALUE, temp;

		for (String string : dictionary) {
			if (searchString.contains(string)
					&& (string.length() <= searchString.length())) {
				temp = LevenshteinDistance.computeLevenshteinDistance(string,
						searchString);
				if (min > temp) {
					min = temp;
					out = string;
				}
			}
		}
		if (out.equals("")) {
			return searchString;
		}
		return out;
	}

	private static void loadDictionary() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(DICTIONARY_FILE));
			String line;
			while ((line = br.readLine()) != null) {
				dictionary.add(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("dictionary file not found");
			e.printStackTrace();
		}
	}

	private static void loadSuffix() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(SUFFIX_FILE));
			String line;
			while ((line = br.readLine()) != null) {
				suffix.add(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("suffix file not found");
			e.printStackTrace();
		}
	}

	static String steam(String Word) {
		if (!flagSuffix) {
			loadSuffix();
			flagSuffix = true;
		}
		for (int i = 0; i < suffix.size(); i++) {
			if (Word.endsWith(suffix.get(i))) {
				Word = Word.substring(0,
						(Word.length() - (suffix.get(i).length())));
			}
		}
		return Word;
	}
}
