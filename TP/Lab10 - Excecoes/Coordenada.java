package br.edu.ufam.icomp.lab_excecoes;

public class Coordenada {
	private int posX, posY, digito;
	
	public Coordenada(int posX, int posY, int digito) throws CoordenadaNegativaException, 
															 CoordenadaForaDosLimitesException, 
															 DigitoInvalidoException {
		int resto;
		
		if (posX < 0 || posY < 0) throw new CoordenadaNegativaException();
		if (posX < 0 || posX > 30000 || posY < 0 || posY > 30000) throw new CoordenadaForaDosLimitesException();
		
		resto = (posX + posY) % 10;		
		if (resto != digito) throw new DigitoInvalidoException();
		
		this.posX = posX;
		this.posY = posY;
		this.digito = digito;		
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public double distancia(Coordenada coordenada) {
		return Math.sqrt(Math.pow(posX - coordenada.posX, 2) + Math.pow(posY - coordenada.posY, 2));
	}
	
	public String toString() {
		return posX + ", " + posY;
	}
}
