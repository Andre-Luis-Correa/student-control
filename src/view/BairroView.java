package view;

import model.BairroModel;

import java.util.Scanner;

public class BairroView {

    private Scanner scanner;

    public BairroView() {
        this.scanner = new Scanner(System.in);
    }

    public BairroModel lerBairro() {
        BairroModel bairroModel = new BairroModel();

        System.out.print("Nome do Bairro: ");
        bairroModel.setNomeBairro(scanner.nextLine());

        return bairroModel;
    }
}
