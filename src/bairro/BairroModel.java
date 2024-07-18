package bairro;

public class BairroModel {

    private int idBairro;
    private String nomeBairro;

    public BairroModel() {
    }

    public BairroModel(int idBairro, String nomeBairro) {
        this.idBairro = idBairro;
        this.nomeBairro = nomeBairro;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    @Override
    public String toString() {
        return "BairroModel{" +
                "idBairro=" + idBairro +
                ", nomeBairro='" + nomeBairro + '\'' +
                '}';
    }
}
