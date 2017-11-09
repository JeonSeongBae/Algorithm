import java.util.ArrayList;

public class PrimsAlgorithm {
	final static int infinity = Integer.MAX_VALUE;

	public static void main(String[] args) {
		String[] v = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" };
		int[] key = new int[v.length];
		int[][] w = new int[v.length][v.length];
		String[] π = new String[v.length];
		ArrayList<String> q = new ArrayList<String>();
		PrimsAlgorithm self = new PrimsAlgorithm();
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				w[i][j] = infinity;
			}
		}
		self.add(w, "a", "b", 4);
		self.add(w, "a", "h", 8);

		self.add(w, "b", "h", 11);
		self.add(w, "b", "c", 8);

		self.add(w, "c", "d", 7);
		self.add(w, "c", "f", 4);
		self.add(w, "c", "i", 2);

		self.add(w, "d", "e", 9);
		self.add(w, "d", "f", 14);

		self.add(w, "e", "f", 10);

		self.add(w, "f", "g", 2);

		self.add(w, "g", "h", 1);
		self.add(w, "g", "i", 6);

		self.add(w, "h", "i", 7);

		for (int i = 0; i < v.length; i++) {
			self.insert(q, v[i]);
		}
		for (int i = 0; i < key.length; i++) {
			key[i] = infinity;
		}
		key[index("a")] = 0;
		self.buildminheap(q, key);

		while (q.size() > 1) {
			String u = self.extract_min(q);
			System.out.println("w<" + π[index(u)] + "," + u + "> = " + key[index(u)]);
			for (int i = 0; i < w.length; i++) {
				if (w[index(u)][i] != infinity && q.contains(vertex(i)) && w[index(u)][i] < key[i]) {
					key[i] = w[index(u)][i];
					π[i] = u;
				}
			}
			self.buildminheap(q, key);
		}
		int sum = 0;
		for (int i = 0; i < key.length; i++) {
			sum += key[i];
		}
		System.out.println("w<MST> = " + sum);
	}

	private void add(int[][] w, String string, String string2, int i) {
		w[index(string2)][index(string)] = w[index(string)][index(string2)] = i;
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

	private void buildminheap(ArrayList<String> q, int[] key) {
		for (int i = q.size() / 2; i >= 1; i--) {
			minheapify(q, key, i);
		}
	}

	private void minheapify(ArrayList<String> q, int[] key, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int smallest;
		if (l <= q.size() - 1 && key[index(q.get(l))] < key[index(q.get(i))]) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= q.size() - 1 && key[index(q.get(r))] <= key[index(q.get(smallest))]) {
			smallest = r;
		}
		if (smallest != i) {
			String temp = q.get(i);
			q.set(i, q.get(smallest));
			q.set(smallest, temp);
			minheapify(q, key, smallest);
		}
	}

	public String extract_min(ArrayList<String> q) {
		String min = q.remove(1); // 1을 추출
		return min;
	}

	private void insert(ArrayList<String> q, String v) {
		if (q.size() == 0) {
			q.add(0, "");
		}
		q.add(v);
	}
}