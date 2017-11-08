import java.util.ArrayList;

public class DijkstraAlgorithm {
	int queue_size = 0;
	// 무한대를 표현
	final static double infinity = Double.POSITIVE_INFINITY - 1;
	// final static int infinity = Integer.MAX_VALUE;

	public static class V {
		String vertex;
		int cost;

		public V(String vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		DijkstraAlgorithm self = new DijkstraAlgorithm();
		// d[v] 값의 계산이 완료된 점들의 집합
		int[][] w = new int[5][5];
		// A = 0 / B = 1 / C = 2 / D = 3 / E = 4
		// 각각의 위치에 cost를 저장
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				w[i][j] = (int) infinity;
			}
		}
		w[index("A")][index("B")] = 10;
		w[index("A")][index("C")] = 3;
		w[index("B")][index("C")] = 1;
		w[index("B")][index("D")] = 2;
		w[index("C")][index("B")] = 4;
		w[index("C")][index("D")] = 8;
		w[index("C")][index("E")] = 2;
		w[index("D")][index("E")] = 7;
		w[index("E")][index("D")] = 9;
		// path가 없는 곳엔 무한대 값을 저장
		ArrayList<V> V = new ArrayList<V>();
		ArrayList<Integer> d = new ArrayList<Integer>();
		ArrayList<V> Q = new ArrayList<V>();

		// 시작 지점의 값은 0이다.
		V.add(index("A"), new V("A", 0));
		for (int i = 1; i < 5; i++) {
			V.add(i, new V(vertex(i), (int) infinity));
		}
		// d[s] <- 0
		d.add(index("A"), 0);
		// for each v ∈ V-{s}
		for (int i = 1; i < V.size(); i++) {
			// do d[v] <- ∞
			d.add(i, (int) infinity);
		}
		// S <- ∅
		String[] S = new String[V.size()];
		// Q <- V Q is a priority queue maintaining V - S
		Q.add(0, new V("", 0));
		for (int i = 0; i < V.size(); i++) {
			self.insert(Q, V.get(i));
		}

		int S_index = 0;
		// while Q != ∅
		while (Q.size() != 1) {
			System.out.println("===============================================");
			// do u <- EXTRACT-MIN(Q)
			V u = self.extract_min(Q);
			// S <- S U {u}
			System.out.println("S[" + S_index + "] : d[" + u.vertex + "] = " + d.get(index(u.vertex)));
			System.out.println("-----------------------------------------------");
			S[S_index++] = u.vertex;
			// for each v ∈ Adj[u]
			for (int i = 1; i < Q.size(); i++) {
				// do if d[v] > d[u] + w(w,v)
				System.out.print("Q[" + i + "] : d[" + Q.get(i).vertex + "] = " + Q.get(i).cost);
				if (w[index(u.vertex)][index(Q.get(i).vertex)] != (int) infinity
						&& d.get(index(Q.get(i).vertex)) > d.get(index(u.vertex))
								+ w[index(u.vertex)][index(Q.get(i).vertex)]) {
					// then d[v] <- d[u] + w(w,v)
					d.set(index(Q.get(i).vertex), d.get(index(u.vertex)) + w[index(u.vertex)][index(Q.get(i).vertex)]);
					// Queue를 임의로 변경
					for (int j = 1; j < Q.size(); j++) {
						if (Q.get(j).vertex.equals(Q.get(i).vertex)) {
							System.out.print(" -> ");
							Q.set(j, new V(Q.get(i).vertex,
									d.get(index(u.vertex)) + w[index(u.vertex)][index(Q.get(i).vertex)]));
							System.out.println("d[" + Q.get(j).vertex + "] = " + Q.get(j).cost);
						}
					}

				} else {
					System.out.println();
				}
			}
			System.out.println();
			self.buildminheap(Q);
			// self.printQ(Q);
		}
	}

	private static String vertex(int index) {
		switch (index) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		default:
			return "";
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

	private void buildminheap(ArrayList<V> c) {
		for (int i = c.size() / 2; i >= 1; i--) {
			minheapify(c, i);
		}
	}

	private void minheapify(ArrayList<V> c, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int smallest;
		if (l <= c.size() - 1 && c.get(l).cost <= c.get(i).cost) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= c.size() - 1 && c.get(r).cost <= c.get(smallest).cost) {
			smallest = r;
		}
		if (smallest != i) {
			V temp = (V) c.get(i);
			c.set(i, c.get(smallest));
			c.set(smallest, temp);
			minheapify(c, smallest);
		}
	}

	public V extract_min(ArrayList<V> c) {
		V min = (V) c.remove(1); // 1을 추출
		buildminheap(c);// minheap으로 다시 만들어줌
		return min;
	}

	private void insert(ArrayList<V> c, V object) {
		c.add(object);
		buildminheap(c);
	}
}