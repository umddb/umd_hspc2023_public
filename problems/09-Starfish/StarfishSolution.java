
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;

/** Compute starfish intervals. Given a capacity (cap), interval length (len),
 * gap size (gap), and a sequence of points, this computes a collection of
 * disjoint intervals that cover the maximum number of points, subject to:
 *
 *  - Capacity - no interval can contain more than cap points
 *  - Length - no interval can be longer than len
 *  - Gap - no two consecutive intervals can be closer than gap
 *
 *  Ties are broken in favor of the lexicographically smallest solution.
 */

public class StarfishSolution {

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
			} else if (i > 0 && gapSize <= 0) {
				System.out.println("    Error - Overlap with previous interval");
			} else if (i > 0 && gapSize < gap) {
				System.out.println("    Error - Too close to previous interval");
			}
			totalCollected += collected;
			prevRight = right;
		}
		System.out.println("  Total starfish collected: " + totalCollected);
	}
		
	static ArrayList<Integer> getStarfish(int cap, int len, int gap, int[] x) {

	/* -------------------- INSERT CODE HERE ----------------------*/
		
	//-------------------------------------------------------------------
	// START OF PROTECTED MATERIAL
	//-------------------------------------------------------------------

	// --------------------- HOW IT WORKS --------------------------
	// The solution is based on dynamic programming. It will be
	// convenient to first copy the points to an array with n+1
	// elements, where x[0] = -infinty and otherwise x[i] holds the
	// index of the i-th input point.
	//
	// We say that a set of intervals is *valid* if it satisfies the
	// capacity, length, and gap bounds. For 0 <= i <= n, let M[i]
	// denote the maximum number of points from x[1..i] that can be
	// covered by a valid set of intervals, assuming that the last
	// interval in the set ends at x[i].
	//
	// As a basis case we set M[0] = 0, representing the empty set of
	// intervals. Before explaining how to compute M[i], let us first
	// define a few utilities. For i >= 1, define:
	//
	//   cap(i) = max(1, i-cap+1)
	//
	//   len(i) = min_{1 <= j <= i} (x[i] - x[j] <= len)
	//
	//   mcl(i) = max(cap(i), len(i))
	//
	//   gap(i) = max_{-1 <= j < i} (x[i] - x[j] >= gap)
	//
	// Observe that there is no advantage to be gained from skipping
	// over points when forming intervals. Thus, if an interval ends at
	// x[i], it can start no earlier than x[mcl(i)], for otherwise it 
	// will violate either the capacity or length constraints. Also,
	// if an interval begins at x[i], then the prior interval can end no
	// later than x[gap(i)].
	//
	// To compute M[i], the interval must start at some index s between
	// mcl(i) and i. For each such s, the prior interval ends at some
	// index t between 0 and gap(s). We can apply recursion to compute
	// the best solution ending at x[t]. This yields:
	//
	// M[i] = 1 + max_{mcl(i) <= s <= i} max_{0 <= t <= gap(s)} M[t]
	//
	// (For a faster solution, observe that the t loop never needs to 
	// look back by more than mcl(gap(s))-1, since there must exist 
	// at least one valid interval ending before then.)
	//
	// The overall optimal solution is determined by taking the max 
	// over all possible ending points:
	//
	//     max_{0 <= t <= n} M[t].
	//
	// (Note that the last interval need not end with M[n].
	//
	// This only yields the number of intervals. To obtain the actual
	// solution, we save the best values for the intervals left 
	// endpoint and the right endpoint of the previous interval.
	// ----------------------------------------------------------------
		int n = x.length;					// number of points
		int[] xx = new int[n+1];			// shifted point list
		xx[0] = -99999;						// sentinel point
		for (int i = 0; i < n; i++) {
			xx[i+1] = x[i];
		}

		int[] mcl = new int[n+1];			// utilities
		int[] gapx = new int[n+1];
		mcl[0] = 0;
		gapx[0] = 0;
		for (int i = 1; i <= n; i++) {
			int capx = i - cap + 1;			// earliest start for capacity bound
			if (capx < 1) capx = 1;
			int lenx = i;					// earliest start for length bound
			while (lenx > 1 && xx[i] - xx[lenx-1] <= len) lenx--;
			mcl[i] = (capx > lenx ? capx : lenx); // mcl is max of the two
			int j = -1;
			while (j < i-1 && xx[i] - xx[j+1] >= gap) j++;
			gapx[i] = j;					// latest end for gap bound
		}
		
		int[] M = new int[n+1];				// DP table (M)
		int[] left = new int[n+1];			// helpers for reconstruction
		int[] prevRight = new int[n+1];

		M[0] = 0;							// basis
		left[0] = prevRight[0] = 0;
		for (int i = 1; i <= n; i++) {		// compute table[i]
			int best = -1;
			left[i] = prevRight[i] = -1;				// basis
			for (int s = mcl[i]; s <= i; s++) {			// all valid left
				for (int t = 0; t <= gapx[s]; t++) {	// all valid previous right
					int candidate = M[t] + (i - s + 1);	// trial result
					if (candidate > best) {				// best so far?
						best = candidate;				// save it
						left[i] = s;
						prevRight[i] = t;
					}
				}
			}
			M[i] = best;					// save the best seen
		}

		int bestIdx = -1;					// get the overall best
		int bestCollected = -1;
		for (int i = 1; i <= n; i++) {
			if (M[i] > bestCollected) {
				bestIdx = i;
				bestCollected = M[i];
			}
		}
											// recover interval endpoints
		Stack<Integer> endpts = new Stack<Integer>();

		int t = bestIdx;
		while (t >= 1) {
			endpts.push(xx[t]);				// push right and left endpoints
			endpts.push(xx[left[t]]);
			t = prevRight[t];				// ...and backtrack
		}
											// reverse copy to solution
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (!endpts.empty()) {
			result.add(endpts.pop());
			result.add(endpts.pop());
		}
		return result;

	//-------------------------------------------------------------------
	// END OF PROTECTED MATERIAL
	//-------------------------------------------------------------------
	}

}
