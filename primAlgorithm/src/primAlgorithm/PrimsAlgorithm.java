package primAlgorithm;

import java.util.ArrayList;

public class PrimsAlgorithm {
	final static int infinity = Integer.MAX_VALUE;
	static String[] v;
	static int[][] e;
	static ArrayList<V> q;
	static String[] parent;

	public static class V {
		String vertex;
		int key;

		public V(String vertex, int key) {
			this.vertex = vertex;
			this.key = key;
		}
	}

	public PrimsAlgorithm(String[] vertexes) {
		v = vertexes;
		e = new int[vertexes.length][vertexes.length];
		q = new ArrayList<V>();
		parent = new String[vertexes.length];
	}

	public static void main(String[] args) {
		new PrimsAlgorithm(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" });
		V[] V = new V[v.length];
		for (int i = 0; i < v.length; i++) {
			V[i] = new V(v[i], infinity);
		}
		V[0].key = 0;

		for (int i = 0; i < parent.length; i++) {
			parent[i] = "";
		}
		for (int i = 0; i < e.length; i++) {
			for (int j = 0; j < e.length; j++) {
				e[i][j] = infinity;
			}
		}
		e[index("a")][index("b")] = e[index("b")][index("a")] = 4;
		e[index("a")][index("h")] = e[index("h")][index("a")] = 8;

		e[index("b")][index("h")] = e[index("h")][index("b")] = 11;
		e[index("b")][index("c")] = e[index("c")][index("b")] = 8;

		e[index("c")][index("d")] = e[index("d")][index("c")] = 7;
		e[index("c")][index("f")] = e[index("f")][index("c")] = 4;
		e[index("c")][index("i")] = e[index("i")][index("c")] = 2;

		e[index("d")][index("e")] = e[index("e")][index("d")] = 9;
		e[index("d")][index("f")] = e[index("f")][index("d")] = 14;
		e[index("e")][index("f")] = e[index("f")][index("e")] = 10;
		e[index("f")][index("g")] = e[index("g")][index("f")] = 2;
		e[index("g")][index("h")] = e[index("h")][index("g")] = 1;
		e[index("g")][index("i")] = e[index("i")][index("g")] = 6;
		e[index("h")][index("i")] = e[index("i")][index("h")] = 7;

		insert(q, new V("", 0));
		for (int i = 0; i < v.length; i++) {
			insert(q, V[i]);
		}
		buildminheap(q);
		ArrayList<String> temp = new ArrayList<String>();
		while (q.size() > 1) {
			V u = extract_min(q);
			temp.add(u.vertex);
			System.out.println("w<" + parent[index(u.vertex)] + "," + u.vertex + "> = " + V[index(u.vertex)].key);
			for (int i = 0; i < e.length; i++) {
				if (e[index(u.vertex)][i] != infinity && !temp.contains(vertex(i))
						&& e[index(u.vertex)][i] < V[i].key) {
					V[i].key = e[index(u.vertex)][i];
					parent[i] = u.vertex;
				}
			}
			buildminheap(q);
		}

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
		}
		return "";
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
		}
		return -1;
	}

	private static void buildminheap(ArrayList<V> q) {
		for (int i = q.size() / 2; i >= 1; i--) {
			minheapify(q, i);
		}
	}

	private static void minheapify(ArrayList<V> q, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int smallest;
		if (l <= q.size() - 1 && q.get(l).key < q.get(i).key) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= q.size() - 1 && q.get(r).key < q.get(smallest).key) {
			smallest = r;
		}
		if (smallest != i) {
			V temp = q.get(i);
			q.set(i, q.get(smallest));
			q.set(smallest, temp);
			minheapify(q, smallest);
		}
	}

	public static V extract_min(ArrayList<V> q) {
		V min = q.remove(1);
		buildminheap(q);
		return min;
	}

	private static void insert(ArrayList<V> q, V v) {
		q.add(v);
		buildminheap(q);
	}
}