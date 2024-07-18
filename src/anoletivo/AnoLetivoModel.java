package anoletivo;

public class AnoLetivoModel {

    private int idAnoLetivo;
    private String anoLetivo;

    public AnoLetivoModel() {
    }

    public AnoLetivoModel(int idAnoLetivo, String anoLetivo) {
        this.idAnoLetivo = idAnoLetivo;
        this.anoLetivo = anoLetivo;
    }

    public int getIdAnoLetivo() {
        return idAnoLetivo;
    }

    public void setIdAnoLetivo(int idAnoLetivo) {
        this.idAnoLetivo = idAnoLetivo;
    }

    public String getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(String anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    @Override
    public String toString() {
        return "AnoLetivoModel{" +
                "idAnoLetivo=" + idAnoLetivo +
                ", anoLetivo='" + anoLetivo + '\'' +
                '}';
    }
}
