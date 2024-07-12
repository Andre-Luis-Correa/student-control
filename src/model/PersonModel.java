package model;

// Quando criar uma classe, gere os Constructor, Getter ecSetter, toString
// Escrever em inglês, tanto na tabela do banco quando na classe

public class PersonModel {
    private int id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private String email;
    private AddressModel addressModel; // Association with AddressModel class

    public PersonModel(int id, String name, String cpf, String phoneNumber, String email, AddressModel addressModel) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressModel = addressModel;
    }

    public PersonModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", addressModel=" + addressModel +
                '}';
    }
}
