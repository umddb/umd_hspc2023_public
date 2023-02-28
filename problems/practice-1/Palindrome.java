import java.util.Scanner;

public class Palindrome {
	public static boolean isPalindrome(String s) {
		boolean ret = false;
		// YOUR CODE HERE

		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine(); // skip the newline character

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			String s = sc.next();
		    sc.nextLine(); // skip the newline character
			System.out.println(isPalindrome(s) ? "Yes": "No");
		}
		sc.close();
	}
}
