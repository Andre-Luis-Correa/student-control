package aluno;

import endereco.EnderecoModel;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import endereco.EnderecoView;

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

    public void matricularAluno() throws SelectSqlException {
        System.out.println("Matricular aluno: \n");

        System.out.print("Digite o CPF do aluno: ");
        String cpfAluno = scanner.nextLine();
        AlunoModel alunoModel = alunoController.buscarPorCpf(cpfAluno);

        if (alunoModel != null) {
            alunoController.matricularAluno(alunoModel);
            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("Cadastro de aluno não encontrado com esse CPF, realize o cadastro no menu principal");
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
