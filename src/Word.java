import java.util.Arrays;
import java.util.stream.Collectors;

public class Word {
	private final String word;
	private final String[] syllables;

	public Word(String word) {
		this.word = word;
		this.syllables = findSyllables();
	}

	public Word(String word, String[] syllables) {
		this.word = word;
		this.syllables = syllables;
	}

	private String[] findSyllables() {
		return null;
	}

	public String getWord() {
		return word;
	}

	public String[] getSyllables() {
		return syllables;
	}

	public int getNumSyllables() {
		return syllables.length;
	}

	public String toString() {
		return "Word: " + word + "\nSyllables: " + Arrays.stream(syllables).map(a -> "-" + a)
				.collect(Collectors.joining()).substring(1);
	}
}
