import java.util.Scanner;

public class FindLargestNumber {

	public static int findLargestNumber(int n, int digit1, int digit2) {
		// YOUR CODE HERE -- YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE 
		// 
		// Write code to insert digit1 and digit2 into string representation of n, so that we get the largest number
		// Return the largest number
		// Example: n = 123, digit1 = 4, digit2 = 5
		// The largest number we can get is 54123
		// So, return 54123

		return n;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int n = sc.nextInt();
			int digit1 = sc.nextInt();
			int digit2 = sc.nextInt();
			System.out.println(findLargestNumber(n, digit1, digit2));
		}

		sc.close();
	}
}
