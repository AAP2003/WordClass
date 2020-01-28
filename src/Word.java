public class Word {
	int i = 0;
	private String word;
	
	public Word() {
		this.word = new String();
	}
	
	public Word(String word) {
		this.word = word.toLowerCase().trim();
	}
	
	public Word(Word word) {
		this.word = word.getWord().toLowerCase().trim();
	}
	
	private static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
	}
	
	public int length() {
		return word.length();
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int countSyllables() {
		return Math.max(1, countVowels() - numReductions());
	}
	
	private int countVowels() {
		int count = 0;
		for (char c : word.toCharArray()) {
			if (Word.isVowel(c)) {
				count++;
			}
		}
		return count;
	}
	
	private int numReductions() {
		int numReductions = 0;
		
		if (charAtEquals(r(0), 'e')
				&& (!charAtEquals(r(1), 'l') || Word.isVowel(charAtReverse(2)))) {
			numReductions++;
		} else if (word.length() > 3 && (!charAtEquals(r(2), 'e') && !charAtEquals(r(2), 's') && substringEquals(r(1), "es")
				|| substringEquals(r(1), "ed")
				&& !charAtEquals(r(2), 't'))) {
			numReductions++;
		} else if (word.length() > 2 && (charAtEquals(r(1), 'a')
				&& (charAtEquals(r(0), 'o') || charAtEquals(r(0), 'u')))) {
			numReductions++;
		} else if (word.length() > 2 && (Word.isVowel(charAtReverse(1)) && charAtEquals(r(0), 'y'))) {
			numReductions++;
		}
		
		if (word.length() > 2 && substringEquals(r(1), "sm")) {
			numReductions--;
		}
		
		if (word.length() > 3 && (!Word.isVowel(charAtReverse(3))) && (substringEquals(r(2), "les")
				|| substringEquals(r(2), "led"))) {
			numReductions--;
		}
		
		for (int i = 0; i < word.length() - 2; i++) {
			if (Word.isVowel(word.charAt(i)) && Word.isVowel(word.charAt(i + 1))) {
				if (word.length() > 3) {
					if (!(charAtEquals(i, 'i') && (charAtEquals(i + 1, 'a')
							|| (charAtEquals(i + 1, 'o') && !charAtEquals(i - 1, 't')
							&& !charAtEquals(i - 1, 'x') && !charAtEquals(i - 1, 'c')
							&& !charAtEquals(i - 1, 's') || charAtEquals(i - 2, 'y'))))
							&& (!charAtEquals(i, 'y') || (charAtEquals(i, 'y') && i == 0))) {
						numReductions++;
					}
				}
			}
		}
		
		return numReductions;
	}
	
	private boolean isValidIndex(int index) {
		return index >= 0 && index < word.length();
	}
	
	private boolean charAtEquals(int index, char equals) {
		return isValidIndex(index) && word.charAt(index) == equals;
	}
	
	private boolean substringEquals(int start_index, String equals) {
		return isValidIndex(start_index) && word.substring(start_index).equals(equals);
	}
	
	private boolean substringEquals(int start_index, int end_index, String equals) {
		return isValidIndex(start_index) && isValidIndex(end_index) && word.substring(start_index, end_index).equals(equals);
	}
	
	private int r(int index) {
		return word.length() - (index + 1);
	}
	
	private char charAtReverse(int index) {
		return word.charAt(r(index));
	}
}
