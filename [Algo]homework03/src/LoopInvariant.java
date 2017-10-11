import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoopInvariant {

	public static void main(String[] args) throws IOException {
		// 초기조건 (Initialization)
		// 유지 조건 (Maintenance)
		// 종료 조건 (Termination)
		LoopInvariant li = new LoopInvariant();
		int[][] initA = new int[10000][2];

		String fp = "closest+data\\closest_data.txt";
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		int size = 0;
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line, " ");
			initA[size][0] = Integer.parseInt(st.nextToken()); // x 좌표 저장
			initA[size++][1] = Integer.parseInt(st.nextToken()); // y 좌표 저장
			line = br.readLine();
		}
		int[][] A = new int[size][2];
		System.arraycopy(initA, 0, A, 0, size); // 사용할 배열의 크기만큼 생성

		int[][] sortA = new int[size][2]; // merge소트를 하기위한 정렬된 배열
		li.mergeSort(A, sortA, 0, size - 1);
		// System.out.println("찾고자 하는 수 x는 표준입력을 사용하여 직접 입력");
		// int find = Scanner.nextInt();

		double leftmin = li.leftDistance(A, 0, 1,
				Math.sqrt(Math.pow((A[1][0] - A[0][0]), 2) + Math.pow((A[1][1] - A[0][1]), 2)));
		double rightmin = li.rightDistance(A, A.length / 2, (A.length / 2) + 1,
				Math.sqrt(Math.pow((A[A.length - 1][0] - A[A.length - 2][0]), 2)
						+ Math.pow((A[A.length - 1][1] - A[A.length - 2][1]), 2)));
	}

	private double leftDistance(int[][] A, int i, int j, double min) {
		double distance = Math.sqrt(Math.pow((A[i][0] - A[j][0]), 2) + Math.pow((A[i][1] - A[j][1]), 2));
		if (min > distance) {
			min = distance;
		}
		if (i >= (A.length / 2) - 1) {
			return min;
		} else if (j >= (A.length / 2) - 1) {
			leftDistance(A, ++i, i + 1, min);
		} else {
			leftDistance(A, i, ++j, min);
		}
		return min;
	}

	private double rightDistance(int[][] A, int i, int j, double min) {
		double distance = Math.sqrt(Math.pow((A[i][0] - A[j][0]), 2) + Math.pow((A[i][1] - A[j][1]), 2));
		if (min > distance) {
			min = distance;
		}
		if (i >= A.length - 2) {
			return min;
		} else if (j >= A.length - 1) {
			rightDistance(A, ++i, i + 1, min);
		} else {
			rightDistance(A, i, ++j, min);
		}
		return min;
	}

	private void mergeSort(int[][] a, int[][] sortA, int left, int right) {
		int middle;
		if (left < right) {
			middle = (left + right) / 2;
			mergeSort(a, sortA, left, middle);
			mergeSort(a, sortA, middle + 1, right);
			merge(a, sortA, left, middle, right);
		}
	}

	private void merge(int[][] a, int[][] sortA, int left, int middle, int right) {
		int i = left;
		int j = middle + 1;
		int k = left;
		int t;

		while (i <= middle && j <= right) {
			if (a[i][0] <= a[j][0]) {
				sortA[k][0] = a[i][0];
				sortA[k][1] = a[i++][1];
			} else {
				sortA[k][0] = a[j][0];
				sortA[k][1] = a[j++][1];
			}
			k++;
		}
		if (i > middle) {
			for (t = j; t <= right; t++, k++) {
				sortA[k][0] = a[t][0];
				sortA[k][1] = a[t][1];
			}
		} else {
			for (t = i; t <= middle; t++, k++) {
				sortA[k][0] = a[t][0];
				sortA[k][1] = a[t][1];
			}
		}
		for (t = left; t <= right; t++) {
			a[t][0] = sortA[t][0];
			a[t][1] = sortA[t][1];
		}
	}

}