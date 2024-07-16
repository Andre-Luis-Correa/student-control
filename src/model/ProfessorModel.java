package model;

public class ProfessorModel {

    private String idProfessor;
    private String nomeProfessor;
    private String emailProfessor;
    private String enderecoProfessor;

    public ProfessorModel() {
    }

    public ProfessorModel(String idProfessor, String nomeProfessor, String emailProfessor, String enderecoProfessor) {
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
        this.emailProfessor = emailProfessor;
        this.enderecoProfessor = enderecoProfessor;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }

    public String getEnderecoProfessor() {
        return enderecoProfessor;
    }

    public void setEnderecoProfessor(String enderecoProfessor) {
        this.enderecoProfessor = enderecoProfessor;
    }

    @Override
    public String toString() {
        return "ProfessorModel{" +
                "idProfessor='" + idProfessor + '\'' +
                ", nomeProfessor='" + nomeProfessor + '\'' +
                ", emailProfessor='" + emailProfessor + '\'' +
                ", enderecoProfessor='" + enderecoProfessor + '\'' +
                '}';
    }
}
