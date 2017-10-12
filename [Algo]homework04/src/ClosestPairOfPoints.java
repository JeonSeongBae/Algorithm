import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ClosestPairOfPoints {

	static int size = 0;

	public static void main(String[] args) throws IOException {
		ClosestPairOfPoints li = new ClosestPairOfPoints();
		double[][] initA = new double[10000][2];

		String fp = "closest+data\\closest_10000.in";
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line, " ");
			initA[size][0] = Double.parseDouble(st.nextToken()); // x 좌표 저장
			initA[size++][1] = Double.parseDouble(st.nextToken()); // y 좌표 저장
			line = br.readLine();
		}
		double[][] A = new double[size][2];
		System.arraycopy(initA, 0, A, 0, size); // 사용할 배열의 크기만큼 생성

		double[][] sortA = new double[size][2]; // merge소트를 하기위한 정렬된 배열
		li.mergeSort(A, sortA, 0, size - 1, "X");

		double leftmin = li.Closest_pair(A, 0, 1,
				Math.sqrt(Math.pow((A[1][0] - A[0][0]), 2) + Math.pow((A[1][1] - A[0][1]), 2)));
		// System.out.println("leftmin : " + leftmin);
		double rightmin = li.Closest_pair(A, A.length / 2, (A.length / 2) + 1,
				Math.sqrt(Math.pow((A[A.length - 1][0] - A[A.length - 2][0]), 2)
						+ Math.pow((A[A.length - 1][1] - A[A.length - 2][1]), 2)));
		// System.out.println("rightmin:" + rightmin);

		double δ = Double.min(leftmin, rightmin);
		// System.out.println("range:" + range);

		// System.out.println("mid:" + A[size / 2][0]);

		double leftrange = (double) (A[size / 2][0]) - δ;
		// System.out.println("leftrange:" + leftrange);

		double rightrange = (double) (A[size / 2][0]) + δ;
		// System.out.println("rightrange:" + rightrange);
		int i, j;

		for (i = A.length / 2; A[i][0] > leftrange; i--)
			;
		for (j = A.length / 2; A[j][0] < rightrange; j++)
			;
		double[][] yA = new double[j - i][2];
		for (int k = i; k < j; k++) {
			yA[k - i][0] = A[k][0];
			yA[k - i][1] = A[k][1];
		}
		double[][] sortyA = new double[yA.length][2]; // merge소트를 하기위한 정렬된 배열
		li.mergeSort(yA, sortA, 0, yA.length - 1, "Y");
		for (int k = 0; k < sortyA.length - 1; k++) {
			if ((yA[k][1] - yA[k + 1][1]) < δ
					&& Math.sqrt(Math.pow((yA[k][0] - yA[k + 1][0]), 2) + Math.pow((yA[k][1] - yA[k + 1][1]), 2)) < δ)
				δ = Math.sqrt(Math.pow((yA[k][0] - yA[k + 1][0]), 2) + Math.pow((yA[k][1] - yA[k + 1][1]), 2));
		}

		// System.out.println("leftindex:" + i);
		// System.out.println("rightindex:" + j);

		double windowmin = li.Closest_pair(A, i, j, δ);
		// System.out.println("windowmin:" + windowmin);

		System.out.println("최 근접 거리: " + Double.min(δ, windowmin));
	}

	private double Closest_pair(double[][] a, int i, int j, double min) {
		double distance = Math.sqrt(Math.pow((a[i][0] - a[j][0]), 2) + Math.pow((a[i][1] - a[j][1]), 2));
		if (min > distance) {
			min = distance;
		}
		if (i >= size / 2) {
			return min;
		} else if (j >= (a.length / 2) - 1) {
			return Closest_pair(a, ++i, i + 1, min);
		} else {
			return Closest_pair(a, i, ++j, min);
		}
	}

	private void mergeSort(double[][] a, double[][] sortA, int left, int right, String XY) {
		int middle;
		if (left < right) {
			middle = (left + right) / 2;
			mergeSort(a, sortA, left, middle, XY);
			mergeSort(a, sortA, middle + 1, right, XY);
			merge(a, sortA, left, middle, right, XY);
		}
	}

	private void merge(double[][] a, double[][] sortA, int left, int middle, int right, String XY) {

		int i = left, j = middle + 1, k = left, t;

		while (i <= middle && j <= right) {

			if (a[i][0] <= a[j][0] && XY.equals("X")) {
				sortA[k][0] = a[i][0];
				sortA[k++][1] = a[i++][1];
			} else if (a[i][1] <= a[j][1] && XY.equals("Y")) {
				sortA[k][0] = a[i][0];
				sortA[k++][1] = a[i++][1];
			} else {
				sortA[k][0] = a[j][0];
				sortA[k++][1] = a[j++][1];
			}
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