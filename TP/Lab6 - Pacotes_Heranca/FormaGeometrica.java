package br.edu.icomp.ufam.lab_heranca;

public abstract class FormaGeometrica {
	public int posX;
	public int posY;
	
	public FormaGeometrica(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	public String getPosString(){
		String descricao;
		descricao = "posição (, )";
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(descricao.length() - 3, posX);
		stringBuilder.insert(descricao.length() - 0, posY);
		return stringBuilder.toString();
	}
	
	public abstract double getArea();
	public abstract double getPerimetro();
	
	

}
