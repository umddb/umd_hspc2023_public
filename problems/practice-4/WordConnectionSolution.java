import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;


public class WordConnectionSolution {
	public static String[] listOfWords = new String[] {"aaw", "aean", "ako", "akx", "ala", "alaw", "ale", "ali", "alw", "amop", "amp", "amrr", "amu", "ana", "ang", "ange", "anti", "apxa", "arye", "asa", "asp", "atxk", "avu", "awa", "awk", "awm", "awn", "awt", "awve", "ean", "eana", "eanu", "eap", "eapo", "eayo", "efa", "eik", "eim", "eimy", "eina", "eir", "eiri", "ekin", "ekx", "elnu", "elp", "elw", "ema", "emz", "emza", "eng", "engs", "eoa", "epa", "epay", "erik", "erok", "eru", "esi", "eti", "etsa", "etx", "eykt", "eyn", "eys", "faa", "fah", "fay", "fko", "fng", "ftx", "ful", "fya", "fyaw", "hap", "haw", "hay", "hel", "hrr", "huf", "hun", "hut", "iaki", "ian", "iant", "iao", "ibe", "ika", "ikn", "ikup", "ikx", "ilva", "ime", "imp", "imz", "ina", "inat", "inka", "inm", "ino", "inum", "iplo", "ipra", "ipu", "ira", "irey", "ita", "itay", "iti", "itp", "ivu", "iya", "iyev", "kaf", "kal", "kalt", "kam", "kamo", "kapa", "kaw", "kay", "kel", "kelk", "ker", "ketu", "kif", "kifk", "kllf", "krrk", "kuns", "kxu", "lamp", "lao", "lapo", "lefp", "lek", "leno", "letr", "liyu", "lop", "loro", "lrok", "lrr", "luke", "lupe", "mak", "mato", "meha", "mekr", "mlte", "moea", "moel", "moey", "mok", "moki", "molk", "mpin", "mpov", "mprr", "msyp", "mune", "naer", "nalp", "nari", "nave", "naw", "nayr", "nefi", "nema", "neyn", "nge", "niwo", "noik", "nolk", "nrra", "ntxi", "nyer", "nyne", "oan", "oeng", "oeoi", "oeti", "ofmi", "ofu", "ohey", "olo", "olu", "olun", "oma", "omum", "ong", "ongo", "ontu", "optx", "orng", "orsa", "oxtx", "pamt", "pang", "peyi", "piak", "piru", "polp", "poru", "puka", "pxam", "pxan", "pxef", "pxem", "pxim", "pxop", "pxur", "rak", "ralt", "rane", "rayu", "renu", "reyo", "rhen", "rllt", "rolu", "rons", "ror", "rtxa", "rutx", "sarp", "sawl", "seze", "siha", "sikt", "sily", "slau", "snel", "soan", "soe", "soha", "soke", "som", "sozo", "spaw", "sref", "srrf", "sung", "syul", "taku", "tamp", "tang", "tarn", "tawt", "teya", "tifa", "tixn", "tok", "tyok", "tute", "takx", "txam", "txap", "txan", "txek", "txem", "txep", "txer", "txig", "txoa", "txon", "txum", "ukem", "ukra"};

	private static boolean addEdge(String start_word, String end_word) {
		// if start_word and end_word are of the same length and differ in a single character, return True
		// else return False
		if (start_word.length() == end_word.length()) {
			int diff = 0;
			for (int i=0; i < start_word.length(); i++) {
				if (start_word.charAt(i) != end_word.charAt(i)) {
					diff++;
				}
			}
			return diff == 1;
		} else {
			String small = start_word.length() < end_word.length() ? start_word : end_word;
			String large = start_word.length() < end_word.length() ? end_word : start_word;

			// check if all letters in small and present in large
			for (int i=0; i < small.length(); i++) {
				if (large.indexOf(small.charAt(i)) == -1) {
					return false;
				}
			}
			return true;
		}
	}

	public static ArrayList<String> findWordConnection(String start_word, String end_word) {
		ArrayList<String> path = new ArrayList<String>();
		// YOUR CODE HERE
		// Construct a graph with the words in listOfWords
		// Find the shortest path between start_word and end_word
		// Return the path as an ArrayList of Strings
		// If no path is found, return null
		
		// Create a graph with the words in listOfWords, using hashmaps
		HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();

		for (String word : listOfWords) {
			ArrayList<String> edges = new ArrayList<String>();
			for (String otherWord : listOfWords) {
				if (addEdge(word, otherWord)) {
					edges.add(otherWord);
					// System.out.println(word + " -> " + otherWord);
				}
			}
			// sort the edges
			Collections.sort(edges);
			graph.put(word, edges);
		}

		// Find the shortest path between start_word and end_word
		// Use BFS
		// Use a queue to store the nodes to be visited

		// Create a queue to store the nodes to be visited
		Queue<String> queue = new LinkedList<String>();
		// Create a hashmap to store the parent of each node
		// The parent of a node is the node that was visited before it
		// This is used to reconstruct the path
		// The parent of the start node is null
		HashMap<String, String> parent = new HashMap<String, String>();

		// Add the start node to the queue
		queue.add(start_word);
		// Set the parent of the start node to null
		parent.put(start_word, null);

		// While the queue is not empty
		while (!queue.isEmpty()) {
			// Remove the first node from the queue
			String node = queue.remove();
			// If the node is the end node
			if (node.equals(end_word)) {
				// Reconstruct the path
				// Start from the end node
				String current = end_word;
				// While the current node is not the start node
				while (current != null) {
					// Add the current node to the path
					path.add(current);
					// Set the current node to its parent
					current = parent.get(current);
				}
				// Reverse the path
				Collections.reverse(path);
				// Return the path
				return path;
			}
			if (graph.get(node) == null) {
				continue;
			}
			// For each neighbour of the node
			for (String neighbour : graph.get(node)) {
				// If the neighbour has not been visited
				if (!parent.containsKey(neighbour)) {
					// Add the neighbour to the queue
					queue.add(neighbour);
					// Set the parent of the neighbour to the node
					parent.put(neighbour, node);
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();
		sc.nextLine(); // skip the newline character

		for (int testCase=0; testCase < numOfTestCases; testCase++) { 
			String start_word = sc.next();
			String end_word = sc.next();
			ArrayList<String> path = findWordConnection(start_word, end_word);
			if (path == null) {
				System.out.println("No path found between " + start_word + " and " + end_word + ".");
			} else {
				// join the path with a space
				// print the path
				System.out.print("Path: ");
				System.out.println(String.join(" -> ", path));
			}
		}
		sc.close();
	}
}
