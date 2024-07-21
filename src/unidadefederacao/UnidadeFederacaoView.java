package unidadefederacao;

import exceptions.SelectSqlException;

import java.util.List;
import java.util.Scanner;

public class UnidadeFederacaoView {

    private UnidadeFederacaoController unidadeFederacaoController;
    private Scanner scanner;

    public UnidadeFederacaoView() {
        this.unidadeFederacaoController = new UnidadeFederacaoController();
        this.scanner = new Scanner(System.in);
    }

    public UnidadeFederacaoModel lerUnidadeFederacao() {
        UnidadeFederacaoModel unidadeSelecionada = null;

        while (unidadeSelecionada == null) {
            mostrarUnidadesFederacao();  // Chama o método para exibir as unidades federativas disponíveis

            System.out.print("\nDigite a sigla da Unidade Federativa: ");
            String sigla = scanner.nextLine();

            try {
                unidadeSelecionada = unidadeFederacaoController.buscarUnidadeFederacaoPorSigla(sigla);
                if (unidadeSelecionada == null) {
                    System.out.println("Unidade Federativa não encontrada. Por favor, tente novamente.\n");
                }
            } catch (SelectSqlException e) {
                System.out.println("Erro ao selecionar unidade federativa: " + e.getMessage());
            }
        }

        return unidadeSelecionada;
    }

    public void mostrarUnidadesFederacao() {
        try {
            List<UnidadeFederacaoModel> unidadesFederativas = unidadeFederacaoController.buscarUnidadesFederacao();
            System.out.println("Unidades Federativas do Brasil:");

            // Definindo a quantidade de colunas e larguras
            int colunas = 3;
            int larguraColuna = 25;
            int total = unidadesFederativas.size();
            int linhas = (int) Math.ceil((double) total / colunas);

            // Imprimindo cabeçalho
            System.out.println(".".repeat(colunas * larguraColuna + colunas + 1));
            System.out.printf(".%-25s.%-25s.%-25s.%n", "UF 1", "UF 2", "UF 3");
            System.out.println(".".repeat(colunas * larguraColuna + colunas + 1));

            // Imprimindo linhas
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    int index = i + j * linhas;
                    if (index < total) {
                        UnidadeFederacaoModel unidade = unidadesFederativas.get(index);
                        System.out.printf(".%-25s", unidade.getSiglaUF() + " - " + unidade.getNomeUF());
                    } else {
                        System.out.printf(".%-25s", ""); // Preenchimento vazio para colunas faltantes
                    }
                }
                System.out.println(".");
            }
            System.out.println(".".repeat(colunas * larguraColuna + colunas + 1));
        } catch (SelectSqlException e) {
            System.out.println("Erro ao exibir unidades federativas: " + e.getMessage());
        }
    }
}
