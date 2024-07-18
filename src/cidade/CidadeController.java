package cidade;

import exceptions.InsertSqlException;

public class CidadeController {

    private CidadeDAO cidadeDAO;

    public CidadeController() {
        this.cidadeDAO = new CidadeDAO();
    }

    public int cadastrarCidade(CidadeModel cidade) throws InsertSqlException {
        return cidadeDAO.insert(cidade);
    }
}
