import java.util.Scanner;

public class Main {

	public static class Shape {
		int width;
		int height;

		public void Shape(int width, int height) {
			this.width = width;
			this.height = height;
		}

		public int area() {
			return 0;
		}

	}

	public static class Rectangle extends Shape {

		@Override
		public int area() {
			return width * height;
		}
	}

	public static class Triangle extends Shape {
		int tri;

		@Override
		public int area() {
			return width * height / 2;
		}
	}

	public static class Circle extends Shape {

		@Override
		public int area() {
			return (int) (width * width * Math.PI);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("width를 입력하세요.");
		int width = input.nextInt();
		System.out.println("height를 입력하세요.");
		int height = input.nextInt();

		Shape shape[] = new Shape[3];
		Shape a = new Triangle();

		Triangle triangle = new Triangle();
		triangle.Shape(width, height);
		shape[0] = triangle;

		Rectangle rectangle = new Rectangle();
		rectangle.Shape(width, height);
		shape[1] = rectangle;

		Circle circle = new Circle();
		circle.Shape(width, height);
		shape[2] = circle;

		for (int i = 0; i < shape.length; i++) {
			System.out.println(shape[i].area());
		}
	}

}
