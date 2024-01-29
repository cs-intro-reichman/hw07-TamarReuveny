
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

		int word1Length = word1.length();
		int word2Length = word2.length();

		if (word1Length == 0 && word2Length == 0) {
			return 0;
		}
		if (word1Length == 0) {
			return word2Length;
		}
		if (word2Length == 0) {
			return word1Length;
		}
		if (word1.charAt(0)== word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));

		}
		int insertCost = levenshtein(tail(word1), word2) + 1;
        int deleteCost = levenshtein(word1, tail(word2)) + 1;
        int replaceCost = levenshtein(tail(word1), tail(word2)) + 1;

		return Math.min(insertCost, Math.min(deleteCost, replaceCost));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0 ; i < dictionary.length ; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int tempDistance;
		int minDistance = word.length();
		String resemblesWord = "";
	
		for (int i = 0; i < dictionary.length; i++) {
			tempDistance = levenshtein(word.toLowerCase(),dictionary[i]);
			if (tempDistance < minDistance) {
			    minDistance = tempDistance;
			    resemblesWord = dictionary[i];
			}
		}	
			if (minDistance <= threshold) {
				return resemblesWord;
			} else {
				return word;
			}
	
	}

}
