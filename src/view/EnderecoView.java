package view;

import model.*;

import java.util.Scanner;

public class EnderecoView {
    private Scanner scanner;
    private LogradouroView logradouroView;
    private CidadeView cidadeView;
    private BairroView bairroView;

    public EnderecoView() {
        this.scanner = new Scanner(System.in);
        this.logradouroView = new LogradouroView();
        this.cidadeView = new CidadeView();
        this.bairroView = new BairroView();
    }

    public EnderecoModel lerEndereco() {
        EnderecoModel enderecoModel = new EnderecoModel();

        LogradouroModel logradouroModel = logradouroView.lerLogradouro();
        enderecoModel.setLogradouroModel(logradouroModel);

        CidadeModel cidadeModel = cidadeView.lerCidade();
        enderecoModel.setCidadeModel(cidadeModel);

        BairroModel bairroModel = bairroView.lerBairro();
        enderecoModel.setBairroModel(bairroModel);

        System.out.print("CEP do Endereço: ");
        enderecoModel.setCepEndereco(scanner.nextLine());

        return enderecoModel;
    }

}
