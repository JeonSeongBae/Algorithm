import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class merge {
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
		int[] sorted = new int[count];
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
		mergeSort(arr, sorted, 0, arr.length - 1);
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

	public static void mergeSort(int[] arr, int[] sorted, int left, int right) {
		int middle;
		if (left < right) {
			middle = (left + right) / 2;
			mergeSort(arr, sorted, left, middle);
			mergeSort(arr, sorted, middle + 1, right);
			merge(arr, sorted, left, middle, right);
		}
	}

	public static void merge(int[] arr, int[] sorted, int left, int middle, int right) {
		int i = left;
		int j = middle + 1;
		int k = left;
		int t;

		while (i <= middle && j <= right) {
			if (arr[i] <= arr[j]) {
				sorted[k] = arr[i++];
			} else {
				sorted[k] = arr[j++];
			}
			k++;
		}
		if (i > middle) {
			for (t = j; t <= right; t++, k++) {
				sorted[k] = arr[t];
			}
		} else {
			for (t = i; t <= middle; t++, k++) {
				sorted[k] = arr[t];
			}
		}
		for (t = left; t <= right; t++) {
			arr[t] = sorted[t];
		}
	}
}