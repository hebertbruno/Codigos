package br.edu.ufam.icomp.lab_encapsulamento;

public class GISMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Localizavel [] vetorLocalizacao = new Localizavel[10];
		
		Celular celular1 = new Celular(554, 225, 92292956);
		vetorLocalizacao[0] = celular1;
		
		Celular celular2 = new Celular(1152, 1, 81105359);
		vetorLocalizacao[1] = celular2;
		
		Celular celular3 = new Celular(400, 80, 92292525);
		vetorLocalizacao[2] = celular3;
		
		Celular celular4 = new Celular(2, 440, 92293080);
		vetorLocalizacao[3] = celular4;
		
		Celular celular5 = new Celular(90, 35, 81884455);
		vetorLocalizacao[4] = celular5;
		
		CarroLuxuoso carro1 = new CarroLuxuoso("ABC123");
		vetorLocalizacao[5] = carro1;
		
		CarroLuxuoso carro2 = new CarroLuxuoso("DEF456");
		vetorLocalizacao[6] = carro2;
		
		CarroLuxuoso carro3 = new CarroLuxuoso("GHI789");
		vetorLocalizacao[7] = carro3;
		
		CarroLuxuoso carro4 = new CarroLuxuoso("JKL000");
		vetorLocalizacao[8] = carro4;
		
		CarroLuxuoso carro5 = new CarroLuxuoso("MNO951");
		vetorLocalizacao[9] = carro5;
		
		for(int i = 0; i<10; i++){
			System.out.println(vetorLocalizacao[i].getPosicao());
		}
		
	}

}
