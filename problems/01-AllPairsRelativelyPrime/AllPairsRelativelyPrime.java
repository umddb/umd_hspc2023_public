import java.util.Scanner;
import java.util.ArrayList;

public class AllPairsRelativelyPrime {

	public static boolean allPairsRelativelyPrime(int[] distances) {
		// YOUR CODE HERE -- YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE 
		//
		// For every pair of numbers in the distances array, check if they are relatively prime
		// If any pair is not relatively prime (i.e., has gcd > 1), then return false.
		// Otherwise return true
		//
		// You may wish to implement a helper method that returns the gcd of two numbers.
		
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int n = sc.nextInt();
			int[] distances = new int[n];
			for (int i = 0; i < n; i++) {
				distances[i] = sc.nextInt();
			}
			System.out.println(allPairsRelativelyPrime(distances) ? "All pairs relatively prime" : "There are some pairs that are not relatively prime");
		}

		sc.close();
	}
}
