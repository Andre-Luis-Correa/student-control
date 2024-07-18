package controller;

import dao.UnidadeFederacaoDAO;
import exceptions.InsertSqlException;
import model.UnidadeFederacaoModel;

public class UnidadeFederacaoController {
    private UnidadeFederacaoDAO unidadeFederacaoDAO;

    public UnidadeFederacaoController() {
        this.unidadeFederacaoDAO = new UnidadeFederacaoDAO();
    }

    public String cadastrarUnidadeFederacao(UnidadeFederacaoModel unidadeFederacao) throws InsertSqlException {
        return unidadeFederacaoDAO.insert(unidadeFederacao);
    }
}
