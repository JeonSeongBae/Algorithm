import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KaratsubaAlgorithm {

	public static void main(String[] args) throws IOException {
		KaratsubaAlgorithm self = new KaratsubaAlgorithm();
		String fp = "data/data05_karatsuba.txt";
		// Count the number of tokens to determine the size of the array
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		int count = 0;
		String line = br.readLine();
		int bigIntA = Integer.parseInt(line);
		int lengthA = (int) (Math.log10(bigIntA) + 1);
		int[] A = new int[lengthA];
		for (int i = lengthA - 1; bigIntA % 10 != 0 || bigIntA / 10 != 0; i--) {
			A[i] = bigIntA % 10;
			bigIntA /= 10;
		}

		line = br.readLine();
		int bigIntB = Integer.parseInt(line);
		int lengthB = (int) (Math.log10(bigIntB) + 1);
		int[] B = new int[lengthB];
		for (int i = lengthB - 1; bigIntB % 10 != 0 || bigIntB / 10 != 0; i--) {
			A[i] = bigIntB % 10;
			bigIntB /= 10;
		}

		// make an array of the number of tokens
		/* 파일 읽어오기 */
		fis = new FileInputStream(fp);
		isr = new InputStreamReader(fis, "euc-kr");
		br = new BufferedReader(isr);

		line = br.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		// save data in a file in an array
		while (line != null) {
			st = new StringTokenizer(line, " ");
			for (int j = 0; st.hasMoreTokens(); j++) {
				A[j] = Integer.parseInt(st.nextToken());
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
		int bigInt = Integer.parseInt(line);
		while (bigInt % 10 != 0 && bigInt / 10 != 0) {

		}
		StringTokenizer st = new StringTokenizer(line, "");
		while (st.hasMoreTokens()) {
			count++;
			st.nextToken();
		}
		return count;
	}

}