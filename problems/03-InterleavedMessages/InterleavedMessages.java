import java.util.Scanner;

public class InterleavedMessages {
	public static String decrypt(String s, int k) {
		// YOUR CODE TO DECRYPT HERE -- YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE

		return s;
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
