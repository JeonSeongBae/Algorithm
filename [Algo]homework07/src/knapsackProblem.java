import java.util.Scanner;

/*The knapsack problem or rucksack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained by a fixed-size knapsack and must fill it with the most valuable items.
The problem often arises in resource allocation where there are financial constraints and is studied in fields such as combinatorics, computer science, complexity theory, cryptography, applied mathematics, and daily fantasy sports.*/
public class knapsackProblem {

	static Scanner scanner = new Scanner(System.in);
	static int[][] items;
	static int[][] opt;

	public static void main(String[] args) {

		// Number of items
		int n = scanner.nextInt();
		int limit_weight = scanner.nextInt();

		opt = new int[n + 1][limit_weight + 1];
		// Array of items
		items = new int[n + 1][2];

		/*
		 * Insert value and weight / Don't use index 0
		 */
		for (int i = 1; i < n + 1; i++) {
			// Value of item
			int value = scanner.nextInt();
			// Weight of itme
			int weight = scanner.nextInt();
			// Save value and weight
			items[i][0] = value;
			items[i][1] = weight;
		}
		scanner.close();

		fillOpt();
		printItem();

	}

	// Fill out 0-1 knapsack
	private static void fillOpt() {
		for (int i = 0; i < opt.length; i++) {
			for (int j = 0; j < opt[0].length; j++) {
				opt[i][j] = opt(i, j);
			}
		}
	}

	private static int opt(int i, int w) {
		if (i == 0) {
			return 0;
		} else if (items[i][1] > w) {
			return opt(i - 1, w);
		} else {
			return Math.max(opt(i - 1, w), items[i][0] + opt(i - 1, w - items[i][1]));
		}
	}

	// Print opt
	private static void printItem() {
		int max = 0;
		for (int i = 0; i < opt.length; i++) {
			for (int j = 0; j < opt[0].length; j++) {
				System.out.printf(" %d", opt[i][j]);
				if (max < opt[i][j]) {
					max = opt[i][j];
				}
			}
			System.out.println();
		}
		System.out.println("max : " + max);
		System.out.print("item : ");
		printMaxItems(max);
	}

	private static void printMaxItems(int max) {
		for (int i = 0; i < opt.length && max != 0; i++) {
			for (int j = 0; j < opt[0].length; j++) {
				if (max == opt[i][j]) {
					max -= items[i][0];
					System.out.print(i + " ");
					printMaxItems(max);
					return;
				}
			}
		}
	}
}
