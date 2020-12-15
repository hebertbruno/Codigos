import java.util.ArrayList;
import java.util.Scanner;

public class OperacoesInteiros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> vetor = new ArrayList<Integer>();

		ArrayList<Integer> vetorSize = new ArrayList<Integer>();
		ArrayList<Integer> Par = new ArrayList<Integer>();
		ArrayList<Integer> Impar = new ArrayList<Integer>();
		ArrayList<Integer> Sum = new ArrayList<Integer>();
		ArrayList<Float> Med = new ArrayList<Float>();
		ArrayList<Integer> Maior = new ArrayList<Integer>();
		ArrayList<Integer> Menor = new ArrayList<Integer>();

		int entrada = 0;
		int indice=0;
		int controlador = 0;
		do{
			int  contPar =0, contImpar = 0, soma = 0, maior = 0, menor = 0;
			entrada = 0;

			while (entrada!=-1){
				entrada = scan.nextInt();
				/*Fazer verificação de quem e o maior valor e o menor valor*/

				if(entrada!=-1){
					if(vetor.size()<1)menor = entrada;
					vetor.add(entrada);
					soma = entrada + soma;
					if(maior<entrada){
						maior = entrada;
					}
					if(menor>entrada){
						menor = entrada;
					}
					if(entrada %2 ==0){
						contPar = contPar+1;
					}
					else{
						contImpar = contImpar+1;
					}
				}
			}
			controlador = (vetor.size()) - controlador;
		//	System.out.printf("Tamanho do vetor: %d\n controlador: %d\n ", vetor.size(), controlador);

			float media = soma/controlador; //obter media dos valores
			/*Obter quantidade de numeros pares e impares*/
		/*	for(Integer num : vetor){
			if(num %2 ==0){
				contPar = contPar+1;
			}
			else{
				contImpar = contImpar+1;
			}
		}
				/*Vetores separados contendo tamanho de cada vetor, qntd pares, impares, media, soma... */
				vetorSize.add(controlador);

				Par.add(contPar);

				Impar.add(contImpar);

				Sum.add(soma);

				Med.add(media);

				Maior.add(maior);

				Menor.add(menor);

					System.out.println(vetorSize.get(indice));
					System.out.println(Par.get(indice));
					System.out.println(Impar.get(indice));
					System.out.println(Sum.get(indice));
					System.out.println(Med.get(indice));
					System.out.println(Maior.get(indice));
					System.out.println(Menor.get(indice));


				indice = indice+1;
			}while(controlador!=0);


		}
}
