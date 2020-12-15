import java.util.Scanner;

public class Fahrenheit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		float tempC = scan.nextFloat();
		
		float tempF = (9*tempC/5)+32f;
		
		System.out.printf("%.1f\n", tempF);

	}

}
