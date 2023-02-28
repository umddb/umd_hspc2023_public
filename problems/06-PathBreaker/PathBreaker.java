import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

public class PathBreaker {

	public static int NumHabitatsSaved(Integer[] P, int N, int K) {
		// Your code to determine and return the solution
		// YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE

		return -1;
	}

	public static int RunTestCase(Scanner sc) {
		// Read the seed, the number of habitats, N, and the number of
		// defense units.
		int seed=sc.nextInt();
		int N=sc.nextInt();
		int K=sc.nextInt();

		Random r = new Random(seed);
		Integer[] P = new Integer[N];
		for (int i = 0; i < N; ++i) {
			P[i] = i;
		}
		Collections.shuffle(Arrays.asList(P), r);

		return NumHabitatsSaved(P, N, K);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			int answer = RunTestCase(sc);
			System.out.println("Num Habitats Saved: " + answer);
		}

		sc.close();
	}
}
