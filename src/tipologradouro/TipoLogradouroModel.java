package tipologradouro;

public class TipoLogradouroModel {
    private String siglaTipoLogradouro;
    private String nomeTipoLogradouro;

    public TipoLogradouroModel() {
    }

    public TipoLogradouroModel(String siglaTipoLogradouro, String nomeTipoLogradouro) {
        this.siglaTipoLogradouro = siglaTipoLogradouro;
        this.nomeTipoLogradouro = nomeTipoLogradouro;
    }

    public String getSiglaTipoLogradouro() {
        return siglaTipoLogradouro;
    }

    public void setSiglaTipoLogradouro(String siglaTipoLogradouro) {
        this.siglaTipoLogradouro = siglaTipoLogradouro;
    }

    public String getNomeTipoLogradouro() {
        return nomeTipoLogradouro;
    }

    public void setNomeTipoLogradouro(String nomeTipoLogradouro) {
        this.nomeTipoLogradouro = nomeTipoLogradouro;
    }

    @Override
    public String toString() {
        return "TipoLogradouroModel{" +
                "siglaLogradouro='" + siglaTipoLogradouro + '\'' +
                ", nomeLogradouro='" + nomeTipoLogradouro + '\'' +
                '}';
    }
}
