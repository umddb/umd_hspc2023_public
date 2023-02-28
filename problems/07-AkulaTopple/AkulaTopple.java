import java.util.Scanner;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AkulaTopple {

	public static boolean firstPlayerWins(char[] initial, char[] player1, char[] player2, ArrayList<String> moves) {
		// YOUR CODE HERE
		// YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			char[] a = sc.next().toCharArray();
			char[] b = sc.next().toCharArray();
			char[] c = sc.next().toCharArray();

			// You should construct the "moves" array as follows.
			// moves[0] = the initial state (i.e., the char array "a" above)
			// moves[1] = the move made by Player 1 ("Akula Up 2 X", where X is the Akula that was moved up, "Akula Up 1 X", and "Akula Topple").
			// moves[2] = the next state after the above move
			// moves[3] = the move made by Player 2
			// ...
			// moves[13] = the final state
			ArrayList<String> moves = new ArrayList<String>();

			// print out the input
			System.out.println("============ Test Case " + testCase + " ====================");
			System.out.println("Initial State: " + new String(a));
			System.out.println("Player 1: " + new String(b));
			System.out.println("Player 2: " + new String(c));

			System.out.println(firstPlayerWins(a, b, c, moves) ? "*** First Player Wins ***": "*** Second Player Wins ***");
			try {
				// print 2 on each line from moves
				System.out.println("Start -->        " + moves.get(0));
				for (int i=0; i < moves.size()-2; i+=2) {
					System.out.println(moves.get(i+1) + " --> " + moves.get(i+2));
				}
			} 
			catch(Exception e) {
			}
		}

		sc.close();
	}
}
