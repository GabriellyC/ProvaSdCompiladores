import java.util.Scanner;
public class Tabuada {
public static void main(String[] args){
int num;
int res;
int count;
int i;
res = 1;
count = 1;
System.out.print( "Tabuada do numero: ");
Scanner teclado = new Scanner(System.in);
 num= teclado.nextInt();
for(i=count;i<=10;i++){
res = num * i;
System.out.print( i);
System.out.print( " x ");
System.out.print( num);
System.out.print( " igual a ");
System.out.print( res);
System.out.println();
}
System.out.println();
}
}
