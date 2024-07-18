package cidade;

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

    public CidadeModel lerCidade() {
        CidadeModel cidadeModel = new CidadeModel();

        System.out.print("Nome da Cidade: ");
        cidadeModel.setNomeCidade(scanner.nextLine());

        UnidadeFederacaoModel unidadeFederacaoModel = unidadeFederacaoView.lerUnidadeFederacao();
        cidadeModel.setUnidadeFederacaoModel(unidadeFederacaoModel);

        return cidadeModel;
    }
}
