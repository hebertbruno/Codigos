package br.edu.ufam.icomp.lab_excecoes;

public class RoverMain {
	public static void main(String[] args) {
		
		Caminho caminho = new Caminho(4);
		try{
		
			Coordenada c1 = new Coordenada(32, 30, 2);
			Coordenada c2 = new Coordenada(35, 40, 6);
			Coordenada c3 = new Coordenada(38, 30, 8);
			Coordenada c4 = new Coordenada(30, 36, 6);
		
			
		
			caminho.addCoordenada(c1);
			caminho.addCoordenada(c2);
			caminho.addCoordenada(c3);
			caminho.addCoordenada(c4);
		
			System.out.println(caminho.toString());
		} 
		catch(RoverException e){
			caminho.reset();
			System.out.println(e);
			
			
		}
		
		
		
		
	
	}
}
