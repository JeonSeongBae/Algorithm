import java.util.ArrayList;

public class MST {
	// Class MST에 다음이 포함되도록 구현하시오. 그 외 필요한 필드들을 추가하시오.
	int size, max, fewer;
	String[] vertex;
	ArrayList<Edge> E, T;
	ArrayList<String> TV;
	Edge tempE;
	String tempV;

	// Edge들의 배열 E/ 내부 클래스 Class Edge/ 각 edge를 표현하기 위한 구조
	public MST(String[] args) {// MST 생성자
		size = args.length;
		// weightedunion & collapsingfind 연산을 위한 정점들의 배열 TV
		vertex = new String[size];
		System.arraycopy(args, 0, vertex, 0, size);
		TV = new ArrayList<>();
		E = new ArrayList<>();
		T = new ArrayList<>();
		max = Integer.MAX_VALUE;
	}

	// 내부 클래스 Edge
	private class Edge {// Arrays.sort를 하기위해 Comparable을
						// implements
		private String v, w;
		private int weight;

		private Edge(String v, String w, int weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
		}
	}

	public void add(String v, String w, int weight) {
		E.add(new Edge(v, w, weight));
	}

	private int index(String v) {
		for (int i = 0; i < size; i++) {
			if (vertex[i].equals(v))
				return i;
		}
		return 0;
	}

	public void prim(String root) {
		TV.add(root);
		while (T.size() < size - 1 && E.size() != 0) {// T배열의 크기가 size -1보다 작으고
														// E배열이 비어있지 않으면 반복
			/* E.get 돌면서 tv가 v w중에 같고 가중치 작을때 temp에 저장하고 템프를 마지막에 지워주고 삽입 */
			fewer = max;
			for (int i1 = 0; i1 < TV.size(); i1++) {// u에 인접한 간선 탐색
				for (int i = 0; i < E.size(); i++) {
					if (E.get(i).weight < fewer && E.get(i).v == TV.get(i1)) {
						fewer = E.get(i).weight;
						tempE = E.get(i);
						tempV = E.get(i).w;
					} else if (E.get(i).weight < fewer && E.get(i).w == TV.get(i1)) {
						fewer = E.get(i).weight;
						tempE = E.get(i);
						tempV = E.get(i).v;
					}
				}
			}
			// 가중치(weight)를 Max값으로 저장
			for (int i = 0; i < E.size(); i++) {
				if (E.get(i).equals(tempE)) {// 사용한 Edge 삭제
					E.remove(i);
					break;
				}
			}
			// add v to TV;
			TV.add(tempV);
			// add (u,v) to T;
			T.add(tempE);
		}
		if (T.size() < size - 1)
			System.out.printf("No spanning tree \n");
		else
			System.out.println("The edges in MST(minimum spanning tree) and the total cos of MST");
	}

	// 최소비용 간선 정보 출력
	public void print() {
		int sum = 0;
		for (int i = 0; i < T.size(); i++) {
			System.out.println("선택된 간선 : (" + T.get(i).v + ", " + T.get(i).w + ", " + T.get(i).weight + ")");
			sum += T.get(i).weight;
		}
		System.out.println("비용 합계 : " + sum);
	}
}