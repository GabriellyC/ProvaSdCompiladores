import java.util.Scanner;

public class Cod {
	public static void main(String[] args) {
		int a;
		int b;
		int c;
		Scanner teclado = new Scanner(System.in);
		a = teclado.nextInt();
		b = teclado.nextInt();
		c = a + b;
		if (a > b) {
			System.out.print(a);
		} else {
			System.out.print(b);
		}
		System.out.println();
		System.out.print("Soma de a e c: ");
		System.out.print(c);
		System.out.println();
		for (c = 1; c <= 10; c++) {
			System.out.print(c);
			System.out.println();
		}
	}
}
