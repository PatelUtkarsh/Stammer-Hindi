import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import stammer.LevenshteinDistance;

public class Stammer {
	final static int MIN_SEARCH_MATCH = 3;

	public static String findClosestWord(ArrayList<String> words,
			String searchString) {
		int min = Integer.MAX_VALUE, temp;
		String FoundString = "";
		for (String string : words) {

			if (searchString.contains(string)
					&& (string.length() < searchString.length())) {
				temp = LevenshteinDistance.computeLevenshteinDistance(string,
						searchString);
				if (min > temp) {
					min = temp;
					FoundString = string;
				}
			}
		}
		if (min <= MIN_SEARCH_MATCH)
			return "";
		return FoundString;
	}

	public static void main(String args[]) throws IOException {
		if (args.length != 2) {
			System.out.println("Invalid Args");
			System.exit(1);
		}
		String line;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		ArrayList<String> suffix = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			suffix.add(line);
		}
		br.close();
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> tmpList;
		ArrayList<ArrayList<String>> stammer = new ArrayList<ArrayList<String>>();
		int temp;
		br = new BufferedReader(new FileReader(args[1]));
		String arr[];
		while ((line = br.readLine()) != null) {
			arr = line.split("\t", 0);
			try {
				if (!arr[1].contains("NN")) {
					words.add(arr[0]);
					for (int i = 0; i < suffix.size(); i++) {
						if (arr[0].endsWith(suffix.get(i))) {
							temp = arr[0].indexOf(suffix.get(i));
							tmpList = new ArrayList<String>();
							tmpList.add(arr[0]);
							if (temp == 0) {
								tmpList.add(arr[0].substring(temp));
							} else {
								tmpList.add(arr[0].substring(0, temp) + " + "
										+ (arr[0].substring(temp)));
							}
							// tmpList.add(findClosestWord(words,arr[0]));
							stammer.add(tmpList);
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid input Line : " + line);
			}
		}

		br.close();

		File file = new File("result");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (ArrayList<String> string : stammer) {
			string.add(findClosestWord(words, string.get(0)));
			for (String string2 : string) {
				System.out.print(string2 + "\t");
				bw.write(string2 + "\t");
			}
			System.out.println();
			bw.write("\n");
		}
		bw.close();
	}
}