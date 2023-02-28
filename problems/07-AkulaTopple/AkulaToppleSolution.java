import java.util.Scanner;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


class GameState {
	char[] combinedState; // first five are the current board state in order -- next 6 characters are 0, 1 or 2 indicating the number of moves of each type left

	static HashMap<String, GameState> states = new HashMap<String, GameState>(); // the HashMap of all game states

	String moveFromParent = null; // the move that got us to this game state

	boolean player1Next() {
		return combinedState[5] + combinedState[6] + combinedState[7] >= combinedState[8] + combinedState[9] + combinedState[10];
	}

	int player1Wins = -1; // 1 if player1 wins, 0 if player2 wins
	ArrayList<GameState> children = null; // the children of this game state

	// initialize the game state
	public GameState(char[] combinedState) {
		this.combinedState = combinedState;
	}

	// check if the game is over
	public boolean isGameOver() {
		return combinedState[8] + combinedState[9] + combinedState[10] == '0' + '0' + '0';
	}

	// create the children of this game state
	public void exploreChildren(int indent) {
		if(indent < 0) {
			for (int i = 0; i < indent; i++) {
				System.out.print(" ");
			}
			System.out.println("======= Exploring " + new String(combinedState) + " =========");
		}
		// if the game is over, then return null
		if (!isGameOver()) {
			boolean player1Next = this.player1Next();
			ArrayList<char[]> nextStates = new ArrayList<char[]>();
			// for each of the three types of moves
			if ( (player1Next && combinedState[5] != '0') || (!player1Next && combinedState[8] != '0') ) {
				// this is akula up 2 and so there are three possibilities
				char[] s1 = Arrays.copyOf(combinedState, 11);
				if (player1Next) {
					s1[5] = (char)(combinedState[5] - 1);
				} else {
					s1[8] = (char)(combinedState[8] - 1);
				}
				char[] s2 = Arrays.copyOf(s1, 11);
				char[] s3 = Arrays.copyOf(s1, 11);
				s1[0] = combinedState[2]; 	s1[1] = combinedState[0]; s1[2] = combinedState[1];
				s2[1] = combinedState[3]; 	s2[2] = combinedState[1]; s2[3] = combinedState[2];
				s3[2] = combinedState[4]; 	s3[3] = combinedState[2]; s3[4] = combinedState[3];

				nextStates.add(s1); nextStates.add(s2); nextStates.add(s3);
			} 
			if ( (player1Next && combinedState[6] != '0') || (!player1Next && combinedState[9] != '0') ) {
				// this is akula up 1 and so there are four possibilities
				char[] s1 = Arrays.copyOf(combinedState, 11);
				if (player1Next) {
					s1[6] = (char)(combinedState[6] - 1);
				} else {
					s1[9] = (char)(combinedState[9] - 1);
				}
				char[] s2 = Arrays.copyOf(s1, 11);
				char[] s3 = Arrays.copyOf(s1, 11);
				char[] s4 = Arrays.copyOf(s1, 11);
				s1[0] = combinedState[1]; s1[1] = combinedState[0];
				s2[1] = combinedState[2]; s2[2] = combinedState[1];
				s3[2] = combinedState[3]; s3[3] = combinedState[2];
				s4[3] = combinedState[4]; s4[4] = combinedState[3];

				nextStates.add(s1);
				nextStates.add(s2);
				nextStates.add(s3);
				nextStates.add(s4);
			} 

			if ( (player1Next && combinedState[7] != '0') || (!player1Next && combinedState[10] != '0') ) {
				// this is akula topple and there is only one possibility
				char[] s1 = Arrays.copyOf(combinedState, 11);
				if (player1Next) {
					s1[7] = (char)(combinedState[7] - 1);
				} else {
					s1[10] = (char)(combinedState[10] - 1);
				}
				s1[0] = combinedState[1];  s1[1] = combinedState[2]; s1[2] = combinedState[3];  s1[3] = combinedState[4]; s1[4] = combinedState[0];

				nextStates.add(s1);
			}


			// for each of the next states, check if already exists in the HashMap
			children = new ArrayList<GameState>();
			for (char[] nextState : nextStates) {
				// if the next state is not in the HashMap, then add it
				// otherwise, add the existing state to the children
				String s = String.valueOf(nextState);
				if (!GameState.states.containsKey(s)) {
					GameState gs = new GameState(nextState);
					gs.exploreChildren(indent + 1);
					GameState.states.put(s, gs);
					children.add(gs);
				} else {
					// System.out.println("Already explored " + s);
					children.add(GameState.states.get(s));
				}
			}
		} 
	}

	public boolean checkIfPlayer1Wins(boolean[] player1Bitmap, boolean[] player2Bitmap) {
		int cnt1 = 0;
		int cnt2 = 0;
		for (int i = 0; i < 3; i++) {
			if (player1Bitmap[i]) {
				cnt1++;
			}
			if (player2Bitmap[i]) {
				cnt2++;
			}
		}

        return cnt1 >= cnt2;
	}

