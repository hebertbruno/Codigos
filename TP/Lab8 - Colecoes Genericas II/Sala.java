public class Sala {
	int bloco, sala, capacidade;
	boolean acessivel;
	
	Sala() {
		this(0, 0, 0, false);
	}
	
	Sala(int bloco, int sala, int capacidade, boolean acessivel) {
		this.bloco = bloco;
		this.sala = sala;
		this.capacidade = capacidade;
		this.acessivel = acessivel;
	}
	
	String getDescricao() {
		String descricao;
		if (acessivel)
			descricao = "Bloco " + bloco + ", Sala " + sala + " (" + capacidade 
						+ " lugares, acess�vel)";
		else
			descricao = "Bloco " + bloco + ", Sala " + sala + " (" + capacidade 
			+ " lugares, n�o acess�vel)";
		
		return descricao;			
	}
}
