import java.util.Scanner;

public class FindLargestNumberSolution {

	public static int findLargestNumber(int n, int digit1, int digit2) {
		int count = 0;
		// YOUR CODE HERE
		// Write code to insert digit1 and digit2 into string representation of n, so that we get the largest number
		// Return the largest number
		// Example: n = 123, digit1 = 4, digit2 = 5
		// The largest number we can get is 54321
		// So, return 54321

		// YOUR CODE HERE
		String str = Integer.toString(n);

		int large = Math.max(digit1, digit2);
		int small = Math.min(digit1, digit2);

		// loop through characters and insert large
		boolean inserted = false;
		for(int i = 0; i < str.length(); i++) {
			// if character is less than digit1, insert digit1 before it
			if( (str.charAt(i) - '0') < large) {
				str = str.substring(0, i) + Integer.toString(large) + str.substring(i);
				inserted = true;
				break;
			}
		}
		if (!inserted) {
			str = str + Integer.toString(large);
		}

		inserted = false;
		for(int i = 0; i < str.length(); i++) {
			// if character is less than digit1, insert digit1 before it
			if( (str.charAt(i) - '0') < small) {
				str = str.substring(0, i) + Integer.toString(small) + str.substring(i);
				inserted = true;
				break;
			}
		}
		if (!inserted) {
			str = str + Integer.toString(small);
		}
		
		return Integer.parseInt(str);
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
