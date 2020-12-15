import java.util.*;

public class Turma {
	String nome, professor;
	int numAlunos;
	boolean acessivel;
	ArrayList<Integer> horarios = new ArrayList<Integer>();
	
	Turma() {
		this("", "", 0, false);
	}
	
	Turma(String nome, String professor, int numAlunos, boolean acessivel) {
		this.nome = nome;
		this.professor = professor;
		this.numAlunos = numAlunos;
		this.acessivel = acessivel;
	}
	
	void addHorario(int horario) {
		boolean naoExiste = true;
		int i = 0;
		
		if ((horario > 0) && (horario < 36)) {
			if (horarios.isEmpty())
				horarios.add(horario);
			else {
				while (naoExiste && i < horarios.size()) {
					if (horarios.get(i) == horario)
						naoExiste = false;
					i++;
				}
				if (naoExiste)
					horarios.add(horario);
			}
		}
	}
	
	String getHorariosString() {
		String horariosString = "";
		String horariosStringFinal;
		
		for (int i = 0; i < horarios.size(); i++) {
			if (horarios.get(i) == 1) 
				horariosString = horariosString + "segunda 8hs, ";
			if (horarios.get(i) == 2) 
				horariosString = horariosString + "segunda 10hs, ";
			if (horarios.get(i) == 3) 
				horariosString = horariosString + "segunda 12hs, ";
			if (horarios.get(i) == 4) 
				horariosString = horariosString + "segunda 14hs, ";
			if (horarios.get(i) == 5) 
				horariosString = horariosString + "segunda 16hs, ";
			if (horarios.get(i) == 6) 
				horariosString = horariosString + "segunda 18hs, ";
			if (horarios.get(i) == 7) 
				horariosString = horariosString + "segunda 20hs, ";
			if (horarios.get(i) == 8) 
				horariosString = horariosString + "terça 8hs, ";
			if (horarios.get(i) == 9) 
				horariosString = horariosString + "terça 10hs, ";
			if (horarios.get(i) == 10) 
				horariosString = horariosString + "terça 12hs, ";
			if (horarios.get(i) == 11) 
				horariosString = horariosString + "terça 14hs, ";
			if (horarios.get(i) == 12) 
				horariosString = horariosString + "terça 16hs, ";
			if (horarios.get(i) == 13) 
				horariosString = horariosString + "terça 18hs, ";
			if (horarios.get(i) == 14) 
				horariosString = horariosString + "terça 20hs, ";
			if (horarios.get(i) == 15) 
				horariosString = horariosString + "quarta 8hs, ";
			if (horarios.get(i) == 16) 
				horariosString = horariosString + "quarta 10hs, ";
			if (horarios.get(i) == 17) 
				horariosString = horariosString + "quarta 12hs, ";
			if (horarios.get(i) == 18) 
				horariosString = horariosString + "quarta 14hs, ";
			if (horarios.get(i) == 19) 
				horariosString = horariosString + "quarta 16hs, ";
			if (horarios.get(i) == 20) 
				horariosString = horariosString + "quarta 18hs, ";
			if (horarios.get(i) == 21) 
				horariosString = horariosString + "quarta 20hs, ";
			if (horarios.get(i) == 22) 
				horariosString = horariosString + "quinta 8hs, ";
			if (horarios.get(i) == 23) 
				horariosString = horariosString + "quinta 10hs, ";
			if (horarios.get(i) == 24) 
				horariosString = horariosString + "quinta 12hs, ";
			if (horarios.get(i) == 25) 
				horariosString = horariosString + "quinta 14hs, ";
			if (horarios.get(i) == 26) 
				horariosString = horariosString + "quinta 16hs, ";
			if (horarios.get(i) == 27) 
				horariosString = horariosString + "quinta 18hs, ";
			if (horarios.get(i) == 28) 
				horariosString = horariosString + "quinta 20hs, ";
			if (horarios.get(i) == 29) 
				horariosString = horariosString + "sexta 8hs, ";
			if (horarios.get(i) == 30) 
				horariosString = horariosString + "sexta 10hs, ";
			if (horarios.get(i) == 31) 
				horariosString = horariosString + "sexta 12hs, ";
			if (horarios.get(i) == 32) 
				horariosString = horariosString + "sexta 14hs, ";
			if (horarios.get(i) == 33) 
				horariosString = horariosString + "sexta 16hs, ";
			if (horarios.get(i) == 34) 
				horariosString = horariosString + "sexta 18hs, ";
			if (horarios.get(i) == 35) 
				horariosString = horariosString + "sexta 20hs, ";
		}
		horariosStringFinal = horariosString.substring(0, horariosString.length()-2);
		
		return horariosStringFinal;
	}
	
	String getDescricao() {
		String descricao;
		if (acessivel)
			descricao = "Turma: " + nome + "\nProfessor: " + professor
					   	+ "\nNúmero de Alunos: " + numAlunos 
					   	+ "\nHorário: " + getHorariosString() + "\nAcessível: sim";
		else
			descricao = "Turma: " + nome + "\nProfessor: " + professor
		   				+ "\nNúmero de Alunos: " + numAlunos 
		   				+ "\nHorário: " + getHorariosString() + "\nAcessível: não";
		
		return descricao;	
	}
}
