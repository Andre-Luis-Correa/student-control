package controller;

import dao.BairroDAO;
import exceptions.InsertSqlException;
import model.BairroModel;

public class BairroController {

    private BairroDAO bairroDAO;

    public BairroController() {
        this.bairroDAO = new BairroDAO();
    }

    public int cadastrarBairro(BairroModel bairro) throws InsertSqlException {
        return bairroDAO.insert(bairro);
    }
}
