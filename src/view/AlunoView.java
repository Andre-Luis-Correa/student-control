package view;

import controller.AlunoController;
import controller.EnderecoController;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import model.*;

import java.util.List;
import java.util.Scanner;

public class AlunoView {

    private AlunoController alunoController;
    private Scanner scanner;
    private EnderecoView enderecoView;

    public AlunoView() {
        this.alunoController = new AlunoController();
        this.scanner = new Scanner(System.in);
        this.enderecoView = new EnderecoView();
    }

    public void cadastrarAluno() throws SelectSqlException {
        System.out.println("Cadastrar aluno:\n");
        System.out.print("Digite o CPF do aluno: ");
        String cpfAluno = scanner.nextLine();

        if (alunoController.buscarPorCpf(cpfAluno) == null) {
            AlunoModel alunoModel = lerAluno();
            alunoModel.setCpfAluno(cpfAluno);
            EnderecoModel enderecoModel = enderecoView.lerEndereco();
            alunoModel.setEnderecoModel(enderecoModel);
            try {
                alunoController.cadastrarAluno(alunoModel);
                System.out.println("Aluno cadastrado com sucesso!");
            } catch (InsertSqlException e) {
                e.printStackTrace();
                System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
            }
        } else {
            System.out.println("Aluno já cadastrado com esse CPF!");
        }
    }

    private AlunoModel lerAluno() {
        AlunoModel alunoModel = new AlunoModel();

        System.out.print("Nome do Aluno: ");
        alunoModel.setNomeAluno(scanner.nextLine());

        System.out.print("Telefone do Aluno: ");
        alunoModel.setTelefoneAluno(scanner.nextLine());

        System.out.print("Email do Aluno: ");
        alunoModel.setEmailAluno(scanner.nextLine());

        return alunoModel;
    }

}