	// recursively set player1Wins
	public void setPlayer1Wins(char[] player1, char[] player2) {
		if (player1Wins != -1) {
			return;
		}
		// if the game is over, then set player1Wins
		if (children == null) {
			// Let's create two boolean arrays for each player
			boolean[] player1Bitmap = new boolean[5];
			boolean[] player2Bitmap = new boolean[5];

			for (int i = 0; i < 5; i++) {
				player1Bitmap[i] = combinedState[i] == player1[0] || combinedState[i] == player1[1] || combinedState[i] == player1[2];
				player2Bitmap[i] = combinedState[i] == player2[0] || combinedState[i] == player2[1] || combinedState[i] == player2[2];
			}

			player1Wins = checkIfPlayer1Wins(player1Bitmap, player2Bitmap) ? 1 : 0;
		} else {
			boolean player1Next = this.player1Next();
			for (GameState gs : children) {
				gs.setPlayer1Wins(player1, player2);
			}
			// if this is player1's turn, then player1 wins if any of the children's player1Wins is true
			// if this is player2's turn, then player1 wins if all of the children's player1Wins is true
			boolean p1Wins = (player1Next ? false : true);
			for (GameState gs : children) {
				if (player1Next) {
					p1Wins = p1Wins || (gs.player1Wins == 1);
				} else {
					p1Wins = p1Wins && (gs.player1Wins == 1);
				}
			}
			player1Wins = (p1Wins ? 1 : 0);
		}
	}

	public void print(int indent) {
		System.out.println("========\n CombinedState: " + new String(combinedState));
		System.out.println("Player1Next: " + player1Next());
		System.out.println("Player1Wins: " + player1Wins);
		if (children != null) {
			System.out.println("Children: ");
			for (GameState gs : children) {
				gs.print(indent + 1);
			}
		}
	}

	String getMove(char[] parent, char[] child) {
		// for either of up moves, just find the first difference
		char c = 0;
		if(parent[0] != child[0])
			c = child[0];
		else if(parent[1] != child[1])
			c = child[1];
		else if(parent[2] != child[2])
			c = child[2];
		else if(parent[3] != child[3])
			c = child[3];

		if((parent[5] != child[5]) || (parent[8] != child[8]))
			return "Akula Up 2 " + c;
		if((parent[6] != child[6]) || (parent[9] != child[9]))
			return "Akula Up 1 " + c;
		if((parent[7] != child[7]) || (parent[10] != child[10]))
			return "Akula Topple";
		assert(false);
		return "false";
	}

	public void bestMoves(ArrayList<String> moves) {
		// get a slice of first 5 characters
		moves.add(new String(this.combinedState, 0, 5));
		if(children != null) { 
			GameState c = null;
			if(this.player1Next()) {
				for (GameState gs : children) {
					if (gs.player1Wins == 1) {
						c = gs;
						break;
					}
				}
			} else {
				for (GameState gs : children) {
					if (gs.player1Wins == 0) {
						c = gs;
						break;
					}
				}
			}
            // if we haven't found a winning move, then pick the first
			if (c == null) {
				c = children.get(0);
			}
			moves.add(getMove(this.combinedState, c.combinedState));
			c.bestMoves(moves);
		}
	}
}

public class AkulaToppleSolution {

	public static boolean firstPlayerWins(char[] initial, char[] player1, char[] player2, ArrayList<String> moves) {
		// YOUR CODE HERE
		// We will build the game tree
		char[] initialGameState = new char[11];
		initialGameState[0] = initial[0]; initialGameState[1] = initial[1]; initialGameState[2] = initial[2];
		initialGameState[3] = initial[3]; initialGameState[4] = initial[4]; 
		initialGameState[5] = '2';
		initialGameState[6] = '2';
		initialGameState[7] = '2';
		initialGameState[8] = '2';
		initialGameState[9] = '2';
		initialGameState[10] = '2';

		GameState root = new GameState(initialGameState);
		root.exploreChildren(0);
		root.setPlayer1Wins(player1, player2);
		// root.print(0);

		// write out the moves
		root.bestMoves(moves);

		// print out who wins
		return root.player1Wins == 1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine();

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			char[] a = sc.next().toCharArray();
			char[] b = sc.next().toCharArray();
			char[] c = sc.next().toCharArray();

			GameState.states = new HashMap<String, GameState>(); // reset the HashMap of all game states
			ArrayList<String> moves = new ArrayList<String>();

			// print out the input
			System.out.println("============ Test Case " + testCase + " ====================");
			System.out.println("Initial State: " + new String(a));
			System.out.println("Player 1: " + new String(b));
			System.out.println("Player 2: " + new String(c));

			System.out.println(firstPlayerWins(a, b, c, moves) ? "*** First Player Wins ***": "*** Second Player Wins ***");
			// print 2 on each line from moves
			System.out.println("Start -->        " + moves.get(0));
			for (int i=0; i < moves.size()-2; i+=2) {
				System.out.println(moves.get(i+1) + " --> " + moves.get(i+2));
			}
		}

		sc.close();
	}
}
