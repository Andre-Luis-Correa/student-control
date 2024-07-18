package matriculaaluno;

import aluno.AlunoController;
import aluno.AlunoModel;
import exceptions.SelectSqlException;

import java.util.Scanner;

public class MatriculaAlunoView {

    private MatriculaAlunoController matriculaAlunoController;

    private Scanner scanner;

    private AlunoController alunoController;

    public MatriculaAlunoView() {
        this.matriculaAlunoController = new MatriculaAlunoController();
        this.scanner = new Scanner(System.in);
        this.alunoController = new AlunoController();
    }

    public void matricularAluno() throws SelectSqlException {
        System.out.println("Matricular aluno: \n");

        System.out.print("Digite o CPF do aluno: ");
        String cpfAluno = scanner.nextLine();
        AlunoModel alunoModel = alunoController.buscarPorCpf(cpfAluno);

        if (alunoModel != null) {
            matriculaAlunoController.matricularAluno(alunoModel);
            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("Cadastro de aluno não encontrado com esse CPF, realize o cadastro no menu principal");
        }

    }
}
