package professor;

import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.util.List;

public class ProfessorController {

    private ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    public void cadastrarProfessor(ProfessorModel professor) throws InsertSqlException {
        professorDAO.insert(professor);
    }

    public ProfessorModel buscarPorId(int idProfessor) throws SelectSqlException {
        return professorDAO.selectById(idProfessor);
    }

    public List<ProfessorModel> listarTodos() throws SelectSqlException {
        return professorDAO.selectAll();
    }
}
