import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class heapSort {
	int heap_size;
	static Node[] A;

	public heapSort() {
		heap_size = 0;
		A = new Node[1024];
	}

	public class Node {
		private int priority; /* 노드에 저장되는 정수 */
		private String subject; /* next 노드 */

		public Node(int x) {
			this.priority = x;
		}

		public Node(int x, String subject) {
			this.priority = x;
			this.subject = subject;
		}
	}

	public static void main(String[] args) throws IOException {
		heapSort h = new heapSort();
		// fileReader
		Scanner scanner = new Scanner(System.in);
		String filepath = "input2.txt";
		FileReader file = new FileReader(filepath);
		// BufferedReader in = new BufferedReader(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "euc-kr"));
		StringTokenizer st;
		// = new StringTokenizer(in.readLine(), " ");
		// int count = 0;
		// while (st.hasMoreTokens()) {
		// count++;
		// st.nextToken();
		// }
		// int[] arr = new int[count];

		file = new FileReader(filepath);
		in = new BufferedReader(file);
		System.out.println("파일 읽기 시작");

		String line = in.readLine();

		while (line != null) {
			st = new StringTokenizer(line, ", ");
			int priority = Integer.parseInt(st.nextToken());
			String subject = st.nextToken();

			String newsub = scanner.nextLine();
			int newpri = scanner.nextInt();
			Node node = h.new Node(newpri, newsub);
			h.insert(A, node);
			line = in.readLine();
		}
		System.out.println("파일 읽기 완료");
		int select = 0;
		while (select != 6) {
			h.BULIDMAXHEAP(A);

			// 작업 추가
			if (select == 1) {
				System.out.println("신규 작업명 (20 Bytes 이내) : ");
				String newsub = scanner.nextLine();
				System.out.println("우선 순위 (0~999) : ");
				int newpri = scanner.nextInt();
				Node node = h.new Node(newpri, newsub);
				h.insert(A, node);
			}
			// 최대값
			else if (select == 2) {
			}
			// 최대 우선순위 작업 처리
			else if (select == 3) {
			} // 원소 키값 증가
			else if (select == 4) {

			} // 작업제거
			else if (select == 5) {

			} // 종료
			else if (select == 6) {
				break;
			} // 기타
			else {
			}
		}
	}

	private void BULIDMAXHEAP(Node[] A) {
		// heap_size = A.length;
		for (int i = heap_size; i >= 1; i--) {
			MAXHEAPIFY(A, i);
		}
	}

	private void MAXHEAPIFY(Node[] A, int i) {
		// TODO Auto-generated method stub
		int L = LEFTCHILD(i);
		int R = RIGHTCHILD(i);
		int largest;
		if (L <= heap_size && A[L].priority > A[i].priority) {
			largest = L;
		} else {
			largest = i;
		}
		if (R <= heap_size && A[R].priority > A[largest].priority) {
			largest = R;
		}
		if (largest != i) {
			Node temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			MAXHEAPIFY(A, largest);
		}
	}

	public void insert(Node[] A, Node x) {
		A[++heap_size] = x;
	}

	public Node max(Node[] A) {
		return A[1];
	}

	public Node extract_max(Node[] A) {
		Node max = A[1];
		A[1] = A[heap_size];
		A[heap_size--] = null;
		return max;
	}

	private void increase_key(Node[] A, Node x, int k) {

	}

	private void delete(Node[] A, Node x) {
		// TODO Auto-generated method stub

	}

	public static int PARENT(int i) {
		return i / 2;
	}

	public static int LEFTCHILD(int i) {
		return 2 * i;
	}

	public static int RIGHTCHILD(int i) {
		return (2 * i) + 1;
	}

}
