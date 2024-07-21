package endereco;

import bairro.BairroModel;
import bairro.BairroView;
import cidade.CidadeModel;
import cidade.CidadeView;
import exceptions.SelectSqlException;
import logradouro.LogradouroModel;
import logradouro.LogradouroView;

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

    public EnderecoModel lerEndereco() throws SelectSqlException {
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
