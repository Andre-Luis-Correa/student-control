package matriculaaluno;

import aluno.AlunoModel;
import anoletivo.AnoLetivoModel;
import curso.CursoModel;

import java.time.LocalDate;
import java.util.List;

public class MatriculaAlunoModel {

    private int nroMatricula;
    private LocalDate dtMatricula;
    private boolean statusMatricula;
    private AlunoModel alunoModel;
    private AnoLetivoModel anoLetivoModel;
    private CursoModel cursoModel;
    private List<Integer> disciplinas;

    public MatriculaAlunoModel() {
    }

    public MatriculaAlunoModel(int nroMatricula, LocalDate dtMatricula, boolean statusMatricula, AlunoModel alunoModel, AnoLetivoModel anoLetivoModel, CursoModel cursoModel, List<Integer> disciplinas) {
        this.nroMatricula = nroMatricula;
        this.dtMatricula = dtMatricula;
        this.statusMatricula = statusMatricula;
        this.alunoModel = alunoModel;
        this.anoLetivoModel = anoLetivoModel;
        this.cursoModel = cursoModel;
        this.disciplinas = disciplinas;
    }

    public int getNroMatricula() {
        return nroMatricula;
    }

    public void setNroMatricula(int nroMatricula) {
        this.nroMatricula = nroMatricula;
    }

    public LocalDate getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(LocalDate dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    public boolean isStatusMatricula() {
        return statusMatricula;
    }

    public void setStatusMatricula(boolean statusMatricula) {
        this.statusMatricula = statusMatricula;
    }

    public AlunoModel getAlunoModel() {
        return alunoModel;
    }

    public void setAlunoModel(AlunoModel alunoModel) {
        this.alunoModel = alunoModel;
    }

    public AnoLetivoModel getAnoLetivoModel() {
        return anoLetivoModel;
    }

    public void setAnoLetivoModel(AnoLetivoModel anoLetivoModel) {
        this.anoLetivoModel = anoLetivoModel;
    }

    public CursoModel getCursoModel() {
        return cursoModel;
    }

    public void setCursoModel(CursoModel cursoModel) {
        this.cursoModel = cursoModel;
    }

    public List<Integer> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Integer> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "MatriculaAlunoModel{" +
                "nroMatricula=" + nroMatricula +
                ", dtMatricula=" + dtMatricula +
                ", statusMatricula=" + statusMatricula +
                ", alunoModel=" + alunoModel +
                ", anoLetivoModel=" + anoLetivoModel +
                ", cursoModel=" + cursoModel +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
