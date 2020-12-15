import java.util.Scanner;

public class Media {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		float a = scan.nextFloat();
		float b = scan.nextFloat();
		float c = scan.nextFloat();
		
		float media = (a+b+c)/3f;
		
		System.out.printf("%.2f\n", media);
	}

}
