package bairro;

import exceptions.InsertSqlException;

public class BairroController {

    private BairroDAO bairroDAO;

    public BairroController() {
        this.bairroDAO = new BairroDAO();
    }

    public int cadastrarBairro(BairroModel bairro) throws InsertSqlException {
        return bairroDAO.insert(bairro);
    }
}
