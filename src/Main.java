import dao.AddressDao;
import exceptions.InsertSqlException;
import model.AddressModel;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        AddressDao addressDao = new AddressDao();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Exibição do menu
            System.out.println("Menu:");
            System.out.println("1. Cadastrar endereço");
            System.out.println("2. Ler endereço");
            System.out.println("3. Atualizar endereço");
            System.out.println("4. Deletar endereço");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int option = -1;
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine(); // Limpa a entrada inválida
                continue; // Volta ao início do loop
            }

            try {
                switch (option) {
                    case 1:
                        // Cadastrar endereço
                        System.out.println("Cadastrar endereço");
                        AddressModel addressToAdd = new AddressModel();
                        System.out.print("Rua: ");
                        addressToAdd.setStreet(scanner.nextLine());
                        System.out.print("Número: ");
                        addressToAdd.setNumber(scanner.nextLine());
                        System.out.print("CEP: ");
                        addressToAdd.setCep(scanner.nextLine());
                        System.out.print("Cidade: ");
                        addressToAdd.setCity(scanner.nextLine());
                        System.out.print("Estado: ");
                        addressToAdd.setState(scanner.nextLine());

                        addressDao.insertAddress(addressToAdd);
                        System.out.println("Endereço cadastrado com sucesso!");
                        break;
                    case 2:
                        // Ler endereço
                        System.out.println("Ler endereço");
                        break;
                    case 3:
                        // Atualizar endereço
                        System.out.println("Atualizar endereço");
                        break;
                    case 4:
                        // Deletar endereço
                        System.out.println("Deletar endereço");
                        break;
                    case 5:
                        // Sair do sistema
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        // Opção inválida
                        System.out.println("Opção inválida!");
                }
            } catch (InsertSqlException e) {
                System.err.println("Error caused by insert (SQL): " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}