package controller;

import dao.AlunoDAO;
import model.AlunoModel;

public class AlunoController {

    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    public void addAluno(AlunoModel aluno) {
        try {
            alunoDAO.insert(aluno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AlunoModel getAluno(int id) {
        try {
            return alunoDAO.select(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
