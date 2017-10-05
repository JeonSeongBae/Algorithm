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
		int[][] A = new int[10][2];
		Scanner Scanner = new Scanner(System.in);
		String fp = "closest+data\\closest_data.txt";
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		for (int i = 0; line != null; i++) {
			StringTokenizer st = new StringTokenizer(line, " ");
			A[i][0] = Integer.parseInt(st.nextToken());
			A[i][1] = Integer.parseInt(st.nextToken());
			line = br.readLine();
		}

		System.out.println("찾고자 하는 수 x는 표준입력을 사용하여 직접 입력");
		int find = Scanner.nextInt();

	}

}
