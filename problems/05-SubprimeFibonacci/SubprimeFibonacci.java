import java.util.Scanner;

public class SubprimeFibonacci {
    static int prime = 10007;

    private static int[] solveSubprimeFibonacci(int[] sequence) {
        int[] firstFive = null;
        // YOUR CODE HERE -- YOU MAY ADD HELPER METHODS IF YOU WISH ANYWHERE IN THIS FILE

        return firstFive;
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

            int[] firstFive = solveSubprimeFibonacci(sequence);
			if(firstFive != null) {
				System.out.print("YES: ");
				for(int j = 0; j < 5; j++) {
					System.out.print(firstFive[j] + " ");
				}
				System.out.println();
			} else {
				System.out.println("NO");
			}
        }
    }
}

