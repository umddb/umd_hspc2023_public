import java.util.Scanner;
import java.lang.Math;

public class CountPlants {

	public static int countPlants(int n) {
		int count = 0;
		// YOUR CODE HERE

		return count;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int n = sc.nextInt();
			System.out.println(countPlants(n));
		}

		sc.close();
	}
}
