package anoletivo;

import exceptions.InsertSqlException;
import exceptions.SelectSqlException;

import java.util.List;
import java.util.Scanner;

public class AnoLetivoView {

    private AnoLetivoController anoLetivoController;
    private Scanner scanner;

    public AnoLetivoView() {
        this.anoLetivoController = new AnoLetivoController();
        this.scanner = new Scanner(System.in);
    }

    public void listarAnosLetivos() {
        try {
            List<AnoLetivoModel> anosLetivos = anoLetivoController.buscarTodos();
            if (anosLetivos.isEmpty()) {
                System.out.println("Nenhum ano letivo encontrado.");
            } else {
                System.out.println("Anos letivos:");
                for (AnoLetivoModel anoLetivo : anosLetivos) {
                    System.out.println("ID: " + anoLetivo.getIdAnoLetivo() + ", Ano: " + anoLetivo.getAnoLetivo());
                }
            }
        } catch (SelectSqlException e) {
            System.out.println("Erro ao buscar anos letivos: " + e.getMessage());
        }
    }

    public void adicionarAnoLetivo() {
        System.out.print("Digite o ano letivo: ");
        int anoLetivo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        AnoLetivoModel anoLetivoModel = new AnoLetivoModel();
        anoLetivoModel.setAnoLetivo(anoLetivo);

        try {
            anoLetivoController.adicionarAnoLetivo(anoLetivoModel);
            System.out.println("Ano letivo adicionado com sucesso!");
        } catch (InsertSqlException e) {
            System.out.println("Erro ao adicionar ano letivo: " + e.getMessage());
        }
    }

    public void buscarAnoLetivo() {
        System.out.print("Digite o ID do ano letivo: ");
        int idAnoLetivo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            AnoLetivoModel anoLetivoModel = anoLetivoController.buscarPorId(idAnoLetivo);
            if (anoLetivoModel == null) {
                System.out.println("Ano letivo não encontrado.");
            } else {
                System.out.println("Ano letivo encontrado: ID: " + anoLetivoModel.getIdAnoLetivo() + ", Ano: " + anoLetivoModel.getAnoLetivo());
            }
        } catch (SelectSqlException e) {
            System.out.println("Erro ao buscar ano letivo: " + e.getMessage());
        }
    }
}
