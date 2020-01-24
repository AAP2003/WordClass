public class Word {
	private static final String[] patterns = {"V", "CV", "VC", "CVC", "CCV", "CCCV", "CVCC"};
	private static final char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
	private final String word;

	public Word(String word) {
		this.word = word.toLowerCase();
	}

	public Word(Word word) {
		this.word = word.getWord().toLowerCase();
	}

	public String getWord() {
		return word;
	}

	public int getNumSyllables() {
		String CVword = new String(findVowels());

		if (CVword.contains("C")) {
			int syllables = 0;
			syllables += (" " + CVword + " ").split("VC").length - 1;
 			syllables += (" " + word + " ").split("ia").length - 1;
			syllables += (" " + word + " ").split("eo").length - 1;
			if ((word.length() >= 5 && word.substring(word.length() - 4).contentEquals("ious"))
					|| (word.length() >= 4 && word.substring(word.length() - 3).contentEquals("ier")))
				syllables++;

			String lengthMinus2 = word.substring(word.length() - 2);
			char charAtLengthMinus3 = word.charAt(word.length() - 3);
			boolean lengthMinus4EqualsConstant = CVword.charAt(word.length() - 4) == 'C';
			if (word.length() > 3
					&& lengthMinus2.contentEquals("ed")) {
				syllables--;
				if (charAtLengthMinus3 == 't')
					syllables++;
				if (charAtLengthMinus3 == 'd')
					syllables++;
				if (word.length() > 4
						&& charAtLengthMinus3 == 'r'
						&& word.charAt(word.length() - 4) != 'r'
						&& lengthMinus4EqualsConstant)
					syllables++;
				if (word.length() > 4
						&& charAtLengthMinus3 == 'l'
						&& lengthMinus4EqualsConstant)
					syllables++;
			}

			if (word.length() > 3
					&& lengthMinus2.contentEquals("es")) {
				syllables--;
				for (char c : new char[]{'c', 'g', 'x', 's', 'z', 'i'}) {
					if (charAtLengthMinus3 == c) {
						syllables++;
						break;
					}
				}
				if (word.length() > 4
						&& charAtLengthMinus3 == 'l'
						&& lengthMinus4EqualsConstant)
					syllables++;
			}

			syllables++;
			if (CVword.charAt(word.length() - 1) == 'C')
				syllables--;
			if (word.length() > 1 && CVword.charAt(word.length() - 2) == 'C'
					&& word.charAt(word.length() - 1) == 'e')
				syllables--;

			for (String disyllabic : new String[]{"EA", "II", "IO", "UA", "UO"}) {
				if (word.length() > 1 && lengthMinus2.equals(disyllabic))
					syllables--;
			}

			if (syllables < 1) syllables++;

			return syllables;
		} else {
			return word.length();
		}
	}

	public String toString() {
		return "Word: " + word;
	}

	public char[] findVowels() {
		char[] charWord = word.toLowerCase().toCharArray();
		char[] out = new char[charWord.length];

		for (int i = 0; i < charWord.length; i++) {
			for (char v : vowels) {
				if (charWord[i] == v) {
					out[i] = 'V';
					break;
				} else {
					out[i] = 'C';
				}
			}
		}
		return out;
	}
}
