import java.util.Scanner;

public class SequenceAlignment {
	private static Scanner scanner;
	// Two-dimensional array of path
	static int[][] M;
	// The gap cost is fixed at 1
	static int δ = 1;
	// The Mismatches cost is fixed at 2
	static int αCost = 2;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		// String string1 = " " + scanner.nextLine();
		// String string2 = " " + scanner.nextLine();
		String string1 = " CTGACCTACCT";
		String string2 = " CCTGACTACAT";

		char[] x = string1.toCharArray();
		char[] y = string2.toCharArray();

		int[][] α = new int[x.length][y.length];
		M = new int[x.length][y.length];
		mismathes(x, y, α);
		int minCost = sequenceAlignment(x.length - 1, y.length - 1, x, y, δ, α);

		System.out.println("string1 : " + string1);
		System.out.println("string2 : " + string2 + "\n");
		System.out.println("MIN COST : " + minCost);
		printM(x, y);
	}

	private static int sequenceAlignment(int m, int n, char[] x, char[] y, int δ, int[][] α) {
		for (int i = 0; i <= m; i++) {
			M[i][0] = i * δ;
		}
		for (int j = 0; j <= n; j++) {
			M[0][j] = j * δ;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int min = Math.min(α[i][j] + M[i - 1][j - 1], δ + M[i - 1][j]);
				M[i][j] = Math.min(min, δ + M[i][j - 1]);
			}
		}
		return M[m][n];
	}

	private static void mismathes(char[] x, char[] y, int[][] α) {
		for (int i = 0; i < α.length; i++) {
			for (int j = 0; j < α[0].length; j++) {
				α[i][j] = αCost;
			}
		}
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				if (x[i] - y[j] == 0) {
					α[i][j] = 0;
				}
			}
		}
	}

	private static void printM(char[] x, char[] y) {
		System.out.print("  ");
		for (int i = 0; i < y.length; i++) {
			System.out.printf("%3s", y[i]);
		}
		System.out.println();
		for (int i = 0; i < M.length; i++) {
			System.out.print(x[i] + " ");
			for (int j = 0; j < M[0].length; j++) {
				System.out.printf("%3d", M[i][j]);
			}
			System.out.println();
		}
	}

}
