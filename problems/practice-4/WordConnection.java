import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;


public class WordConnection {
	public static String[] listOfWords = new String[] {"aaw", "aean", "ako", "akx", "ala", "alaw", "ale", "ali", "alw", "amop", "amp", "amrr", "amu", "ana", "ang", "ange", "anti", "apxa", "arye", "asa", "asp", "atxk", "avu", "awa", "awk", "awm", "awn", "awt", "awve", "ean", "eana", "eanu", "eap", "eapo", "eayo", "efa", "eik", "eim", "eimy", "eina", "eir", "eiri", "ekin", "ekx", "elnu", "elp", "elw", "ema", "emz", "emza", "eng", "engs", "eoa", "epa", "epay", "erik", "erok", "eru", "esi", "eti", "etsa", "etx", "eykt", "eyn", "eys", "faa", "fah", "fay", "fko", "fng", "ftx", "ful", "fya", "fyaw", "hap", "haw", "hay", "hel", "hrr", "huf", "hun", "hut", "iaki", "ian", "iant", "iao", "ibe", "ika", "ikn", "ikup", "ikx", "ilva", "ime", "imp", "imz", "ina", "inat", "inka", "inm", "ino", "inum", "iplo", "ipra", "ipu", "ira", "irey", "ita", "itay", "iti", "itp", "ivu", "iya", "iyev", "kaf", "kal", "kalt", "kam", "kamo", "kapa", "kaw", "kay", "kel", "kelk", "ker", "ketu", "kif", "kifk", "kllf", "krrk", "kuns", "kxu", "lamp", "lao", "lapo", "lefp", "lek", "leno", "letr", "liyu", "lop", "loro", "lrok", "lrr", "luke", "lupe", "mak", "mato", "meha", "mekr", "mlte", "moea", "moel", "moey", "mok", "moki", "molk", "mpin", "mpov", "mprr", "msyp", "mune", "naer", "nalp", "nari", "nave", "naw", "nayr", "nefi", "nema", "neyn", "nge", "niwo", "noik", "nolk", "nrra", "ntxi", "nyer", "nyne", "oan", "oeng", "oeoi", "oeti", "ofmi", "ofu", "ohey", "olo", "olu", "olun", "oma", "omum", "ong", "ongo", "ontu", "optx", "orng", "orsa", "oxtx", "pamt", "pang", "peyi", "piak", "piru", "polp", "poru", "puka", "pxam", "pxan", "pxef", "pxem", "pxim", "pxop", "pxur", "rak", "ralt", "rane", "rayu", "renu", "reyo", "rhen", "rllt", "rolu", "rons", "ror", "rtxa", "rutx", "sarp", "sawl", "seze", "siha", "sikt", "sily", "slau", "snel", "soan", "soe", "soha", "soke", "som", "sozo", "spaw", "sref", "srrf", "sung", "syul", "taku", "tamp", "tang", "tarn", "tawt", "teya", "tifa", "tixn", "tok", "tyok", "tute", "takx", "txam", "txap", "txan", "txek", "txem", "txep", "txer", "txig", "txoa", "txon", "txum", "ukem", "ukra"};

	public static ArrayList<String> findWordConnection(String start_word, String end_word) {
		ArrayList<String> path = new ArrayList<String>();
		// YOUR CODE HERE

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
