package professor;

import exceptions.SelectSqlException;

import java.util.List;
import java.util.Scanner;

public class ProfessorView {

    private ProfessorController professorController;
    private Scanner scanner;

    public ProfessorView() {
        this.professorController = new ProfessorController();
        this.scanner = new Scanner(System.in);
    }

    public void listarProfessores() {
        try {
            List<ProfessorModel> professores = professorController.listarTodos();
            if (professores.isEmpty()) {
                System.out.println("Nenhum professor cadastrado.");
            } else {
                System.out.println("Lista de Professores:");
                for (ProfessorModel professor : professores) {
                    System.out.println("ID: " + professor.getIdProfessor());
                    System.out.println("Nome: " + professor.getNomeProfessor());
                    System.out.println("Email: " + professor.getEmailProfessor());
                    System.out.println("Endereço: " + professor.getEnderecoProfessor());
                    System.out.println("-----------------------------");
                }
            }
        } catch (SelectSqlException e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }
    }
}
