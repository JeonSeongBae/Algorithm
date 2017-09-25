import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class heapSort {

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

	// ppt pseudo code
	public static void insertionSort(int[] arr) {
		for (int j = 1; j < arr.length; j++) {
			int key = arr[j];
			int i = j - 1;
			while (i > -1 && arr[i] > key) {
				arr[i + 1] = arr[i];
				i = i - 1;
			}
			arr[i + 1] = key;
		}
	}
}