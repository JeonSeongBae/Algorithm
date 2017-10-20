import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MinimizingLateness {
	public class schedule implements Comparable {
		public int t;
		public int d;

		public schedule(int t, int d) {
			this.t = t;
			this.d = d;
		}

		@Override
		public int compareTo(Object o) {
			schedule temp = (schedule) o;
			if (this.d < temp.d)
				return -1;
			else if (this.d == temp.d)
				return 0;
			return 1;
		}

	}

	public static void main(String[] args) throws IOException {
		MinimizingLateness self = new MinimizingLateness();
		String fp = "data06_lateness.txt";
		// make an array of the number of tokens

		// ArrayList를 생성
		ArrayList<schedule> A = self.fileRead(fp);

		// d를 기준으로 sorting
		Collections.sort(A);

		// Lateness를 출력
		int MAXLateness = self.minimizing(A);
		System.out.println(MAXLateness);
	}

	private ArrayList fileRead(String fp) throws IOException {
		/* 파일 읽어오기 */
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);

		int count = Integer.valueOf(br.readLine());// First row is counting number
		ArrayList<schedule> A = new ArrayList();

		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		// save data in a file in an array
		while (line != null) {
			st = new StringTokenizer(line, " ");
			while (st.hasMoreTokens()) {
				int t = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				schedule temp = new schedule(t, d);
				A.add(temp);
			}
			line = br.readLine();
		}
		return A;
	}

	private int minimizing(ArrayList A) {
		int MAXLateness = 0;
		int time = 0;
		for (int i = 0; i < A.size(); i++) {
			schedule s = (schedule) A.get(i);
			time += s.t;// 소요되는 시간을 저장
			MAXLateness += Math.max(0, time - s.d); // 0보다 클 경우 MAXLateness에 값을 저장
		}
		return MAXLateness;
	}

}