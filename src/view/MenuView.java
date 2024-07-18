package view;

import exceptions.SelectSqlException;

import java.util.Scanner;

public class MenuView {

    private AlunoView alunoView;
    private ProfessorView professorView;
    private Scanner scanner;

    public MenuView() {
        this.alunoView = new AlunoView();
        this.professorView = new ProfessorView();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() throws SelectSqlException {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Matricular aluno");
            System.out.println("3. Exibir matricula de aluno");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumes the newline character

            switch (opcao) {
                case 1:
                    alunoView.cadastrarAluno();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }
}
