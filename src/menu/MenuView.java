package menu;

import aluno.AlunoView;
import exceptions.SelectSqlException;
import matriculaaluno.MatriculaAlunoView;
import professor.ProfessorView;

import java.util.Scanner;

public class MenuView {

    private AlunoView alunoView;
    private MatriculaAlunoView matriculaAlunoView;
    private ProfessorView professorView;
    private Scanner scanner;

    public MenuView() {
        this.alunoView = new AlunoView();
        this.matriculaAlunoView = new MatriculaAlunoView();
        this.professorView = new ProfessorView();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Matricular aluno");
            System.out.println("3. Exibir matrícula de aluno");
            System.out.println("4. Listar professores");
            System.out.println("5. Listar informações do aluno");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumes the newline character

            try {
                switch (opcao) {
                    case 1:
                        alunoView.cadastrarAluno();
                        break;
                    case 2:
                        matriculaAlunoView.matricularAluno();
                        break;
                    case 3:
                        // Implementar exibição da matrícula de aluno
                        break;
                    case 4:
                        professorView.listarProfessores();
                        break;
                    case 5:
                        alunoView.listarInformacoesAluno();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (SelectSqlException e) {
                System.out.println("Erro ao executar a operação: " + e.getMessage());
            }
        }

        scanner.close();
    }
}