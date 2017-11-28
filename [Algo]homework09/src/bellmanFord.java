import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bellmanFord {
	static int nodesCount;
	static int s;
	static int t;
	static int edgesCount;
	final static int infinity = Integer.MAX_VALUE;
	// final static int infinity = 700;

	public static class Node {
		int u;
		int v;
		int cost;

		public Node(int u, int v, int cost) { // 빈 노드
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {

		String fp = "data10 - 복사본.txt";
		ArrayList<Node> E = fileRead(fp);
		ArrayList<Integer> d = new ArrayList<>();

		// initialization
		for (int i = 0; i < nodesCount; i++) {
			d.add(infinity);
		}
		d.set(s, 0);
		for (int i = 1; i <= d.size() - 1; i++) {
			boolean update = false;
//			ArrayList<Integer> clone_d = (ArrayList<Integer>) d.clone();
			for (int j = 0; j < E.size(); j++) {
				Node tempE = E.get(j);
				if ( d.get(tempE.u) != infinity && d.get(tempE.v) > d.get(tempE.u) + tempE.cost) {
					d.set(tempE.v, d.get(tempE.u) + tempE.cost);
				}
//				if (!update && d.get(tempE.u) != infinity && d.get(tempE.v) > d.get(tempE.u) + tempE.cost) {
//					d.set(tempE.v, d.get(tempE.u) + tempE.cost);
//					update = true;
//				} else if (update && d.get(tempE.u) != infinity && clone_d.get(tempE.v) > d.get(tempE.u) + tempE.cost) {
//					d.set(tempE.v, d.get(tempE.u) + tempE.cost);
//				}

			}
		}

		for (int j = 0; j < E.size(); j++) {
			Node tempE = E.get(j);
			if (d.get(tempE.u) != infinity && d.get(tempE.v) > d.get(tempE.u) + tempE.cost) {
				System.out.println("report that a negative-weight cycle exists");
			}
		}

		for (int i = 0; i < d.size(); i++) {
			System.out.print(d.get(i) + " ");
		}

	}

	private static ArrayList<Node> fileRead(String fp) throws IOException {
		// fileReader
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);

		// Node 개수
		String line = br.readLine();
		nodesCount = Integer.parseInt(line);

		// s, t
		line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		// Edge 개수
		line = br.readLine();
		edgesCount = Integer.parseInt(line);
		ArrayList<Node> E = new ArrayList<Node>();

		line = br.readLine();
		while (line != null) {
			st = new StringTokenizer(line, " ");
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			Node node = new Node(v, w, cost);
			E.add(node);
			line = br.readLine();
		}
		fis.close();
		isr.close();
		return E;

	}
}
