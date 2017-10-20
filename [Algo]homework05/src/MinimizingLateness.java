import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinimizingLateness {
	public class schedule implements Comparable<schedule> {
		public int t;
		public int d;

		public schedule(int t, int d) {
			this.t = t;
			this.d = d;
		}

		@Override
		public int compareTo(schedule s) {
			if (this.d == s.d) {
				return 0;
			} else if (this.d < s.d) {
				return -1;
			} else {
				return 1;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		MinimizingLateness self = new MinimizingLateness();
		String fp = "data06_lateness.txt";
		// make an array of the number of tokens

		// ArrayList를 생성
		schedule[] scheldule = self.fileRead(fp);

		// d를 기준으로 sorting
		Arrays.sort(scheldule);

		// Lateness를 출력
		int MAXLateness = self.minimizing(scheldule);
		System.out.println(MAXLateness);
	}

	private schedule[] fileRead(String fp) throws IOException {
		/* 파일 읽어오기 */
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		int count = Integer.valueOf(br.readLine());// First row is counting number
		schedule[] schedule = new schedule[count];
		String line = br.readLine();
		// save data in a file in an array
		for (int i = 0; line != null; i++) {
			st = new StringTokenizer(line, " ");
			int t = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			schedule temp = new schedule(t, d);
			schedule[i] = temp;
			line = br.readLine();
		}
		return schedule;
	}

	private int minimizing(schedule[] A) {
		int MAXLateness = 0;
		int time = 0;
		for (int i = 0; i < A.length; i++) {
			schedule s = (schedule) A[i];
			time += s.t;// 소요되는 시간을 저장
			MAXLateness += Math.max(0, time - s.d); // 0보다 클 경우 MAXLateness에 값을 저장
		}
		return MAXLateness;
	}

}