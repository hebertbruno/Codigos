package br.edu.ufam.icomp.lab_excecoes;

public class TamanhoMaximoExcedidoException extends RoverCaminhoException {
	private static final long serialVersionUID = 1L;
	
	public TamanhoMaximoExcedidoException() {
		this("Quantidade m�xima de coordenadas excedida");
	}
	
	public TamanhoMaximoExcedidoException(String s) {
		super(s);
	}
}
