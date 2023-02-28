import java.util.Scanner;

public class PalindromeSolution {
	public static boolean isPalindrome(String s) {
		boolean ret = false;
		// YOUR CODE HERE
		// find if s is a palindrome
		// if s is a palindrome, return true
		// if s is not a palindrome, return false
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                		// System.out.println("Fails for " + s.charAt(i) + " and " + s.charAt(s.length() - 1 - i));
				return false;
			} 
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine(); // skip the newline character

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			String s = sc.nextLine();
			System.out.println(isPalindrome(s) ? "Yes": "No");
		}
		sc.close();
	}
}
