package br.edu.icomp.ufam.lab_heranca;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double raio = 4;
		int posX = 1;
		int posY = 2;
		getArea(raio);
		getPerimetro(raio);
		toString(posX, posY, raio);
		
			

}
	public static double getArea(double raio){
		return  Math.PI*raio*raio;
	}
	
	public static double getPerimetro(double raio){
		return 2*Math.PI*raio;
	}
	
	public static String toString(int posX, int posY, double raio){
		String descricao;
		descricao = "Círculo na posição (, ) com raio de cm (área=cm2, perímetro=cm)";
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(descricao.length() - 3, getPerimetro(raio));
		stringBuilder.insert(descricao.length() - 18, getArea(raio));
		stringBuilder.insert(descricao.length() - 27, raio);
		stringBuilder.insert(descricao.length() - 41, posY);
		stringBuilder.insert(descricao.length() - 43, posX);
		System.out.println(stringBuilder.toString());
		return(stringBuilder.toString());
	}
}