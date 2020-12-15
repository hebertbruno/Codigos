package br.edu.icomp.ufam.lab_heranca;

public class Retangulo extends FormaGeometrica {

	public double largura;
	public double altura;

	public Retangulo(int posX, int posY, double largura, double altura) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		this.largura = largura;
		this.altura = altura;
	}

	public double getArea() {
		double area = largura*altura;
		return area;
	}

	public double getPerimetro() {
		double perimetro = 2*(largura+altura);
		return perimetro;
	}
	
	public String toString(){
		String descricao;
		descricao = "Retângulo na posição (, ) com largura de cm e altura de cm (área=cm2, perímetro=cm)";
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(descricao.length() - 3, getPerimetro());
		stringBuilder.insert(descricao.length() - 18, getArea());
		stringBuilder.insert(descricao.length() - 27, altura);
		stringBuilder.insert(descricao.length() - 42, largura);
		stringBuilder.insert(descricao.length() - 59, posY);
		stringBuilder.insert(descricao.length() - 61, posX);
		System.out.println(stringBuilder.toString());
		return(stringBuilder.toString());
		
	}

}
