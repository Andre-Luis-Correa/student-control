package view;

import model.LogradouroModel;
import model.TipoLogradouroModel;

import java.util.Scanner;

public class LogradouroView {

    private Scanner scanner;
    private TipoLogradouroView tipoLogradouroView;

    public LogradouroView() {
        this.scanner = new Scanner(System.in);
        this.tipoLogradouroView = new TipoLogradouroView();
    }

    public LogradouroModel lerLogradouro() {
        LogradouroModel logradouroModel = new LogradouroModel();

        System.out.print("Nome do Logradouro: ");
        logradouroModel.setNomeLogradouro(scanner.nextLine());

        TipoLogradouroModel tipoLogradouroModel = tipoLogradouroView.lerTipoLogradouro();
        logradouroModel.setTipoLogradouroModel(tipoLogradouroModel);

        return logradouroModel;
    }
}
