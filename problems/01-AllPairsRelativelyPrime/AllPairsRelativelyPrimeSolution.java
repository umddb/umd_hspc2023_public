import java.util.Scanner;
import java.util.ArrayList;

public class AllPairsRelativelyPrimeSolution {

	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static boolean allPairsRelativelyPrime(int[] distances) {
		// YOUR CODE HERE
		// find all pairs of relatively prime numbers such that one number is between a and b, and other number is between c and d
		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				if (gcd(distances[i], distances[j]) != 1) {
					return false;
				}
			}
		}
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
