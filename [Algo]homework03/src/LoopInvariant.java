import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.crypto.SealedObject;

public class LoopInvariant {

	public static void main(String[] args) throws IOException {
		LoopInvariant li = new LoopInvariant();
		Scanner scanner = new Scanner(System.in);
		String fp = "invariant_data.txt";
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int count = 0;
		while (st.hasMoreTokens()) {
			count++;
			st.nextToken();
		}
		int[] A = new int[count];
		int[] sortedA = new int[count];
		fis = new FileInputStream(fp);
		isr = new InputStreamReader(fis, "euc-kr");
		br = new BufferedReader(isr);
		st = new StringTokenizer(br.readLine(), " ");
		// System.out.println("파일 읽기 시작");
		for (int i = 0; st.hasMoreTokens(); i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		li.mergeSort(A, sortedA, 0, A.length - 1);
		System.out.println("찾고자 하는 수 x를 입력하세요.");
		int x = scanner.nextInt();
		li.Search(A, A.length, x);
	}

	int Search(int A[], int length, int x) {

		// 초기조건 (Initialization)
		// 유지 조건 (Maintenance)
		// 종료 조건 (Termination)
		int left = 0, right = length - 1, middle;

		while (left < right) {
			middle = (left + right) / 2;
			if (A[middle] < x)
				left = middle + 1;
			else if (A[middle] > x)
				right = middle - 1;
			else
				return middle;
		}
		return -1;
	}

	public static void mergeSort(int[] A, int[] sortedA, int left, int right) {
		int middle;
		if (left < right) {
			middle = (left + right) / 2;
			mergeSort(A, sortedA, left, middle);
			mergeSort(A, sortedA, middle + 1, right);
			merge(A, sortedA, left, middle, right);
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