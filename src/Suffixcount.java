import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Suffix implements Comparable<Suffix> {
	private String suffix;
	private int count;

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int probability) {
		this.count = probability;
	}

	public int compareTo(Suffix compareSuffix) {
		double compareQuantity = compareSuffix.getCount();
		return (int) (compareQuantity - this.getCount());
	}

	public Suffix(String string) {
		this.setSuffix(string);
		this.setCount(0);
	}

	public Suffix(String string, int val) {
		this.setCount(val);
		this.setSuffix(string);
	}

	@Override
	public String toString() {
		return String.format("%-10s \t %4d ", getSuffix(), getCount());
	}
}

public class Suffixcount {

	public static void main(String args[]) throws IOException {
		if (args.length != 2) {
			System.out.println("Invalid Args");
			System.exit(1);
		}
		ArrayList<Suffix> suffix = new ArrayList<Suffix>();
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		String line;
		int wordcount = 0;
		while ((line = br.readLine()) != null) {
			suffix.add(new Suffix(line));
		}
		br.close();
		br = new BufferedReader(new FileReader(args[1]));
		while ((line = br.readLine()) != null) {
			wordcount++;
			Collections.sort(suffix);
			for (int i = 0; i < suffix.size(); i++) {
				if (line.endsWith(suffix.get(i).getSuffix()))
					suffix.get(i).setCount(suffix.get(i).getCount() + 1);
			}
		}
		System.out.printf("%-10s \t %s \t %s \n", "Suffix", "count",
				"Probability");
		for (int i = 0; i < suffix.size(); i++) {
			System.out.printf("%s \t %.4f \n", (suffix.get(i)),
					(((float) suffix.get(i).getCount() / wordcount) * 100));
		}
	}
}
