package br.edu.icomp.ufam.lab_heranca;

abstract class Circulo extends FormaGeometrica{
	
	public double raio;
	
	public Circulo(int posX, int posY, double raio) {
		super(posX, posY);
		this.raio = raio;
	}

	
	public double getArea(){
		double pi = Math.PI;
		double area = pi*raio*raio;
		return  area;
	}
	
	public double getPerimetro(){
		double pi = Math.PI;
		double perimetro = 2*pi*raio; 
		return perimetro;
	}
	
	public String toString(){
		String descricao;
		descricao = "Círculo na posição (, ) com raio de cm (área=cm2, perímetro=cm)";
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(descricao.length() - 3, getPerimetro());
		stringBuilder.insert(descricao.length() - 18, getArea());
		stringBuilder.insert(descricao.length() - 27, raio);
		stringBuilder.insert(descricao.length() - 41, posY);
		stringBuilder.insert(descricao.length() - 43, posX);
		System.out.println(stringBuilder.toString());
		return(stringBuilder.toString());
		
	}
}
