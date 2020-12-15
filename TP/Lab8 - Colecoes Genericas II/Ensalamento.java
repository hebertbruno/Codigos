import java.util.*;

public class Ensalamento {
	ArrayList<Sala> salas = new ArrayList<Sala>();
	ArrayList<Turma> turmas = new ArrayList<Turma>();
	ArrayList<TurmaEmSala> ensalamento = new ArrayList<TurmaEmSala>();
	
	Ensalamento() {}
	
	//adiciona um objeto da classe Sala na lista de salas
	void addSala(Sala sala) {
		boolean naoExiste = true;
		int i = 0;
		
		if (salas.isEmpty()) 
			salas.add(sala);
		else {
			while (naoExiste && i < salas.size()) {
				if (salas.get(i).sala == sala.sala)
					naoExiste = false;
				i++;
			}
			if (naoExiste)
				salas.add(sala);
		}
	}
	
	//adiciona uma Turma na lista de turmas
	void addTurma(Turma turma) {
		boolean naoExiste = true;
		int i = 0;
		
		if (turmas.isEmpty()) 
			turmas.add(turma);
		else {
			while (naoExiste && i < turmas.size()) {
				if (turmas.get(i).nome == turma.nome)
					naoExiste = false;
				i++;
			}
			if (naoExiste)
				turmas.add(turma);
		}
	}
	
	
	//retorna a sala alocada a uma determinada turma. Se nenhuma sala for encontrada, retorna null
	Sala getSala(Turma turma) {
		boolean achou = false;
		Sala salaEncontrada = null;
		int i = 0;
		
		while (achou == false && i < ensalamento.size()) {
			if (ensalamento.get(i).turma == turma) {
				salaEncontrada = ensalamento.get(i).sala;
				achou = true;
			}
			i++;			
		}
		
		return salaEncontrada;		
	}
	
	// retorna true se a sala está disponível em um determinado horário. false caso contrário
	boolean salaDisponivel(Sala sala, int horario) {
		boolean disponivel = true;
		int i = 0, j = 0;
		
		while (disponivel && i < ensalamento.size()) {
			if (ensalamento.get(i).sala == sala) {
				while (disponivel && j < ensalamento.get(i).turma.horarios.size()) {
					if (ensalamento.get(i).turma.horarios.get(j) == horario)
						disponivel = false;
					j++;
				}
			}
			i++;
		}
		
		return disponivel;
	}
	
	//etorna true se a sala está disponível em todos os horários do parâmetro horarios (lista de inteiros). false caso contrário.
	boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios) {
		int i = 0;
		
		for (int j = 0; j < ensalamento.size(); j++) {
			if (salaDisponivel(sala, horarios.get(j)) == false) {
				return false;
			}
			else {
				i++;
			}
		}
		return true;		
	}
	
	
	boolean alocar(Turma turma, Sala sala) {
		boolean disponibilidade = false;
		
		if (salaDisponivel(sala, turma.horarios)) {
			if (turma.numAlunos <= sala.capacidade) {
				if(sala.acessivel){
					TurmaEmSala novaTurma = new TurmaEmSala(turma, sala);
					ensalamento.add(novaTurma);
					disponibilidade = true;
				}
			}
		}
			
		
		return disponibilidade;
	}
	
	void alocarTodas() {
		int diferenca = 100;
		int indexSala=0, verificador=0;
		for(int i = 0; i < turmas.size(); i++) {
			for(int j = 0; j< salas.size(); j++) {
				
				if(salaDisponivel(salas.get(j), turmas.get(i).horarios)) {//verifica disponibilidade de horario da sala nos horarios da turma
					if(turmas.get(i).numAlunos <= salas.get(j).capacidade) {//verifica a capacidade da sala
						if(salas.get(j).acessivel!=false){
							if(diferenca > (salas.get(j).capacidade - turmas.get(i).numAlunos)) {
								diferenca = salas.get(j).capacidade - turmas.get(i).numAlunos;
								indexSala = j;
								verificador = 1; //é possivel alocar turma na sala
							}
						}
					}
				}
						
			}
			if(verificador==1) {
				TurmaEmSala novaTurma = new TurmaEmSala(turmas.get(i), salas.get(indexSala));
				ensalamento.add(novaTurma);
			}
			
		}

	}

	int getTotalTurmasAlocadas() {
		int resultado=0;
		for(int i = 0; i < ensalamento.size(); i++) {
			if(ensalamento.get(i).sala != null) {
				resultado++;
			}
		}
		return resultado;
	}
	
	int getTotalEspacoLivre() {
		int contador=0;
		for(int i = 0; i < ensalamento.size(); i++) {
			contador += ensalamento.get(i).sala.capacidade - ensalamento.get(i).turma.numAlunos;
		}
		return contador;
	}

	String relatorioResumoEnsalamento() {
		return "Total de Salas: " + salas.size() + "\nTotal de Turmas: " + turmas.size() + "\nTurmas Alocadas: " + 
				getTotalTurmasAlocadas()+ "\nEspaços Livres: " + getTotalEspacoLivre();
	}
	
	
	String relatorioTurmasPorSala() {
		String descricao = relatorioResumoEnsalamento();
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(stringBuilder.length(), '\n');
		
				
		for(int i = 0; i < salas.size(); i ++) {
			stringBuilder.insert(stringBuilder.length(), "--- ");
			stringBuilder.insert(stringBuilder.length(), salas.get(i).getDescricao());
			stringBuilder.insert(stringBuilder.length(), " ---");
			stringBuilder.insert(stringBuilder.length(), '\n');
			for(int j=0;j<ensalamento.size();j++){
				if(salas.get(i)==ensalamento.get(j).sala){
					stringBuilder.insert(stringBuilder.length(), turmas.get(j).getDescricao());
					stringBuilder.insert(stringBuilder.length(), "\n");
					
				}
			}
			
		}
		descricao = stringBuilder.toString();
		return descricao;
	}
	
	String relatorioSalasPorTurma(){
		String descricao = relatorioResumoEnsalamento();
		StringBuilder stringBuilder = new StringBuilder(descricao);
		stringBuilder.insert(stringBuilder.length(), "\n\n");
		
		for(int i = 0; i < turmas.size(); i ++) {
			stringBuilder.insert(stringBuilder.length(), turmas.get(i).getDescricao());
			stringBuilder.insert(stringBuilder.length(), '\n');
			stringBuilder.insert(stringBuilder.length(), "Sala: ");
			int ver=0;
			
			for(int j=0; j<ensalamento.size(); j++){
				ver++;
				if(turmas.get(i)==ensalamento.get(j).turma){
					stringBuilder.insert(stringBuilder.length(), salas.get(j).getDescricao());
					stringBuilder.insert(stringBuilder.length(), '\n');
				}
				
			}
			if(ver==0){

				stringBuilder.insert(stringBuilder.length(), "SEM SALA");
			}
			
		}
		descricao = stringBuilder.toString();
		return descricao;
	}
}
