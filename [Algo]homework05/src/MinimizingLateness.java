import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinimizingLateness {
	public static class Node {
		public int t;
		public int d;

		public Node(int t, int d) {
			this.t = t;
			this.d = d;
		}

	}

	public static void main(String[] args) throws IOException {
		String fp = "data06_lateness.txt";
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);

		int count = Integer.valueOf(br.readLine());
		ArrayList<Node> A = new ArrayList();

		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		while (line != null) {
			st = new StringTokenizer(line, " ");
			while (st.hasMoreTokens()) {
				int t = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				Node temp = new Node(t, d);
				A.add(temp);
			}
			line = br.readLine();
		}

		int MAXLateness = minimizing(A);
		System.out.println(MAXLateness);
	}

	private static int minimizing(ArrayList A) {
		int MAXLateness = 0;
		int time = 0;
		for (int i = 0; i < A.size(); i++) {
			Node s = (Node) A.get(i);
			time += s.t;
			MAXLateness += Math.max(0, time - s.d);
		}
		return MAXLateness;
	}

}