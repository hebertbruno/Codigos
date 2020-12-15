package br.edu.icomp.ufam.lab_heranca;

public class Quadrado extends Retangulo{
	
	public Quadrado(int posX, int posY, double lado) {
		super(posX, posY, lado, lado);
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		String descricao;
		descricao = "Quadrado na posição (, ) com lado de cm (área=cm2, perímetro=cm)";
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(descricao.length() - 3, getPerimetro());
		stringBuilder.insert(descricao.length() - 18, getArea());
		stringBuilder.insert(descricao.length() - 27, largura);
		//stringBuilder.insert(descricao.length() - 42, largura);
		stringBuilder.insert(descricao.length() - 41, posY);
		stringBuilder.insert(descricao.length() - 43, posX);
		System.out.println(stringBuilder.toString());
		return(stringBuilder.toString());
		
	}
	

}
