import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class binaryInsertion {

	public static void main(String[] args) throws IOException {
		// fileReader
		String filepath = "input.txt";
		FileReader file = new FileReader(filepath);
		BufferedReader in = new BufferedReader(file);
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int count = 0;
		while (st.hasMoreTokens()) {
			count++;
			st.nextToken();
		}
		int[] arr = new int[count];

		file = new FileReader(filepath);
		in = new BufferedReader(file);
		st = new StringTokenizer(in.readLine(), " ");
		// System.out.println("파일 읽기 시작");
		for (int i = 0;st.hasMoreTokens();i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// System.out.println("파일 읽기 완료");

		// Sorting
		long start = System.currentTimeMillis();
		// System.out.println("시작시간: " + start);
		insertionSort(arr);
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

	public static void insertionSort(int[] arr) {
		int[] temp = new int[arr.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = arr[i];
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			int left = 0, right = arr.length;
			int mid;
			while (right >= left) {
				mid = (left + right) / 2;
				// 중간값보다 클 때
				if (temp[i] > arr[mid]) {
					left = mid + 1;
				}
				// 중간값보다 작을 때
				else if (temp[i] <= arr[mid]) {
					right = mid - 1;
				}
			}
			int key = temp[i];
			System.arraycopy(arr, left, arr, left + 1, arr.length - left - 1);
			arr[left] = key;
		}

	}

}