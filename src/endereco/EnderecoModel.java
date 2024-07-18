package endereco;

import bairro.BairroModel;
import cidade.CidadeModel;
import logradouro.LogradouroModel;

public class EnderecoModel {
    private int idEndereco;
    private String cepEndereco;
    private BairroModel bairroModel;
    private CidadeModel cidadeModel;
    private LogradouroModel logradouroModel;

    public EnderecoModel() {
    }

    public EnderecoModel(int idEndereco, String cepEndereco, BairroModel bairroModel, CidadeModel cidadeModel, LogradouroModel logradouroModel) {
        this.idEndereco = idEndereco;
        this.cepEndereco = cepEndereco;
        this.bairroModel = bairroModel;
        this.cidadeModel = cidadeModel;
        this.logradouroModel = logradouroModel;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(String cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public BairroModel getBairroModel() {
        return bairroModel;
    }

    public void setBairroModel(BairroModel bairroModel) {
        this.bairroModel = bairroModel;
    }

    public CidadeModel getCidadeModel() {
        return cidadeModel;
    }

    public void setCidadeModel(CidadeModel cidadeModel) {
        this.cidadeModel = cidadeModel;
    }

    public LogradouroModel getLogradouroModel() {
        return logradouroModel;
    }

    public void setLogradouroModel(LogradouroModel logradouroModel) {
        this.logradouroModel = logradouroModel;
    }

    @Override
    public String toString() {
        return "EnderecoModel{" +
                "idEndereco=" + idEndereco +
                ", cepEndereco='" + cepEndereco + '\'' +
                ", bairroModel=" + bairroModel +
                ", cidadeModel=" + cidadeModel +
                ", logradouroModel=" + logradouroModel +
                '}';
    }
}
