package cidade;

import exceptions.SelectSqlException;
import unidadefederacao.UnidadeFederacaoModel;
import unidadefederacao.UnidadeFederacaoView;

import java.util.Scanner;

public class CidadeView {

    private Scanner scanner;
    private UnidadeFederacaoView unidadeFederacaoView;

    public CidadeView() {
        this.scanner = new Scanner(System.in);
        this.unidadeFederacaoView = new UnidadeFederacaoView();
    }

    public CidadeModel lerCidade() throws SelectSqlException {
        CidadeModel cidadeModel = new CidadeModel();

        System.out.print("\nNome da Cidade: ");
        cidadeModel.setNomeCidade(scanner.nextLine());

        UnidadeFederacaoModel unidadeFederacaoModel = unidadeFederacaoView.lerUnidadeFederacao();
        cidadeModel.setUnidadeFederacaoModel(unidadeFederacaoModel);

        return cidadeModel;
    }
}
