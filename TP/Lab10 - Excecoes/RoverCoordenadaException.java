package br.edu.ufam.icomp.lab_excecoes;

public class RoverCoordenadaException extends RoverException {
private static final long serialVersionUID = 1L;
	
	public RoverCoordenadaException() {
		this("Exce��o geral de coordenada do rover");
	}
	
	public RoverCoordenadaException(String s) {
		super(s);
	}
}
