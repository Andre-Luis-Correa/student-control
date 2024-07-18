package tipologradouro;

import exceptions.InsertSqlException;

public class TipoLogradouroController {

    private TipoLogradouroDAO tipoLogradouroDAO;

    public TipoLogradouroController() {
        this.tipoLogradouroDAO = new TipoLogradouroDAO();
    }

    public String cadastrarTipoLogradouro(TipoLogradouroModel tipoLogradouro) throws InsertSqlException {
        return tipoLogradouroDAO.insert(tipoLogradouro);
    }
}
