import java.util.*;

public class Starfish {

	/** 
	 *  Main Program - Read the input and output the final answer.
	 */
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		try {
			int numCases = scanner.nextInt();	// number of test cases to run

			for(int t = 1; t <= numCases; t++) {
				int cap = scanner.nextInt();	// max interval capacity
				int len = scanner.nextInt();	// max interval length
				int gap = scanner.nextInt();	// min inter-interval gap

				if (cap < 1 || len < 0 || gap < 0) {
					System.err.println("Error - One or more of cap, len, and gap inputs out of valid range");
					return;
				}

				int n = scanner.nextInt();		// number of starfish
				if (n <= 0) {
					System.err.println("Error - Must have at least one starfish");
					return;
				}

				int[] x = new int[n];			// starfish coordinates

				for (int i = 0; i < n; i++) {	// input starfish
					x[i] = scanner.nextInt();
					// must be strictly increasing
					if ((i >= 1) && (x[i] <= x[i-1])) {
						System.err.println("Error - Starfish must be given in increasing order");
						return;
					}
				}
				// diagnostic output
				System.out.println("Test case: " + t);
				System.out.println("  Capacity: " + cap + ", Length: " + len + ", Gap: " + gap);
				System.out.print("  Starfish:");
				for (int i = 0; i < n; i++) {
					System.out.print("  " + x[i]);
					if (i != n-1 && i%20 == 19) System.out.print(System.lineSeparator() + "           ");
				}
				System.out.println();
				// get solution
				ArrayList<Integer> endpoints = getStarfish(cap, len, gap, x);

				sumarizeResult(cap, len, gap, x, endpoints); // summarize the result
			}
		} catch (Exception e) {					// something went wrong
			System.err.println("Error encountered in processing input/output");
			e.printStackTrace();
		}
		finally {								// close scanner resource
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	/** Summaries the result and check for errors.
	*/
	private static void sumarizeResult(int cap, int len, int gap, int[] x, ArrayList<Integer> endpoints)
	{
		if (endpoints == null) {
			System.out.println("    Error - Result of getStarfish is null");
			return;
		}
		int n = x.length;						// number of starfish
		if (endpoints.size() % 2 != 0) {		// need an even number
			System.out.println("  Error - Must have an even number of endpoints");
		}

		System.out.println("  Found " + endpoints.size()/2 + " intervals:");
		int totalCollected = 0;					// total starfish collected
		int prevRight = -99999;					// previous interval right endpoint
		for (int i = 0; i < endpoints.size(); i+=2) {
			int left = endpoints.get(i);		// interval endpoints
			int right = endpoints.get(i+1);
			int s = -1;							// indices of endpoints
			int t = -1;
			for (int j = 0; j < n; j++) {
				if (left == x[j]) s = j;
				if (right == x[j]) t = j;
			}
			if (s < 0) {
				System.out.println("    Error - Interval endpoint " + left + " is not at a starfish location");
			}
			if (t < 0) {
				System.out.println("    Error - Interval endpoint " + right + " is not at a starfish location");
			}
			if (left > right) {
				System.out.println("    Error - Left endpoint " + left + " is greater than right endpoint " + right);
			}
			int collected = t - s + 1;			// starfish collected
			int length = right - left;			// interval length
			int gapSize = left - prevRight;		// gap with prior interval

			System.out.print("    [" + left + ", " + right + "] of length " + length + " containing " + collected + " points");
			if (i > 0) {
				System.out.print(" gap size " + gapSize);
			}
			System.out.println();

			if (length > len) {
				System.out.println("    Error - Interval length exceeds length bound");
			} else if (collected > cap) {
				System.out.println("    Error - Number of contained points exceeds capacity bound");
			} else if (i > 0 && gapSize < gap) {
				System.out.println("    Error - Too close to previous interval");
			}
			totalCollected += collected;
			prevRight = right;
		}
		System.out.println("  Total starfish collected: " + totalCollected);
	}

	static ArrayList<Integer> getStarfish(int cap, int len, int gap, int[] x) {
		/* -------------------- INSERT YOUR CODE HERE ----------------------*/
		// YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE
		return null; // replace this...It's just to keep the compiler happy
	}

}
