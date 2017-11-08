import java.util.ArrayList;

public class PrimsAlgorithm {
	final static int infinity = Integer.MAX_VALUE;
	String[] v;
	int[] key;
	int[][] e;
	ArrayList<String> q;

	public PrimsAlgorithm(String[] vertexes) {
		v = vertexes;
		key = new int[vertexes.length];
		e = new int[vertexes.length][vertexes.length];
		q = new ArrayList<String>();
	}

	public static void main(String[] args) {
		System.out.println("Prims algorithm.");
		System.out.println();
		PrimsAlgorithm self = new PrimsAlgorithm(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" });

		self.add("a", "b", 4);
		self.add("a", "h", 8);

		self.add("b", "h", 11);
		self.add("b", "c", 8);

		self.add("c", "d", 7);
		self.add("c", "f", 4);
		self.add("c", "i", 2);

		self.add("d", "e", 9);
		self.add("d", "f", 14);

		self.add("e", "f", 10);

		self.add("f", "g", 2);

		self.add("g", "h", 1);
		self.add("g", "i", 6);

		self.add("h", "i", 7);

		for (int i = 0; i < self.v.length; i++) {
			self.insert(self.q, self.v[i]);
		}
		for (int i = 0; i < self.key.length; i++) {
			self.key[i] = infinity;
		}
		self.key[index("a")] = 0;
		self.buildminheap(self.q);

		while (self.q.size() > 1) {
			String u = self.extract_min(self.q);
		}
	}

	private void add(String string, String string2, int i) {
		e[index(string2)][index(string)] = e[index(string)][index(string2)] = i;
	}

	private static String vertex(int index) {
		switch (index) {
		case 0:
			return "a";
		case 1:
			return "b";
		case 2:
			return "c";
		case 3:
			return "d";
		case 4:
			return "e";
		case 5:
			return "f";
		case 6:
			return "g";
		case 7:
			return "h";
		case 8:
			return "i";
		default:
			return "";
		}
	}

	private static int index(String vertex) {
		switch (vertex) {
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;
		case "i":
			return 8;
		default:
			return 5;
		}
	}

	private void buildminheap(ArrayList<String> q) {
		for (int i = q.size() / 2; i >= 1; i--) {
			minheapify(q, i);
		}
	}

	private void minheapify(ArrayList<String> q, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int smallest;
		if (l <= q.size() - 1 && key[index(q.get(l))] < key[index(q.get(i))]) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= q.size() - 1 && key[index(q.get(r))] < key[index(q.get(smallest))]) {
			smallest = r;
		}
		if (smallest != i) {
			String temp = q.get(i);
			q.set(i, q.get(smallest));
			q.set(smallest, temp);
			minheapify(q, smallest);
		}
	}

	public String extract_min(ArrayList<String> q) {
		String min = q.remove(1); // 1을 추출
		buildminheap(q);// minheap으로 다시 만들어줌
		return min;
	}

	private void insert(ArrayList<String> q, String v) {
		if (q.size() == 0) {
			q.add(0, "");
		}
		q.add(v);
		buildminheap(q);
	}
}