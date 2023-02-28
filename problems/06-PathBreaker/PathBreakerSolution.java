import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

import java.util.PriorityQueue;

public class PathBreaker_solution {

	public static int NumHabitatsSaved(Integer[] P, int N, int K) {
		// Your code to determine and return the solution.
   
    // Mark all habitats as not yet visited.
    boolean[] visited = new boolean[N];
    for (int i = 0; i < N; ++i) {
      visited[i] = false;
    }

    PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());

    // Try to visit the next habitat.
    for (int i = 0; i < N; ++i) {
      // If not yet visited, then visit it and figure out the length
      // of this ship's attack path (i.e., the cycle length).
      if (!visited[i]) {
        visited[i] = true;
        int cur = P[i];
        int length = 1;
        while (cur != i) {
          visited[cur] = true;
          cur = P[cur];
          ++length;
        }
        // Add this path length to the PQ.
        PQ.add(length);
      }
    }

    // Greedily placing defense units on the longest cycles will save
    // the most habitats.
    int num_saved = 0;
    for (int j = 0; j < K && PQ.size() > 0; ++j) {
      num_saved += PQ.poll();
    }

    return num_saved;
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
