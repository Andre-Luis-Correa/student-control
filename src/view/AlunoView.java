package view;

import controller.AlunoController;
import model.AlunoModel;

import java.util.Scanner;

public class AlunoView {

    private AlunoController alunoController;
    private Scanner scanner;

    public AlunoView() {
        this.alunoController = new AlunoController();
        this.scanner = new Scanner(System.in);
    }

    public void createAluno() {
        AlunoModel aluno = new AlunoModel();

        System.out.print("Nome do Aluno: ");
        aluno.setNomeAluno(scanner.nextLine());

        System.out.print("CPF do Aluno: ");
        aluno.setCpfAluno(scanner.nextLine());

        System.out.print("Telefone do Aluno: ");
        aluno.setTelefoneAluno(scanner.nextLine());

        System.out.print("Email do Aluno: ");
        aluno.setEmailAluno(scanner.nextLine());

        System.out.print("Endereço do Aluno: ");
        aluno.setEnderecoAluno(scanner.nextLine());

        alunoController.addAluno(aluno);
    }

    public void showAluno(int id) {
        AlunoModel aluno = alunoController.getAluno(id);
        if (aluno != null) {
            System.out.println(aluno);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    // Outros métodos para interação com o usuário
}
