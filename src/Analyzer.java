import java.util.ArrayList;

public class Analyzer {

	private static final char[] vowels = {'a', 'i', 'u', 'e', 'o'};

	public static Word analyze(String word) {
		char[] temp = word.toCharArray();
		ArrayList<Integer> vowelLocations = Analyzer.getVowelPositions(temp);


		return null;
	}

	private static ArrayList<Integer> getVowelPositions(char[] word) {
		ArrayList<Integer> out = new ArrayList<Integer>();

		for (int i = 0; i < word.length; i++) {
			for (int v = 0; v < vowels.length; v++) {
				if (word[i] == vowels[v]) {
					out.add(i);
					break;
				}
			}
		}

		return out;
	}
}
