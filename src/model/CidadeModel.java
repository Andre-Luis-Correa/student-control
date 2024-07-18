package model;

public class CidadeModel {
    private int idCidade;
    private String nomeCidade;
    private UnidadeFederacaoModel unidadeFederacaoModel;

    public CidadeModel() {
    }

    public CidadeModel(int idCidade, String nomeCidade, UnidadeFederacaoModel unidadeFederacaoModel) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.unidadeFederacaoModel = unidadeFederacaoModel;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public UnidadeFederacaoModel getUnidadeFederacaoModel() {
        return unidadeFederacaoModel;
    }

    public void setUnidadeFederacaoModel(UnidadeFederacaoModel unidadeFederacaoModel) {
        this.unidadeFederacaoModel = unidadeFederacaoModel;
    }

    @Override
    public String toString() {
        return "CidadeModel{" +
                "idCidade=" + idCidade +
                ", nomeCidade='" + nomeCidade + '\'' +
                ", unidadeFederacaoModel=" + unidadeFederacaoModel +
                '}';
    }
}
