import java.util.Scanner;

public class HiddenPaths {

    public static long NumHiddenPaths(int m, int n) {
        // Your code to determine and return the solution...
        // YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE
      return -1;
    }

    public static long RunTest(Scanner sc) {
        // Read m and n.
        int m=sc.nextInt();
        int n=sc.nextInt();
        // Solve the problem.
        return NumHiddenPaths(m, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            long answer = RunTest(sc);
            System.out.println("Num Hidden Paths: " + answer);
        }

        sc.close();
    }
}
