import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HuffmanCode {
	String baseHuffman = "";

	public class Node {
		private String alpha; /* 알파벳 */
		private int frequency; /* 빈도수 */
		private Node left; // 왼쪽 가지
		private Node right;// 오른쪽 가지
		private String huffman;// 허프만 코드

		public Node(String alpha) {
			this.alpha = alpha; /* 알파벳 */
			this.frequency = 1; /* 빈도수 */
			this.left = null; // 왼쪽 가지
			this.right = null;// 오른쪽 가지
			this.huffman = "";// 허프만 코드
		}

		public Node() {
			this.alpha = ""; /* 알파벳 */
			this.frequency = 0; /* 빈도수 */
			this.left = null; // 왼쪽 가지
			this.right = null;// 오른쪽 가지
			this.huffman = "";// 허프만 코드
		}
	}

	public static void main(String[] args) throws IOException {
		HuffmanCode self = new HuffmanCode();

		String fp = "data06_huffman.txt";
		ArrayList<Node> c = self.fileRead(fp);
		Node min = self.huffman(c);
		System.out.println(min.frequency);
		printHuffman(min);
	}

	private static void printHuffman(Node min) {
		if (min.left != null) {// 좌측 가지는 0
			printHuffman(min.left);
		}
		if (min.right != null) { // 우측 가지는 1
			printHuffman(min.right);
		}
		if (!min.alpha.equals("")) {
			System.out.println(min.alpha + min.huffman);
		}
	}

	private ArrayList<Node> fileRead(String fp) throws IOException {
		// fileReader
		Scanner scanner = new Scanner(System.in);
		FileInputStream fis = new FileInputStream(fp);
		InputStreamReader isr = new InputStreamReader(fis, "euc-kr");
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		String line = br.readLine();
		ArrayList<Node> A = new ArrayList<Node>(); // 알파벳과 빈도수가 저장될 ArrayList
		ArrayList<String> B = new ArrayList<String>(); // 사용되는 문자열을 저장하여 포함되어있는지 확인
		while (line != null) {
			for (int i = 0; i < line.length(); i++) {
				String s = line.substring(i, i + 1);
				if (B.contains(s)) {
					// 포함되어 있음
					for (int j = 0; j < A.size(); j++) {
						Node tempCompare = A.get(j);
						if (tempCompare.alpha.equals(s)) {
							tempCompare.frequency++;
							break;
						}
					}
				} else {
					// 포함되어있지 않음
					Node tempAdd = new Node(s);
					A.add(tempAdd); // Node리스트에 추가
					B.add(s); // 포함되어있는 리스트에 추가
				}
			}
			line = br.readLine();
		}
		return A;
	}

	private Node huffman(ArrayList<Node> c) {
		int n = c.size();

		c.add(0, new Node(""));
		buildminheap(c);
		Node left;
		Node right;
		for (int i = 1; i <= n - 1; i++) {
			Node z = new Node();
			z.left = left = extract_min(c);
			z.right = right = extract_min(c);
			z.frequency = left.frequency + right.frequency;
			insert(c, z);
		}
		Node min = extract_min(c);
		huffmancode(min);
		return min;
	}

	private void insert(ArrayList<Node> c, Node z) {
		c.add(1, z);
		buildminheap(c);
	}

	private void buildminheap(ArrayList<Node> c) {
		for (int i = c.size() / 2; i >= 1; i--) {
			minheapify(c, i);
		}
	}

	private void minheapify(ArrayList<Node> c, int i) {
		int l = leftchild(i);
		int r = rightchild(i);
		int largest;
		if (l <= c.size() - 1 && c.get(l).frequency <= c.get(i).frequency) {
			largest = l;
		} else {
			largest = i;
		}
		if (r <= c.size() - 1 && c.get(r).frequency <= c.get(largest).frequency) {
			largest = r;
		}
		if (largest != i) {
			Node temp = c.get(i);
			c.set(i, c.get(largest));
			c.set(largest, temp);
			minheapify(c, largest);
		}
	}

	public Node extract_min(ArrayList<Node> c) {
		Node min = c.remove(1); // 1을 추출
		buildminheap(c);// minheap으로 다시 만들어줌
		return min;
	}

	public static int parent(int i) {
		return i / 2;
	}

	public static int leftchild(int i) {
		return 2 * i;
	}

	public static int rightchild(int i) {
		return (2 * i) + 1;
	}

	/* 허프만 코드를 구성하여 저장하는 메소드 */
	private void huffmancode(Node c) {
		/*
		 * 각각의 문자에 대한 코드는 그 문자에 대한 루트-리프 경로에 의해 결정되는데, 왼쪽 가지는 “0”으로 표시되고, 오른쪽 가지는 “1”로
		 * 표시됨
		 */
		c.huffman = baseHuffman;// 허프만 코드를 각 노드에 저장
		if (c.left != null) {// 좌측 가지는 0
			baseHuffman += "0";
			huffmancode(c.left);
		}
		if (c.right != null) { // 우측 가지는 1
			baseHuffman += "1";
			huffmancode(c.right);
		}
		if (baseHuffman.length() != 0) // 마지막 String을 제거
			baseHuffman = baseHuffman.substring(0, baseHuffman.length() - 1);

	}
}
