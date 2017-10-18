import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountingInversions {

	public static void main(String[] args) throws IOException {
		CountingInversions self = new CountingInversions();
		for (int i = 1; i < 5; i++) {
			String fp = "data/data05_inversion_0" + i + ".txt";
			// Count the number of tokens to determine the size of the array
			int count = self.arrayCounting(fp);
			// make an array of the number of tokens
			int[] A = new int[count];
			/* 파일 읽어오기 */
			FileInputStream fis = new FileInputStream(fp);
			InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
			BufferedReader br = new BufferedReader(isr);

			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			// save data in a file in an array
			while (line != null) {
				st = new StringTokenizer(line, " ");
				for (int j = 0; st.hasMoreTokens(); j++) {
					A[j] = Integer.parseInt(st.nextToken());
				}
				line = br.readLine();
			}
			System.out.println(self.SORT_AND_COUNT(A));
		}
	}

	// A function that counts the token of a file
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

	private int SORT_AND_COUNT(int[] L) {
		// if size is 1 return 0 and L
		if (L.length == 1) {
			return 0;
		}
		int[] left = new int[L.length / 2];
		System.arraycopy(L, 0, left, 0, L.length / 2);
		int middle = L.length / 2;
		
		if (L.length % 2 != 0)
			middle++;
		
		int[] right = new int[middle];
		System.arraycopy(L, L.length / 2, right, 0, middle);
		int ra = SORT_AND_COUNT(left);
		int rb = SORT_AND_COUNT(right);
		int r = MERGE_AND_COUNT(L, left, right);
		// return sum of count and sorted L
		return ra + rb + r;
	}

	private int MERGE_AND_COUNT(int[] L, int[] left, int[] right) {
		int inverstion_count = 0;
		int indexA = 0;
		int indexB = 0;
		int indexL = 0;
		while (indexA <= left.length - 1 && indexB <= right.length - 1) {
			if (left[indexA] > right[indexB]) {
				inverstion_count += indexA + 1;
				L[indexL++] = right[indexB++];
			} else {
				L[indexL++] = left[indexA++];
			}
		}
		// save remaining elements to list
		if (indexA <= left.length - 1) {
			System.arraycopy(left, indexA, L, indexL, L.length - indexL - 1);
		} else {
			System.arraycopy(right, indexB, L, indexL, L.length - indexL - 1);
		}
		// return inverstion count
		return inverstion_count;
	}

}