package anoletivo;

import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.util.List;

public class AnoLetivoController {

    private AnoLetivoDAO anoLetivoDAO;

    public AnoLetivoController() {
        this.anoLetivoDAO = new AnoLetivoDAO();
    }

    public void adicionarAnoLetivo(AnoLetivoModel anoLetivoModel) throws InsertSqlException {
        anoLetivoDAO.insert(anoLetivoModel);
    }

    public AnoLetivoModel buscarPorId(int idAnoLetivo) throws SelectSqlException {
        return anoLetivoDAO.selectById(idAnoLetivo);
    }

    public List<AnoLetivoModel> buscarTodos() throws SelectSqlException {
        return anoLetivoDAO.selectAll();
    }

    public AnoLetivoModel selectByAnoLetivo(int anoLetivo) throws SelectSqlException {
        return anoLetivoDAO.selectByAnoLetivo(anoLetivo);
    }
}
