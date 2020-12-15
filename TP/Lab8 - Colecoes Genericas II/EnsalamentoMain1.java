
public class EnsalamentoMain1 {

	public static void main(String[] args) {
			Sala sala1 = new Sala(6, 101, 50, true);
			Sala sala2 = new Sala(6, 107, 30, false);
			Sala sala3 = new Sala();
			Turma turma1 = new Turma("Algoritmos e Estrutura de Dados I", "Edleno Silva", 60, true);
			Turma turma2 = new Turma("Linguagens Formais e Autômatos", "Eduardo Nakamura", 40, false);
			Turma turma3 = new Turma("Matemática Discreta", "Eulanda Miranda", 52, true);
			
			turma1.addHorario(1);
			turma1.addHorario(15);
			turma1.addHorario(29);
			turma2.addHorario(9);
			turma2.addHorario(23);
			turma3.addHorario(5);
			turma3.addHorario(19);
			turma3.addHorario(5);		
			
		
		
		Ensalamento e1 = new Ensalamento();
		Sala s1 = new Sala(2, 102, 80, true);
		//Sala s2 = new Sala(3, 103, 70, true);
		e1.addSala(s1);
		e1.addSala(sala1);
		e1.addSala(sala2);
		e1.addSala(sala3);
		Turma t1 = new Turma("Organização de Computadores", "Andrew S. Tanenbaum", 70, true);
		t1.addHorario(7);
		t1.addHorario(21);
		t1.addHorario(35);
		e1.addTurma(t1);
		e1.addTurma(turma1);
		e1.addTurma(turma2);
		e1.addTurma(turma3);
		e1.alocarTodas();
		System.out.println(e1.relatorioTurmasPorSala());
		System.out.println(e1.relatorioSalasPorTurma());
	}

}
