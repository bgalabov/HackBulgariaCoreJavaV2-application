import java.util.ArrayList;
import java.util.Scanner;

public class smallestSubstring {

	static void smallestSubstringContainingTheAlphabet(String str) {
		char[] chars; //we put the characters from the input string here
		int[][] countOfCharacter = new int[str.length()][26]; //a table, containing the count of the letters of alphabet found in each substring
		ArrayList<Integer> stringStart = new ArrayList<Integer>(); //contains the begin of each substring, containing the alphabet
		ArrayList<Integer> stringLength = new ArrayList<Integer>(); //contains the length of each substring, containing the alphabet
		int indexOfMin; //we put there the index of the substring which has min. length
		int b; //the current character we check
		int p = 0; //position. We use it for looping when we print the smallest found substring.
		int charCount=0; //the count of the alphabetic characters we have found
		boolean allLettersFound=false; //have we found all letters from the alphabet
		
		chars = str.toLowerCase().toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			for (int k = i; k < chars.length; k++) {
				b = (int) chars[k];
				charCount++;
				/* checking if a character from the input is outside of ASCII */
				if(b<0 || b>127){
					System.err.println("Error! String contains symbols out of ASCII table.");
					return;
				}
				/* checking if there is a blank space in the input string */
				else if(b==32){
					System.err.println("Error! String contains blank space.");
					return;
				}
				/* checking if all of the letters in the alphabet is found at least
				for at least one time */
				else if (b >= 97 && b <= 122) {
					countOfCharacter[i][b - 97]++;
					/* we start checking if we have all characters from the alphabet after the 26-th character we've got.  */
					if(charCount>=26){
						for(int j=0; j<26; j++){
							if(countOfCharacter[i][j]>0){
								allLettersFound=true;
							}else{
								allLettersFound=false;
								break;
							}
						}
						if(allLettersFound==true){
							stringStart.add(i);
							stringLength.add(k - i);
						}
					}
				}
			}
		}
		
		/*
		 * for(int i=0; i<chars.length; i++){ for(int j=0; j<26; j++){
		 * System.out.print(countOfCharacter[i][j] + " "); }
		 * System.out.println(); }
		 */
		
		/* Checks if there is a substring, containing the alphabet. 
		 * If yes, then gets the index of the string which has the smallest length. 
		 * We use it to find the begin of the string - they have same index.*/
		if (stringLength.isEmpty()) {
			System.out
					.println("The input string doesn't contain the whole alphabet.");
			return;
		} else {
			indexOfMin = getMin(stringLength);

			/*
			 * starts looping from the begin of the smallest substring until its
			 * end.
			 */
			chars=str.toCharArray();
			for (int i = stringStart.get(indexOfMin); p <= stringLength
					.get(indexOfMin); i++, p++) {
				System.out.print(chars[i]);
			}
			System.out.println();
		}
	}

	/* We use it to find the begin of the string - they have same index.*/
	public static int getMin(ArrayList<Integer> arr) {
		int min = arr.get(0);
		int i;
		for (i = 0; i < arr.size(); i++) {
			if (arr.get(i) < min) {
				min = arr.get(i);
			}
		}
		return arr.indexOf(min);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str;

		System.out.println("Type a string: ");
		str = in.nextLine();
		smallestSubstring.smallestSubstringContainingTheAlphabet(str);

		// smallestSubstring.smallestSubstringContainingTheAlphabet("aaaaaabcdefghijklmnopqrstuvwxyz");
		// smallestSubstring.smallestSubstringContainingTheAlphabet("abcdefghijklmn124345678!@#$%^&*opqrstuvwxyz!*abcdefghijklmn");

	}

}
