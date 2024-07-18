package unidadefederacao;

import java.util.Scanner;

public class UnidadeFederacaoView {

    private Scanner scanner;

    public UnidadeFederacaoView() {
        this.scanner = new Scanner(System.in);
    }

    public UnidadeFederacaoModel lerUnidadeFederacao() {
        UnidadeFederacaoModel unidadeFederacaoModel = new UnidadeFederacaoModel();

        System.out.print("Sigla da UF: ");
        unidadeFederacaoModel.setSiglaUF(scanner.nextLine());

        System.out.print("Nome da UF: ");
        unidadeFederacaoModel.setNomeUF(scanner.nextLine());

        return unidadeFederacaoModel;
    }
}
