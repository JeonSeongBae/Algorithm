import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KaratsubaAlgorithm {

	public static void main(String[] args) throws IOException {
		KaratsubaAlgorithm self = new KaratsubaAlgorithm();

		String fp = "data/data05_karatsuba.txt";
		int count = self.arrayCounting(fp);
		int[] A = new int[count];
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);

		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		while (line != null) {
			st = new StringTokenizer(line, " ");
			for (int i = 0; st.hasMoreTokens(); i++) {
				A[i] = Integer.parseInt(st.nextToken()); // x 좌표 저장
			}
			line = br.readLine();
		}
	}

	private int arrayCounting(String fp) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		int count = 0;
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		while (line != null) {
			while (st.hasMoreTokens()) {
				count++;
				st.nextToken();
			}
			line = br.readLine();
		}
		return count;
	}


}