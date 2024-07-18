package controller;

import dao.AlunoDAO;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import model.AlunoModel;

public class AlunoController {

    private AlunoDAO alunoDAO;
    private EnderecoController enderecoController;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        this.enderecoController = new EnderecoController();
    }

    public void cadastrarAluno(AlunoModel aluno) throws InsertSqlException {
        int idEndereco = enderecoController.cadastrarEndereco(aluno.getEnderecoModel());
        aluno.getEnderecoModel().setIdEndereco(idEndereco);
        alunoDAO.insert(aluno);
    }

    public AlunoModel buscarPorCpf(String cpfAluno) throws SelectSqlException {
        return alunoDAO.selectByCpf(cpfAluno);
    }

}
