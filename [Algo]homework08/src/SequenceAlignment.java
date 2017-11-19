import java.util.Scanner;

public class SequenceAlignment {
	// δα
	static int[][] M = new int[10][10];
	private static Scanner scanner;
	static int b = 1;
	static int mismatch = 2;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		char[] x = scanner.nextLine().toCharArray();
		char[] y = scanner.nextLine().toCharArray();
		int[][] a = new int[x.length][y.length];
		int output = SequenceAlignment(x.length, y.length, x, y, b, a);
		System.out.println(output);
	}

	private static int SequenceAlignment(int m, int n, char[] x, char[] y, int b, int[][] a) {
		for (int i = 0; i <= m; i++) {
			M[0][i] = i * b;
		}
		for (int j = 0; j <= n; j++) {
			M[j][0] = j * b;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int min = Math.min(a[x[i]][y[i]] + M[i - 1][j - 1], b + M[i - 1][j]);
				M[i][j] = Math.min(min, b + M[i][j - 1]);
			}
		}
		return M[m][n];
	}

}
