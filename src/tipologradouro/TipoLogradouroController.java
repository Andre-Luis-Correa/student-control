package tipologradouro;

import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.util.List;

public class TipoLogradouroController {

    private TipoLogradouroDAO tipoLogradouroDAO;

    public TipoLogradouroController() {
        this.tipoLogradouroDAO = new TipoLogradouroDAO();
    }

    public String cadastrarTipoLogradouro(TipoLogradouroModel tipoLogradouro) throws InsertSqlException {
        return tipoLogradouroDAO.insert(tipoLogradouro);
    }

    public List<TipoLogradouroModel> buscarTiposLogradouro() throws SelectSqlException {
        return tipoLogradouroDAO.selectAll();
    }

    public TipoLogradouroModel buscarTipoLogradouroPorSigla(String sigla) throws SelectSqlException {
        return tipoLogradouroDAO.selectBySigla(sigla);
    }
}
