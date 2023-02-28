import java.util.Scanner;

public class InterleavedMessagesSolution {
	public static String decrypt(String s, int k) {
		// YOUR CODE HERE
		// Create a new string with characters at positions 0, k, .., 1, k+1, ...
		String temp = "";
        int offset = s.length()/k;
        // System.out.println(offset);
		for (int i = 0; i < offset; i++) {
			for (int j = i; j < s.length(); j += offset) {
				temp += s.charAt(j);
			}
		}

		return temp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine(); // skip the newline character

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int k = sc.nextInt();
			String s = sc.next();
			System.out.println(decrypt(s, k));
		}
		sc.close();
	}
}
