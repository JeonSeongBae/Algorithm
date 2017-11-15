import java.util.Scanner;

public class knapsackProblem {

	static Scanner scanner = new Scanner(System.in);
	static int[][] items;
	static int[][] opt;

	public static void main(String[] args) {
		System.out.println("Start");
		// Number of items
		int n = scanner.nextInt();
		int limit_weight = scanner.nextInt();

		opt = new int[n + 1][limit_weight + 1];
		// Array of items
		items = new int[n + 1][2];

		// Insert value and weight
		for (int i = 1; i < n + 1; i++) {
			// Value of item
			int value = scanner.nextInt();

			// Weight of itme
			int weight = scanner.nextInt();

			// Save value and weight
			items[i][0] = value;
			items[i][1] = weight;
		}

		fillOpt();
		print();
	}

	private static int opt(int i, int w) {
		if (i == 0) {
			return 0;
		} else if (items[i][1] > w) {
			return opt(i - 1, w);
		} else {
			int a = opt(i - 1, w);
			int b = items[i][0] + opt(i - 1, w - items[i][1]);
			return Math.max(a, b);
		}
	}

	private static void fillOpt() {
		for (int i = 0; i < opt.length; i++) {
			for (int j = 0; j < opt[0].length; j++) {
				int a = opt(i, j);
				opt[i][j] = a;
			}
		}
	}

	private static void print() {

		for (int i = 0; i < opt.length; i++) {
			for (int j = 0; j < opt[0].length; j++) {
				System.out.printf("%d\t", opt[i][j]);
			}
			System.out.println();
		}
	}

}
