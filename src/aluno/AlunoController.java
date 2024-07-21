package aluno;

import endereco.EnderecoController;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

public class AlunoController {

    private AlunoDAO alunoDAO;
    private EnderecoController enderecoController;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        this.enderecoController = new EnderecoController();
    }

    public void cadastrarAluno(AlunoModel aluno) throws InsertSqlException, SelectSqlException {
        int idEndereco = enderecoController.cadastrarEndereco(aluno.getEnderecoModel());
        aluno.getEnderecoModel().setIdEndereco(idEndereco);
        alunoDAO.insert(aluno);
    }

    public AlunoModel buscarPorCpf(String cpfAluno) throws SelectSqlException {
        return alunoDAO.selectByCpf(cpfAluno);
    }
}
