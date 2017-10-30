package kruskal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MST {
	// Class MST에 다음이 포함되도록 구현하시오. 그 외 필요한 필드들을 추가하시오.
	private int parent[];
	private int size;
	private int minCost = 0;

	int edgesize, Tsize;
	String[] vertex;
	Edge[] E, T;

	// Edge들의 배열 E
	// 내부 클래스 Class Edge
	// 각 edge를 표현하기 위한 구조
	public MST() {// MST 생성자
		edgesize = Tsize = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("C://input.txt"));
			String line = br.readLine();
			String[] split_line = line.split(" ");
			size = Integer.parseInt(split_line[0]); // 정점의 수

			vertex = new String[Integer.parseInt(split_line[0])];
			E = new Edge[Integer.parseInt(split_line[1])];
			T = new Edge[size - 1];
			this.parent = new int[size];
			for (int i = 0; i < parent.length; i++) {// TV값을 -1로 초기화
				parent[i] = -1;
			}
			// 간선 개수 만큼 객체 배열 생성
			for (int i = 0; (line = br.readLine()) != null; i++) {
				split_line = line.split(" ");
				add(Integer.parseInt(split_line[0]), Integer.parseInt(split_line[1]), Integer.parseInt(split_line[2]));
			} // input.txt 파일에서 읽은 데이터를 바탕으로 E초기화

		} catch (IOException e) {
			e.printStackTrace();
		}
		// weightedunion & collapsingfind 연산을 위한 정점들의 배열 TV

	}

	// 내부 클래스 Edge
	private class Edge implements Comparable {
		private int v, w;
		private int weight;

		public Edge(int parseInt, int parseInt2, int parseInt3) {
			this.v = parseInt;
			this.w = parseInt2;
			this.weight = parseInt3;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Edge temp = (Edge) o;
			if (weight < temp.weight) { // 시간복잡도 = n*log n (n == Edge의 개수)
				return -1;
			} else if (weight > temp.weight) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	public void add(int inputv, int inputw, int inputweight) {
		E[edgesize++] = new Edge(inputv, inputw, inputweight);
	}

	private int index(String v) {
		for (int i = 0; i < size; i++) {
			if (vertex[i].equals(v))
				return i;
		}
		return 0;
	}

	private int findparent(int num) {
		while (0 <= parent[num]) {
			num = parent[num];
		}
		return num;
	}

	private int collapsingfind(int i) {// O(1)
		if (parent[i] >= 0) {
			parent[i] = findparent(i);
			collapsingfind(parent[i]);
		}
		return findparent(i);
	}

	private void weightedunion(int v, int w) {
		int vparent = findparent((v));
		int wparent = findparent((w));
		// circle이 형성되지 않음
		if (vparent > wparent) {
			parent[wparent] += parent[vparent];
			parent[vparent] = wparent;
			// vparent < wparent
		} else {
			parent[vparent] += parent[wparent];
			parent[wparent] = vparent;
		}
	}

	public void kruskal() {
		// T contains less than n-1 edges and E is not empty
		for (int i = 0; Tsize != size - 1 && edgesize != 0; i++) {
			if (collapsingfind((E[i].v)) != collapsingfind((E[i].w))) {
				weightedunion(E[i].v, E[i].w);
				T[Tsize++] = new Edge(E[i].v, E[i].w, E[i].weight);
			}
			E[i] = null;
			edgesize--;
		}
		if (Tsize < size - 1) {
			System.out.println("No spanning tree");
		}
	}

	// 최소비용 간선 정보 출력
	public void print() {
		int sum = 0;
		System.out.println("최소 신장 트리에 포함된 간선");
		for (int i = 0; i < T.length; i++) {
			System.out.print("( " + T[i].v + " , " + T[i].w + " ) ");
			sum += T[i].weight;
		}
		System.out.println();
		System.out.println("비용 합계 : " + sum);
	}
}