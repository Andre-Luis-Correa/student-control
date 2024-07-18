package controller;

import dao.TipoLogradouroDAO;
import exceptions.InsertSqlException;
import model.TipoLogradouroModel;

public class TipoLogradouroController {

    private TipoLogradouroDAO tipoLogradouroDAO;

    public TipoLogradouroController() {
        this.tipoLogradouroDAO = new TipoLogradouroDAO();
    }

    public String cadastrarTipoLogradouro(TipoLogradouroModel tipoLogradouro) throws InsertSqlException {
        return tipoLogradouroDAO.insert(tipoLogradouro);
    }
}
