import java.util.Scanner;

public class SequenceAlignment {
	// δα
	static int[][] M;
	private static Scanner scanner;
	static int b = 1;
	static int mismatch = 2;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String string1 = " " + scanner.nextLine();
		String string2 = " " + scanner.nextLine();

		char[] x = string1.toCharArray();
		char[] y = string2.toCharArray();

		int[][] a = new int[x.length][y.length];
		M = new int[x.length][y.length];
		misMatch(x, y, a);
		int output = SequenceAlignment(x.length, y.length, x, y, b, a);
		System.out.println("string1 : " + string1);
		System.out.println("string2 : " + string2 + "\n");
		System.out.println("MIN COST : " + output);
		printM(x, y);
	}

	private static void printM(char[] x, char[] y) {
		System.out.print("   ");
		for (int i = 0; i < y.length; i++) {
			System.out.print(y[i] + "  ");
		}
		System.out.println();
		for (int i = 0; i < M.length; i++) {
			System.out.print(x[i] + "  ");
			for (int j = 0; j < M[0].length; j++) {
				System.out.print(M[i][j] + "  ");
			}
			System.out.println();
		}
	}

	private static void misMatch(char[] x, char[] y, int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = mismatch;
			}
		}
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				if (x[i] - y[j] == 0) {
					a[i][j] = 0;
				}
			}
		}
	}

	private static int SequenceAlignment(int m, int n, char[] x, char[] y, int b, int[][] a) {
		int[][] tempM = M;
		for (int i = 0; i < m; i++) {
			M[i][0] = i * b;
		}
		for (int j = 0; j < n; j++) {
			M[0][j] = j * b;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				int min = Math.min(a[i][j] + M[i - 1][j - 1], b + M[i - 1][j]);
				M[i][j] = Math.min(min, b + M[i][j - 1]);
			}
		}
		return M[m - 1][n - 1];
	}

}
