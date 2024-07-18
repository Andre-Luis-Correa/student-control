package matriculaaluno;

import aluno.AlunoModel;
import anoletivo.AnoLetivoModel;

import java.time.LocalDate;

public class MatriculaAlunoModel {

    private int nroMatricula;
    private LocalDate dtMatricula;

    private AlunoModel alunoModel;

    private AnoLetivoModel anoLetivoModel;

    public MatriculaAlunoModel() {
    }

    public MatriculaAlunoModel(int nroMatricula, LocalDate dtMatricula, AlunoModel alunoModel, AnoLetivoModel anoLetivoModel) {
        this.nroMatricula = nroMatricula;
        this.dtMatricula = dtMatricula;
        this.alunoModel = alunoModel;
        this.anoLetivoModel = anoLetivoModel;
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

    @Override
    public String toString() {
        return "MatriculaAluno{" +
                "nroMatricula=" + nroMatricula +
                ", dtMatricula=" + dtMatricula +
                ", alunoModel=" + alunoModel +
                ", anoLetivoModel=" + anoLetivoModel +
                '}';
    }
}
