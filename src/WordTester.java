import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordTester {

	public WordTester() {

	}

	public static void main(String[] args) {
		while (true) {
			Scanner in = new Scanner(System.in);
			String strWord;
			Word word;
			
			System.out.println("Enter the word to count the syllables of (Press '0' to exit)");
			
			do {
				strWord = in.next();
			} while (strWord.isBlank());
			if (strWord.contentEquals("0")) break;
			word = new Word(strWord.trim());
			System.out.println(word.toString());
		}
	}
}
