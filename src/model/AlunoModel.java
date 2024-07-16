package model;

public class AlunoModel {

    private int idAluno;
    private String nomeAluno;
    private String cpfAluno;
    private String telefoneAluno;
    private String emailAluno;
    private String enderecoAluno;

    public AlunoModel() {}

    public AlunoModel(int idAluno, String nomeAluno, String cpfAluno, String telefoneAluno, String emailAluno, String enderecoAluno) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.cpfAluno = cpfAluno;
        this.telefoneAluno = telefoneAluno;
        this.emailAluno = emailAluno;
        this.enderecoAluno = enderecoAluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getEnderecoAluno() {
        return enderecoAluno;
    }

    public void setEnderecoAluno(String enderecoAluno) {
        this.enderecoAluno = enderecoAluno;
    }

    @Override
    public String toString() {
        return "AlunoModel{" +
                "idAluno='" + idAluno + '\'' +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", cpfAluno='" + cpfAluno + '\'' +
                ", telefoneAluno='" + telefoneAluno + '\'' +
                ", emailAluno='" + emailAluno + '\'' +
                ", enderecoAluno='" + enderecoAluno + '\'' +
                '}';
    }
}
