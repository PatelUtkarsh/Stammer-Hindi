/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trial {
	
	
	public static String findClosestWord(){
		
		return "";
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> tmpList;
		ArrayList<ArrayList<String>> stammer = new ArrayList<ArrayList<String>>();
	BufferedReader	br = new BufferedReader(new FileReader(args[0]));
	String line;
		String arr[];
		while ((line = br.readLine()) != null) {
			arr = line.split("\t", 0);
			words.add(arr[0]);
		}
		br.close();
	
	
	Scanner sysScanner = new Scanner(System.in);
	String scn = sysScanner.nextLine();
	int min=Integer.MAX_VALUE,demo;
	String tempstring="";
	for(String temp1 : words){
		
		if (scn.contains(temp1) && (temp1.length() < scn.length())) {
			//System.out.println(temp1);
			demo=LevenshteinDistance.computeLevenshteinDistance(temp1, scn);
			if (min > demo) {
				min = demo;
				tempstring=temp1;
			}
		}
	}
	System.out.println(tempstring);
}
}*/