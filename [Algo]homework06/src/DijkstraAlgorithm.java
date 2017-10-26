
public class DijkstraAlgorithm {
	// 무한대를 표현
	final static double infinity = Double.POSITIVE_INFINITY;
	// class Object {
	// String start;
	// String end;
	// int cost;
	//
	// Object(String start, String end, int cost) {
	// this.start = start;
	// this.end = end;
	// this.cost = cost;
	// }
	// }

	public static void main(String[] args) {
		Object[][] path = new Object[5][5];
		// A = 0 / B = 1 / C = 2 / D = 3 / E = 4
		path[0][1] = 10;
		path[0][1] = 10;
		path[0][1] = 10;
		path[0][1] = 10;
		path[0][1] = 10;
		path[0][1] = 10;
		path[0][1] = 10;
		path[0][1] = 10;
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
