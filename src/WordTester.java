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
			do {
				strWord = in.next();
			} while (strWord.isBlank());
			if (strWord.contentEquals("0")) break;
			word = new Word(strWord.trim());
			System.out.println(word.countSyllables());
		}

		try {
			Scanner in = new Scanner(new File("src\\tester.txt"));
			while (in.hasNext()) {
				String temp[] = in.nextLine().split(" ");
				Word word = new Word(temp[0]);
				int error = Integer.parseInt(temp[1]) - word.countSyllables();
				if (error != 0) {
					System.out.println("Error: " + error + "  Word: " + word.getWord());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
