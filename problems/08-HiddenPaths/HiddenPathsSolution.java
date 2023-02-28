import java.util.Scanner;

public class HiddenPaths_solution {

    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    public static long NumHiddenPaths(int m, int n) {
      //Your code to determine and return the solution...

      // The code contracts together any tuple with sum k. It runs in
      // (m+n)^2 time.
      long[] h = new long[m+n+1];  // histogram array.
      long[] num_paths = new long[m+n+1];  // kth-index stores the number of paths from any tuple with sum k.
      for (int i=2; i <=m+n; ++i) {
        h[i] = 0;
        num_paths[i] = 0;
      }

      // Initialize histogram array.
      for (int a=1; a <= m; ++a) {
        for (int b=1; b <=n; ++b) {
          if (a > b) {
            continue;
          }
          int k = a + b;
          h[k] += 1;
        }
      }

      // Fill in num_paths from m+n down to 2.
      num_paths[m+n] = 1;
      for (int k=m+n-1; k>1; --k) {
        for (int i = k+1; i <= m+n; ++i) {
          // gcd(k, i) == 1, then there are edges from any node with
          // sum k to any node with sum i.
          if (gcd(k, i) == 1) {
            num_paths[k] += h[i] * num_paths[i];
          }
        }
      }

      // The number of paths from (1,1).
      return num_paths[2];
    }

    public static long RunTest(Scanner sc) {
        //Read m and n..
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
