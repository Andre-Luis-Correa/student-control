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

    public AlunoModel lerAluno() {
        AlunoModel alunoModel = new AlunoModel();

        System.out.print("Nome do Aluno: ");
        alunoModel.setNomeAluno(scanner.nextLine());

        System.out.print("Telefone do Aluno: ");
        alunoModel.setTelefoneAluno(scanner.nextLine());

        System.out.print("Email do Aluno: ");
        alunoModel.setEmailAluno(scanner.nextLine());

        return alunoModel;
    }

    public void listarInformacoesAluno() {
        try {
            System.out.print("Digite o CPF do aluno: ");
            String cpfAluno = scanner.nextLine();

            AlunoModel aluno = alunoController.buscarPorCpf(cpfAluno);
            if (aluno != null) {
                System.out.println("Informações do Aluno:");
                System.out.println("ID: " + aluno.getIdAluno());
                System.out.println("Nome: " + aluno.getNomeAluno());
                System.out.println("CPF: " + aluno.getCpfAluno());
                System.out.println("Telefone: " + aluno.getTelefoneAluno());
                System.out.println("Email: " + aluno.getEmailAluno());
                System.out.println("Endereço: ");
                System.out.println("  CEP: " + aluno.getEnderecoModel().getCepEndereco());
                System.out.println("  Bairro: " + aluno.getEnderecoModel().getBairroModel().getNomeBairro());
                System.out.println("  Cidade: " + aluno.getEnderecoModel().getCidadeModel().getNomeCidade());
                System.out.println("  Logradouro: " + aluno.getEnderecoModel().getLogradouroModel().getNomeLogradouro());
            } else {
                System.out.println("Aluno não encontrado com o CPF fornecido.");
            }
        } catch (SelectSqlException e) {
            System.out.println("Erro ao buscar informações do aluno: " + e.getMessage());
        }
    }
}
