package unidadefederacao;

import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.util.List;

public class UnidadeFederacaoController {
    private UnidadeFederacaoDAO unidadeFederacaoDAO;

    public UnidadeFederacaoController() {
        this.unidadeFederacaoDAO = new UnidadeFederacaoDAO();
    }

    public String cadastrarUnidadeFederacao(UnidadeFederacaoModel unidadeFederacao) throws InsertSqlException {
        return unidadeFederacaoDAO.insert(unidadeFederacao);
    }

    public List<UnidadeFederacaoModel> buscarUnidadesFederacao() throws SelectSqlException {
        return unidadeFederacaoDAO.selectAll();
    }

    public UnidadeFederacaoModel buscarUnidadeFederacaoPorSigla(String sigla) throws SelectSqlException {
        return unidadeFederacaoDAO.selectBySigla(sigla);
    }
}
