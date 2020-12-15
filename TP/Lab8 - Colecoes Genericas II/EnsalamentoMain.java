
public class EnsalamentoMain {

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
		
		System.out.println(sala1.getDescricao());
		System.out.println(sala2.getDescricao());
		System.out.println(sala3.getDescricao());
		System.out.println(turma1.getDescricao());
		System.out.println(turma2.getDescricao());
		System.out.println(turma3.getDescricao());		
	}
}
