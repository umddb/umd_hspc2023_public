import java.util.Scanner;
import java.lang.Math;

public class CountPlantsSolution {

	public static int countPlants(int n) {
		int count = 0;
		// YOUR CODE HERE
		// Write code to count ceil(n/i) for all i from 1 to n and return the sum of all counts
		for(int i = 1;i <= n; i++){
		    count += Math.ceil(((float)n)/i);
		}

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
