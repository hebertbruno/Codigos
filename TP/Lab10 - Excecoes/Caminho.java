package br.edu.ufam.icomp.lab_excecoes;

public class Caminho {
	private Coordenada[] caminho;
	private int tamanho;
	
	public Caminho(int maxTam) {
		tamanho = maxTam;
		caminho = new Coordenada[maxTam];
	}
	
	public int tamanho() {
		int cont = 0;
		
		if (caminho.length == 0)
			return 0;
		else {
			for (int i = 0; i < tamanho; i++) {
				if (caminho[i] != null)
					cont++;
			}
		return cont;
		}
	}
	
	public void addCoordenada(Coordenada coordenada) throws TamanhoMaximoExcedidoException, 
															DistanciaEntrePontosExcedidaException {
		
		if (tamanho() >= tamanho) throw new TamanhoMaximoExcedidoException();
		if (tamanho() > 0 && (caminho[tamanho()-1].distancia(coordenada) > 15)) throw new DistanciaEntrePontosExcedidaException();
		
		caminho[tamanho()] = coordenada;		
	}
	
	public void reset() {
		for (int i = 0; i < tamanho(); i++) 
			caminho[i] = null;
		tamanho = 0;
	}
	
	public String toString() {
		String descricao1 = "Dados do caminho:\n  - Quantidade de pontos: " + tamanho()
						   + "\n  - Pontos:";
		String descricao2 = "";
		String descricaoFinal;
		for (int i = 0; i < tamanho(); i++) 
			descricao2 = descricao2 + "    -> " + caminho[i].getPosX() + ", " + caminho[i].getPosY() + "\n";
		descricaoFinal = descricao1 + descricao2;
		
		return descricaoFinal;
	}

}
