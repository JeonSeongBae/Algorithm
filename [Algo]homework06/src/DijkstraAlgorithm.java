public class DijkstraAlgorithm {
	int queue_size = 0;
	// 무한대를 표현
	final static double infinity = Double.POSITIVE_INFINITY;

	public static class V {
		int cost;

		public V(int cost) {
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		DijkstraAlgorithm self = new DijkstraAlgorithm();
		// d[v] 값의 계산이 완료된 점들의 집합
		int[][] w = new int[5][5];
		// A = 0 / B = 1 / C = 2 / D = 3 / E = 4
		// 각각의 위치에 cost를 저장
		w[index("A")][index("B")] = 10;
		w[index("A")][index("C")] = 3;
		w[index("B")][index("C")] = 1;
		w[index("B")][index("D")] = 2;
		w[index("C")][index("B")] = 4;
		w[index("C")][index("D")] = 8;
		w[index("D")][index("E")] = 7;
		w[index("E")][index("D")] = 9;
		// path가 없는 곳엔 무한대 값을 저장

		int[] d = new int[5];
		// 시작 지점의 값은 0이다.
		d[index("A")] = 0;
		V[] v = new V[10];
		v[index("A")] = new V(0);
		v[index("A") + 1] = new V(0);
		for (int i = 1; i < v.length; i++) {
			v[i] = new V((int) infinity);
		}
		V[] Q = new V[v.length];
		Q[0] = new V(0);
		for (int i = 0; i < v.length; i++) {
			self.insert(Q, v[i]);
		}
		while (Q[1] == null) {

		}
	}

	private void insert(V[] Q, V v) {
		Q[++queue_size] = v;
		buildminheap(Q);
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

	private void buildminheap(V[] Q) {
		for (int i = Q.length / 2; i >= 1; i--) {
			minheapify(Q, i);
		}
	}

	private void minheapify(V[] Q, int i) {
		int l = 2 * i;
		int r = 2 * i + 1;
		int smallest;
		if (l <= Q.length - 1 && Q[l].cost <= Q[i].cost) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= Q.length - 1 && Q[r].cost <= Q[smallest].cost) {
			smallest = r;
		}
		if (smallest != i) {
			V temp = Q[i];
			Q[i] = Q[smallest];
			Q[smallest] = temp;
			minheapify(Q, smallest);
		}
	}

	public V extract_min(V[] Q) {
		V min = Q[1]; // 1을 추출
		Q[1] = null;
		buildminheap(Q);// minheap으로 다시 만들어줌
		return min;
	}

}
