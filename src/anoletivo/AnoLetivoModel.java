package anoletivo;

public class AnoLetivoModel {
    private int idAnoLetivo;
    private int anoLetivo;

    public AnoLetivoModel() {
    }

    public AnoLetivoModel(int idAnoLetivo, int anoLetivo) {
        this.idAnoLetivo = idAnoLetivo;
        this.anoLetivo = anoLetivo;
    }

    public int getIdAnoLetivo() {
        return idAnoLetivo;
    }

    public void setIdAnoLetivo(int idAnoLetivo) {
        this.idAnoLetivo = idAnoLetivo;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    @Override
    public String toString() {
        return "AnoLetivoModel{" +
                "idAnoLetivo=" + idAnoLetivo +
                ", anoLetivo=" + anoLetivo +
                '}';
    }
}
