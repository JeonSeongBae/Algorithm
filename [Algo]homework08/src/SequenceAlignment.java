
public class SequenceAlignment {
	// δα
	static int[][] M = new int[10][10];

	public static void main(String[] args) {
		int m = 0, n = 0;
		int[] x = new int[10];
		int[] y = new int[10];
		int b = 0;
		int[][] a = new int[10][10];

		int output = SequenceAlignment(m, n, x, y, b, a);
		System.out.println(output);
	}

	private static int SequenceAlignment(int m, int n, int[] x, int[] y, int b, int[][] a) {
		for (int i = 0; i <= m; i++) {
			M[0][i] = i * b;
		}
		for (int j = 0; j <= n; j++) {
			M[j][0] = j * b;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				int min = Math.min(a[x[i]][y[i]] + M[i - 1][j - 1], b + M[i - 1][j]);
				min = Math.min(min, b + M[i][j - 1]);
			}
		}
		return M[m][n];
	}

}
