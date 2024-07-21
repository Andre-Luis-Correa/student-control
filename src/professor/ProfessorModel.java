package professor;

import endereco.EnderecoModel;

public class ProfessorModel {

    private String idProfessor;
    private String nomeProfessor;
    private String emailProfessor;
    private int idEndereco;
    private EnderecoModel enderecoProfessor;

    public ProfessorModel() {
    }

    public ProfessorModel(String idProfessor, String nomeProfessor, String emailProfessor, int idEndereco) {
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
        this.emailProfessor = emailProfessor;
        this.idEndereco = idEndereco;
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

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public EnderecoModel getEnderecoProfessor() {
        return enderecoProfessor;
    }

    public void setEnderecoProfessor(EnderecoModel enderecoProfessor) {
        this.enderecoProfessor = enderecoProfessor;
    }

    @Override
    public String toString() {
        return "ProfessorModel{" +
                "idProfessor='" + idProfessor + '\'' +
                ", nomeProfessor='" + nomeProfessor + '\'' +
                ", emailProfessor='" + emailProfessor + '\'' +
                ", idEndereco=" + idEndereco +
                ", enderecoProfessor=" + enderecoProfessor +
                '}';
    }
}
