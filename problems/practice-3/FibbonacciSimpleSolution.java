import java.util.Scanner;

public class FibbonacciSimpleSolution {
    private static boolean solveFibbonacciSimple(int [] sequence) {
        boolean isFibbonacci = true;

        /* ------------------- INSERT CODE HERE ---------------------*/

        for(int i = 2; i < sequence.length; i++) {
            if(sequence[i] != (sequence[i-1] + sequence[i-2])) {
                isFibbonacci = false;
            }
        }

        /* -------------------- END OF INSERTION --------------------*/

        return isFibbonacci;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int i = 0; i < numCases; i++) 
        {
            int lengthOfSequence = sc.nextInt();

            int [] sequence = new int[lengthOfSequence];

            for(int j = 0; j < lengthOfSequence; j++) {
                sequence[j] = sc.nextInt();
            }

            if(solveFibbonacciSimple(sequence)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

