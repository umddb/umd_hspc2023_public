import java.util.Scanner;
import java.lang.Math;

public class CountWoodstripesSolution {

	public static long countWoodstripes(long n) {
		long count = 0;
		// YOUR CODE HERE
		// Write code to count floor(n/i) for all i from 1 to n and return the sum of all counts
		// Given: n/i = m + d1, the floor doesn’t change for next k = i*d1/m integers (+- 1)
		//
		
		long sqrt = (long) Math.sqrt(n);

		for (long k = 1; k <= sqrt; k++) {
			// the range of values a where floor(n/a) = k is: n/(k+1) < a <= n/k
			long count1 = n/k - n/(k+1);
			count += count1 * k;
		}
		for (long a = n/(sqrt+1); a >= 1; a--) {
			// the range of values a where floor(n/a) = k is: n/(k+1) < a <= n/k
			count += n/a;
		}
		//System.out.println("COUNT FASTER: " + count);

//		count = 0;
//		for(long i = 1;i <= n; i++) {
//			count += n/i;
//		}
//		System.out.println("COUNT DIRECT: " + count);

		return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			long n = sc.nextLong();
			System.out.println(countWoodstripes(n));
		}

		sc.close();
	}
}
