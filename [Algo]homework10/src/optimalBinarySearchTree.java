import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class optimalBinarySearchTree {
	final static int infinity = Integer.MAX_VALUE;
	static ArrayList<Double> p;
	static ArrayList<Double> q;
	static double[][] e;
	static double[][] w;
	static int[][] root;
	static int n;

	public static void main(String[] args) throws IOException {
		p = new ArrayList<Double>();
		q = new ArrayList<Double>();
		String fp = "data11.txt";
		fileRead(fp);
		e = new double[n + 1][n + 1];
		w = new double[n + 1][n + 1];
		root = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				e[i][j] = -1;
				w[i][j] = -1;
				root[i][j] = -1;
			}
		}
		optimalBst(p, q, n);
		print();
	}

	private static void print() {
		System.out.print("          ");
		System.out.printf("p\t  q\n");
		for (int i = 0; i < p.size(); i++) {
			System.out.printf("%4d\t%5.2f\t%5.2f\n", i, p.get(i), q.get(i));
		}

		System.out.println("----------------------------------------------------------------");
		System.out.println("e(i,j) : ");
		System.out.print("         ");
		for (int i = 0; i <= n; i++) {
			System.out.printf("%2d\t", i);
		}
		System.out.println();
		for (int i = 0; i <= n; i++) {
			System.out.printf("%5d\t", i);

			for (int j = 0; j <= n; j++) {
				System.out.printf("%5.2f\t", e[i][j]);
			}
			System.out.println();
		}

		System.out.println("----------------------------------------------------------------");
		System.out.println("w(i,j) : ");
		System.out.print("         ");
		for (int i = 0; i <= n; i++) {
			System.out.printf("%2d\t", i);
		}
		System.out.println();
		for (int i = 0; i <= n; i++) {
			System.out.printf("%5d\t", i);

			for (int j = 0; j <= n; j++) {
				System.out.printf("%5.2f\t", w[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println("root(i,j) : ");
		System.out.print("        ");
		for (int i = 0; i <= n; i++) {
			System.out.printf("%2d\t", i);
		}
		System.out.println();
		for (int i = 0; i <= n; i++) {
			System.out.printf("%4d\t", i);
			for (int j = 0; j <= n; j++) {
				System.out.printf("%2d\t", root[i][j]);
			}
			System.out.println();
		}
	}

	private static void optimalBst(ArrayList<Double> p, ArrayList<Double> q, int n) {

		for (int i = 1; i < n + 1; i++) {
			e[i][i - 1] = q.get(i - 1);
			w[i][i - 1] = q.get(i - 1);
		}
		for (int l = 1; l < n; l++) {
			for (int i = 1; i < n - l + 1; i++) {
				int j = i + l - 1;
				e[i][j] = infinity;
				w[i][j] = w[i][j - 1] + p.get(j) + q.get(j);
				for (int r = i; r <= j; r++) {
					double t = e[i][r - 1];
					t += e[r + 1][j];
					t += w[i][j];
					if (t < e[i][j]) {
						e[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
	}

	private static void fileRead(String fp) throws IOException {
		// fileReader
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");

		while (line != null) {
			st = new StringTokenizer(line, "\t");
			double cost = Double.parseDouble(st.nextToken());

			p.add(cost);
			cost = Double.parseDouble(st.nextToken());

			q.add(cost);
			line = br.readLine();
			n++;
		}
		fis.close();
		isr.close();
	}
}
