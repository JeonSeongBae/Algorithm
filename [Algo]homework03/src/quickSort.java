import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class quickSort {

	public static void main(String[] args) throws IOException {
		// fileReader
		String filepath = "data/Worst/10000.txt";
		FileReader file = new FileReader(filepath);
		BufferedReader br = new BufferedReader(file);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int count = 0;
		while (st.hasMoreTokens()) {
			count++;
			st.nextToken();
		}
		int[] arr = new int[count];

		file = new FileReader(filepath);
		br = new BufferedReader(file);
		st = new StringTokenizer(br.readLine(), " ");
		// System.out.println("파일 읽기 시작");
		for (int i = 0; st.hasMoreTokens(); i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// System.out.println("파일 읽기 완료");
		// Sorting
		long start = System.currentTimeMillis();
		// System.out.println("시작시간: " + start);
		QUICKSORT(arr, 0, arr.length - 1);
		long end = System.currentTimeMillis();
		// System.out.println("종료시간: " + end);
		System.out.println(end - start);

		// fileWriter
		BufferedWriter out = new BufferedWriter(new FileWriter("201302476_output.txt"));
		for (int i = 0; i < arr.length; i++) {
			out.write(arr[i] + " ");
		}
		out.close();
	}

	private static int PARTITION(int[] A, int p, int r) {
		int x = A[r];
		int i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= x) {
				i = i + 1;
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;

			}
		}
		i = i + 1;
		int temp = A[i];
		A[i] = A[r];
		A[r] = temp;
		return i;
	}

	private static void QUICKSORT(int[] A, int p, int r) {
		// TODO Auto-generated method stub
		if (p < r) {
			int q = PARTITION(A, p, r);
			QUICKSORT(A, p, q - 1);
			QUICKSORT(A, q + 1, r);
		}
	}
}