package practice;
import java.util.Scanner;
public class accountMain {
	public static void main(String[] args) {
		account account = new account();
		
		makeAccout(account);
		checkID(account);
	}

	private static void makeAccout(account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("<계정생성>");

		System.out.println("ID를 입력하세요.");
		String id = scanner.nextLine();
		account.setID(id);

		System.out.println("Password를 입력하세요.");
		String password = scanner.nextLine();
		account.setPassword(password);

	}

	private static void checkID(account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("<로그인>");
		System.out.println("ID:");
		String id = scanner.nextLine();
		
		System.out.println("PW:");
		String password = scanner.nextLine();
		if (account.getID().equals(id) && account.getPassword().equals(password)) {
			System.out.println("로그인에 성공하였습니다.");
		} else {
			System.out.println("로그인에 실패하였습니다.");
		}
	}

}
