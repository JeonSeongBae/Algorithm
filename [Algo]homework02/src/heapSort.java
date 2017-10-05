import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class heapSort {
	int heap_size;
	static Node[] A;

	public heapSort() {
		heap_size = 0;

		A = new Node[1024]; // heap배열의 기본크기
	}

	public class Node {
		private int priority; /* 노드에 저장되는 우선순위 */
		private String subject; /* 노드에 저장되는 과목이름 */

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

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "euc-kr"));
		StringTokenizer st;
		String line = in.readLine();

		while (line != null) {
			st = new StringTokenizer(line, ", ");
			int priority = Integer.parseInt(st.nextToken());
			String subject = st.nextToken();

			Node node = h.new Node(priority, subject);
			h.insert(A, node);
			line = in.readLine();
		}
		System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****");
		System.out.println();
		h.BULIDMAXHEAP(A);
		h.print(A);
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("1. 작업추가    2. 최대값    3. 최대 우선순위 작업 처리");
		System.out.println("4. 원소 키값 증가               5. 작업 제거          6. 종료");
		System.out.println("-----------------------------------------------");
		int select = scanner.nextInt();
		while (select != 6) {
			// 작업 추가
			if (select == 1) {
				System.out.println("신규 작업명 (20 Bytes 이내) : ");
				String newsub = scanner.nextLine();

				newsub = scanner.nextLine();
				System.out.println("우선 순위 (0~999) : ");
				int newpri = scanner.nextInt();
				Node node = h.new Node(newpri, newsub);
				h.insert(A, node);

				System.out.println("**** 작업 추가 완료 ****");
			}
			// 최대값
			else if (select == 2) {
				System.out.println("**** MAX :  " + h.max(A).priority + "," + h.max(A).subject + " ****");
			}
			// 최대 우선순위 작업 처리
			else if (select == 3) {
				Node temp = h.extract_max(A);
				System.out.println("**** " + temp.priority + ", " + temp.subject + "의 작업을 처리했습니다 ****");
			} // 원소 키값 증가
			else if (select == 4) {
				System.out.println("수정할 노드 x : ");
				int n = scanner.nextInt();
				System.out.println("수정할 key : ");
				int k = scanner.nextInt();
				h.increase_key(A, n, k);
			} // 작업제거
			else if (select == 5) {
				System.out.println("삭제할 노드 x 입력 : ");
				int n = scanner.nextInt();
				h.delete(A, n);
			} // 기타
			h.BULIDMAXHEAP(A);
			h.print(A);
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.println("1. 작업추가    2. 최대값    3. 최대 우선순위 작업 처리");
			System.out.println("4. 원소 키값 증가               5. 작업 제거          6. 종료");
			System.out.println("-----------------------------------------------");
			select = scanner.nextInt();
		}
		System.out.println("**** 프로그램 종료 ****");

	}

	private void BULIDMAXHEAP(Node[] A) {
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
		if (heap_size < 1) {
			return null;
		}
		return A[1];
	}

	public Node extract_max(Node[] A) {
		Node max = A[1];
		A[1] = A[heap_size];
		A[heap_size--] = null;
		return max;
	}

	private void increase_key(Node[] A, int x, int k) {
		A[x].priority += k;
	}

	private void delete(Node[] A, int x) {
		A[x] = A[heap_size];
		A[heap_size--] = null;
	}

	private void print(Node[] A) {
		for (int i = 1; i <= heap_size; i++) {
			System.out.println(A[i].priority + ", " + A[i].subject);
		}
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
