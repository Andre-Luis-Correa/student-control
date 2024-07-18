package model;

public class LogradouroModel {
    private int idLogradouro;
    private String nomeLogradouro;

    private TipoLogradouroModel tipoLogradouroModel;

    public LogradouroModel() {
    }

    public LogradouroModel(int idLogradouro, String nomeLogradouro, TipoLogradouroModel tipoLogradouroModel) {
        this.idLogradouro = idLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.tipoLogradouroModel = tipoLogradouroModel;
    }

    public int getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(int idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public TipoLogradouroModel getTipoLogradouroModel() {
        return tipoLogradouroModel;
    }

    public void setTipoLogradouroModel(TipoLogradouroModel tipoLogradouroModel) {
        this.tipoLogradouroModel = tipoLogradouroModel;
    }

    @Override
    public String toString() {
        return "LogradouroModel{" +
                "idLogradouro=" + idLogradouro +
                ", nomeLogradouro='" + nomeLogradouro + '\'' +
                ", tipoLogradouroModel=" + tipoLogradouroModel +
                '}';
    }
}
