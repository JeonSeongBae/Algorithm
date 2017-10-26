
public class DijkstraAlgorithm {
	// 무한대를 표현
	final static double infinity = Double.POSITIVE_INFINITY;
	/*
	 * public static class path { String start; String end; int cost;
	 * 
	 * public path(String start, String end, int cost) { this.start = start;
	 * this.end = end; this.cost = cost; } }
	 */

	public static void main(String[] args) {

		int[][] path = new int[5][5];
		// A = 0 / B = 1 / C = 2 / D = 3 / E = 4
		path[index("A")][index("B")] = 10;
		path[index("A")][index("C")] = 3;
		path[index("B")][index("C")] = 1;
		path[index("B")][index("D")] = 2;
		path[index("C")][index("B")] = 4;
		path[index("C")][index("D")] = 8;
		path[index("D")][index("E")] = 7;
		path[index("E")][index("D")] = 9;
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path.length; j++) {
				if (path[i][j] == 0) {
					path[i][j] = (int) infinity;
				}
			}
		}
	}

	private static int vertex(int index) {
		switch (index) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		default:
			return 5;
		}
	}

	private static int index(String vertex) {
		switch (vertex) {
		case "A":
			return 0;
		case "B":
			return 1;
		case "C":
			return 2;
		case "D":
			return 3;
		case "E":
			return 4;
		default:
			return 5;
		}
	}

}
