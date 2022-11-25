import java.util.Scanner;

public class Fatorial {
	public static void main(String[] args) {
		int num;
		int res;
		int count;
		int i;
		res = 1;
		count = 1;
		Scanner teclado = new Scanner(System.in);
		num = teclado.nextInt();
		for (i = count; i <= num; i++) {
			res = res * i;
		}
		System.out.println();
		System.out.print(res);
	}
}
