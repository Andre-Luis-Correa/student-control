package model;

public class UnidadeFederacaoModel {
    private String siglaUF;
    private String nomeUF;

    public UnidadeFederacaoModel() {

    }

    public UnidadeFederacaoModel(String siglaUF, String nomeUF) {
        this.siglaUF = siglaUF;
        this.nomeUF = nomeUF;
    }

    public String getSiglaUF() {
        return siglaUF;
    }

    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    public String getNomeUF() {
        return nomeUF;
    }

    public void setNomeUF(String nomeUF) {
        this.nomeUF = nomeUF;
    }

    @Override
    public String toString() {
        return "UnidadeFederacaoModel{" +
                "siglaUF='" + siglaUF + '\'' +
                ", nomeUF='" + nomeUF + '\'' +
                '}';
    }

}
