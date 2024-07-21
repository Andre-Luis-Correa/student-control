package matriculaaluno;

import aluno.AlunoModel;
import anoletivo.AnoLetivoDAO;
import anoletivo.AnoLetivoModel;
import curso.CursoModel;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.time.LocalDate;
import java.util.List;

public class MatriculaAlunoController {

    private MatriculaAlunoDAO matriculaAlunoDAO;
    private AnoLetivoDAO anoLetivoDAO;

    public MatriculaAlunoController() {
        this.matriculaAlunoDAO = new MatriculaAlunoDAO();
        this.anoLetivoDAO = new AnoLetivoDAO();
    }

    public void matricularAluno(AlunoModel alunoModel, CursoModel cursoModel, List<Integer> disciplinas) throws InsertSqlException, SelectSqlException {
        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();

        MatriculaAlunoModel matriculaAlunoModel = new MatriculaAlunoModel();
        matriculaAlunoModel.setAlunoModel(alunoModel);
        matriculaAlunoModel.setDtMatricula(dataAtual);
        matriculaAlunoModel.setCursoModel(cursoModel);

        AnoLetivoModel anoLetivoModel = anoLetivoDAO.selectByAnoLetivo(anoAtual);
        if (anoLetivoModel == null) {
            throw new SelectSqlException("Ano letivo atual não encontrado.");
        }
        matriculaAlunoModel.setAnoLetivoModel(anoLetivoModel);
        matriculaAlunoModel.setStatusMatricula(true);

        int nroMatricula = matriculaAlunoDAO.generateNroMatricula();
        matriculaAlunoModel.setNroMatricula(nroMatricula);

        // Desativar matrículas anteriores do aluno
        matriculaAlunoDAO.desativarMatriculasAnteriores(alunoModel.getIdAluno());

        // Verificar se o aluno já cursou alguma das disciplinas em qualquer ano letivo
        List<Integer> disciplinasJaCursadas = matriculaAlunoDAO.selectDisciplinasCursadasPorAluno(alunoModel.getIdAluno());
        for (int idDisciplina : disciplinas) {
            if (disciplinasJaCursadas.contains(idDisciplina)) {
                throw new InsertSqlException("O aluno já cursou a disciplina com ID: " + idDisciplina);
            }
        }

        matriculaAlunoModel.setDisciplinas(disciplinas);

        matriculaAlunoDAO.insert(matriculaAlunoModel);
    }

    public MatriculaAlunoModel buscarPorNroMatricula(int nroMatricula) throws SelectSqlException {
        return matriculaAlunoDAO.selectByNroMatricula(nroMatricula);
    }
}
