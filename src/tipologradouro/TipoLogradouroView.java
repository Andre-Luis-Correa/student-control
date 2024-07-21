package tipologradouro;

import exceptions.SelectSqlException;

import java.util.List;
import java.util.Scanner;

public class TipoLogradouroView {

    private TipoLogradouroController tipoLogradouroController;
    private Scanner scanner;

    public TipoLogradouroView() {
        this.tipoLogradouroController = new TipoLogradouroController();
        this.scanner = new Scanner(System.in);
    }

    public TipoLogradouroModel lerTipoLogradouro() {
        TipoLogradouroModel tipoSelecionado = null;

        while (tipoSelecionado == null) {
            mostrarTiposLogradouro();  // Chama o método para exibir os tipos de logradouro disponíveis

            System.out.print("\nDigite a sigla do Tipo de Logradouro: ");
            String sigla = scanner.nextLine();

            try {
                tipoSelecionado = tipoLogradouroController.buscarTipoLogradouroPorSigla(sigla);
                if (tipoSelecionado == null) {
                    System.out.println("Tipo de Logradouro não encontrado. Por favor, tente novamente.\n");
                }
            } catch (SelectSqlException e) {
                System.out.println("Erro ao selecionar tipo de logradouro: " + e.getMessage());
            }
        }

        return tipoSelecionado;
    }

    public void mostrarTiposLogradouro() {
        try {
            List<TipoLogradouroModel> tiposLogradouro = tipoLogradouroController.buscarTiposLogradouro();

            // Definindo o tamanho das colunas
            int larguraSigla = 10;
            int larguraNome = 25;

            // Cabeçalho da tabela
            System.out.println("\nTipos de Logradouro:");
            System.out.println(".".repeat(larguraSigla + larguraNome));
            System.out.printf(".%-10s%-23s.%n", "  Sigla", "  Nome");
            System.out.println(".".repeat(larguraSigla + larguraNome));

            // Linhas da tabela
            for (TipoLogradouroModel tipo : tiposLogradouro) {
                System.out.printf(".  %-10s%-21s.%n", tipo.getSiglaTipoLogradouro(), tipo.getNomeTipoLogradouro());
            }
            System.out.println(".".repeat(larguraSigla + larguraNome));
        } catch (SelectSqlException e) {
            System.out.println("Erro ao exibir tipos de logradouro: " + e.getMessage());
        }
    }

}
