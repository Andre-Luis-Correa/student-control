package disciplina;

import exceptions.SelectSqlException;
import professor.ProfessorModel;

import java.util.List;

public class DisciplinaController {

    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController() {
        this.disciplinaDAO = new DisciplinaDAO();
    }

    public List<DisciplinaModel> buscarPorCursoEAnoLetivo(int idCurso, int anoLetivo) throws SelectSqlException {
        return disciplinaDAO.buscarPorCursoEAnoLetivo(idCurso, anoLetivo);
    }

    public List<ProfessorModel> buscarProfessoresPorDisciplina(int idDisciplina) throws SelectSqlException {
        return disciplinaDAO.buscarProfessoresPorDisciplina(idDisciplina);
    }
}
