package view;

import model.TipoLogradouroModel;

import java.util.Scanner;

public class TipoLogradouroView {

    private Scanner scanner;

    public TipoLogradouroView() {
        this.scanner = new Scanner(System.in);
    }

    public TipoLogradouroModel lerTipoLogradouro() {
        TipoLogradouroModel tipoLogradouroModel = new TipoLogradouroModel();

        System.out.print("Sigla do Tipo de Logradouro: ");
        tipoLogradouroModel.setSiglaTipoLogradouro(scanner.nextLine());

        System.out.print("Nome do Tipo de Logradouro: ");
        tipoLogradouroModel.setNomeTipoLogradouro(scanner.nextLine());

        return tipoLogradouroModel;
    }
}
