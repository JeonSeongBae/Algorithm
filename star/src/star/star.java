package star;

import java.util.Random;
import java.util.Scanner;

public class star {

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());

		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		int num5 = 0;
		int num6 = 0;

		for (int i = 0; i < 6; i++) {
			switch (i) {
			// first
			case 0:
				if (num1 == 0) {
					num1 = rand.nextInt(45) + 1;
				}
				break;
			// second
			case 1:
				if (num2 == 0) {
					num2 = rand.nextInt(45) + 1;
				}
				while (num1 == num2) {
					num2 = rand.nextInt(45) + 1;
				}
				break;
			// third
			case 2:
				if (num3 == 0) {
					num3 = rand.nextInt(45) + 1;
				}
				while (num1 == num3 || num2 == num3) {
					num3 = rand.nextInt(45) + 1;
				}
				break;
			// fourth
			case 3:
				if (num4 == 0) {
					num4 = rand.nextInt(45) + 1;
				}
				while (num1 == num4 || num2 == num4 || num3 == num4) {
					num4 = rand.nextInt(45) + 1;
				}
				break;
			// fifth
			case 4:
				if (num5 == 0) {
					num5 = rand.nextInt(45) + 1;
				}
				while (num1 == num5 || num2 == num5 || num3 == num5 || num4 == num5) {
					num5 = rand.nextInt(45) + 1;
				}
				break;
			// sixth
			case 5:
				if (num6 == 0) {
					num6 = rand.nextInt(45) + 1;
				}
				while (num1 == num6 || num2 == num6 || num3 == num6 || num4 == num6 || num5 == num6) {
					num6 = rand.nextInt(45) + 1;
				}
				break;
			}
		}

		System.out.println("<로또 생성 프로그램>");
		System.out.println(num1 + " " + num2 + " " + num3 + " " + num4 + " " + num5 + " " + num6);

	}
}
