import java.util.Scanner;

public class MediaColecao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		float entrada = 0, numeros = 0;
		int cont = 0;
		Scanner scan = new Scanner(System.in);		
		entrada = scan.nextFloat();
		
		while(entrada!=-1){
			numeros = numeros + entrada;
			cont = cont+1;
			entrada = scan.nextFloat();
		}
		
		float media = numeros/cont;
		
		System.out.printf("%.2f\n", media);

		
	}

}
