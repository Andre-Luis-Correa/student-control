package controller;

import dao.CidadeDAO;
import exceptions.InsertSqlException;
import model.CidadeModel;

public class CidadeController {

    private CidadeDAO cidadeDAO;

    public CidadeController() {
        this.cidadeDAO = new CidadeDAO();
    }

    public int cadastrarCidade(CidadeModel cidade) throws InsertSqlException {
        return cidadeDAO.insert(cidade);
    }
}
