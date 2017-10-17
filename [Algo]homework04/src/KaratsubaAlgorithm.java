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
		String line = br.readLine();

		// make first array
		int bigIntA = Integer.parseInt(line);
		int lengthA = (int) (Math.log10(bigIntA) + 1);
		int[] A = new int[lengthA];

		// save data in a file in an array
		for (int i = lengthA - 1; bigIntA % 10 != 0 || bigIntA / 10 != 0; i--) {
			A[i] = bigIntA % 10;
			bigIntA /= 10;
		}

		// make second array
		line = br.readLine();
		int bigIntB = Integer.parseInt(line);
		int lengthB = (int) (Math.log10(bigIntB) + 1);
		int[] B = new int[lengthB];

		// save data in a file in an array
		for (int i = lengthB - 1; bigIntB % 10 != 0 || bigIntB / 10 != 0; i--) {
			B[i] = bigIntB % 10;
			bigIntB /= 10;
		}

	}

}