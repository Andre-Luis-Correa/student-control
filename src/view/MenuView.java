package view;

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

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Criar Aluno");
            System.out.println("2. Exibir Aluno");
            System.out.println("3. Criar Professor");
            System.out.println("4. Exibir Professor");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumes the newline character

            switch (choice) {
                case 1:
                    System.out.println("Criando um novo aluno:");
                    alunoView.createAluno();
                    break;
                case 2:
                    System.out.print("Digite o ID do aluno: ");
                    int alunoId = scanner.nextInt();
                    scanner.nextLine(); // Consumes the newline character
                    System.out.println("Exibindo aluno:");
                    alunoView.showAluno(alunoId);
                    break;
                case 3:
                    System.out.println("Criando um novo professor:");
                    professorView.createProfessor();
                    break;
                case 4:
                    System.out.print("Digite o ID do professor: ");
                    int professorId = scanner.nextInt();
                    scanner.nextLine(); // Consumes the newline character
                    System.out.println("Exibindo professor:");
                    professorView.showProfessor(professorId);
                    break;
                case 5:
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
