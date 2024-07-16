package view;

import controller.ProfessorController;
import model.ProfessorModel;

import java.util.Scanner;

public class ProfessorView {

    private ProfessorController professorController;
    private Scanner scanner;

    public ProfessorView() {
        this.professorController = new ProfessorController();
        this.scanner = new Scanner(System.in);
    }

    public void createProfessor() {
        ProfessorModel professor = new ProfessorModel();

        System.out.print("Nome do Professor: ");
        professor.setNomeProfessor(scanner.nextLine());

        System.out.print("Email do Professor: ");
        professor.setEmailProfessor(scanner.nextLine());

        System.out.print("Endereço do Professor: ");
        professor.setEnderecoProfessor(scanner.nextLine());

        professorController.addProfessor(professor);
    }

    public void showProfessor(int id) {
        ProfessorModel professor = professorController.getProfessor(id);
        if (professor != null) {
            System.out.println(professor);
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    // Outros métodos para interação com o usuário
}
