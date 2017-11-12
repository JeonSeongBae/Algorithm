package practice;

import java.util.Scanner;

public class reverseString {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str;

		System.out.println("문자열을 입력하시오. :");
		str = input.nextLine();

		System.out.println(reverse(str));
	}

	private static String reverse(String str) {
		String rv = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			rv = rv + str.charAt(i);
		}
		return rv;
	}
}
