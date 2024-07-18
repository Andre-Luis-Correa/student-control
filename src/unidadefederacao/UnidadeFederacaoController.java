package unidadefederacao;

import exceptions.InsertSqlException;

public class UnidadeFederacaoController {
    private UnidadeFederacaoDAO unidadeFederacaoDAO;

    public UnidadeFederacaoController() {
        this.unidadeFederacaoDAO = new UnidadeFederacaoDAO();
    }

    public String cadastrarUnidadeFederacao(UnidadeFederacaoModel unidadeFederacao) throws InsertSqlException {
        return unidadeFederacaoDAO.insert(unidadeFederacao);
    }
}
