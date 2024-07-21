package curso;

import exceptions.SelectSqlException;

import java.util.List;

public class CursoController {

    private CursoDAO cursoDAO;

    public CursoController() {
        this.cursoDAO = new CursoDAO();
    }

    public CursoModel buscarPorId(int idCurso) throws SelectSqlException {
        return cursoDAO.selectById(idCurso);
    }

    public List<CursoModel> buscarTodos() throws SelectSqlException {
        return cursoDAO.selectAll();
    }
}
