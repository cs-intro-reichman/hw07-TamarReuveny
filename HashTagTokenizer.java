

public class HashTagTokenizer {

	public static void main(String[] args) {


		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0 ; i < dictionary.length ; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean exist = false;
		for (int i = 0; i < dictionary.length; i++) {
            if (word.equals(dictionary[i])) {
				exist = true;
			}
		}
			return exist;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
		hashtag = hashtag.toLowerCase();
        int N = hashtag.length();
		int endIndex = 1;

        for (int i = 1; i <= N; i++) {
			String tempWord = hashtag.substring(0, endIndex); 
			    if (existInDictionary(tempWord,dictionary)) {
					System.out.println(tempWord);
					breakHashTag(hashtag.substring(endIndex),dictionary);
					return;
				} else {
					endIndex++;
				}						
        } 
    }

}
