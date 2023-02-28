import java.util.Scanner;
import java.lang.Math;

public class CountWoodstripes {

    public static long countWoodstripes(long n) {
        long count = 0;

        // YOUR CODE HERE -- YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE
        // Write code to count floor(n/i) for all i from 1 to n and return the sum of all counts

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
