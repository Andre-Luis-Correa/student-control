package matriculaaluno;

import aluno.AlunoController;
import aluno.AlunoModel;
import curso.CursoController;
import curso.CursoModel;
import disciplina.DisciplinaController;
import disciplina.DisciplinaModel;
import exceptions.InsertSqlException;
import exceptions.SelectSqlException;
import professor.ProfessorModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatriculaAlunoView {

    private MatriculaAlunoController matriculaAlunoController;
    private Scanner scanner;
    private AlunoController alunoController;
    private CursoController cursoController;
    private DisciplinaController disciplinaController;

    public MatriculaAlunoView() {
        this.matriculaAlunoController = new MatriculaAlunoController();
        this.scanner = new Scanner(System.in);
        this.alunoController = new AlunoController();
        this.cursoController = new CursoController();
        this.disciplinaController = new DisciplinaController();
    }

    public void matricularAluno() {
        System.out.println("Matricular aluno: \n");

        System.out.print("Digite o CPF do aluno: ");
        String cpfAluno = scanner.nextLine();
        AlunoModel alunoModel = null;
        try {
            alunoModel = alunoController.buscarPorCpf(cpfAluno);
        } catch (SelectSqlException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
            return;
        }

        if (alunoModel != null) {
            List<CursoModel> cursosDisponiveis;
            try {
                cursosDisponiveis = cursoController.buscarTodos();
            } catch (SelectSqlException e) {
                System.out.println("Erro ao buscar cursos: " + e.getMessage());
                return;
            }

            System.out.println("Cursos disponíveis:");
            for (CursoModel curso : cursosDisponiveis) {
                System.out.println(curso.getIdCurso() + ": " + curso.getNomeCurso());
            }

            System.out.print("Digite o ID do curso: ");
            int idCurso = scanner.nextInt();
            scanner.nextLine(); // consume newline
            CursoModel cursoModel = null;
            try {
                cursoModel = cursoController.buscarPorId(idCurso);
            } catch (SelectSqlException e) {
                System.out.println("Erro ao buscar curso: " + e.getMessage());
                return;
            }

            int anoLetivoAtual = LocalDate.now().getYear();
            List<DisciplinaModel> disciplinasDisponiveis;
            try {
                disciplinasDisponiveis = disciplinaController.buscarPorCursoEAnoLetivo(idCurso, anoLetivoAtual);
            } catch (SelectSqlException e) {
                System.out.println("Erro ao buscar disciplinas: " + e.getMessage());
                return;
            }

            System.out.println("Disciplinas disponíveis no ano letivo " + anoLetivoAtual + ":");
            for (DisciplinaModel disciplina : disciplinasDisponiveis) {
                System.out.println(disciplina.getIdDisciplina() + ": " + disciplina.getNomeDisciplina());

                List<ProfessorModel> professores;
                try {
                    professores = disciplinaController.buscarProfessoresPorDisciplina(disciplina.getIdDisciplina());
                    for (ProfessorModel professor : professores) {
                        System.out.println("\tProfessor: " + professor.getNomeProfessor() + " (" + professor.getEmailProfessor() + ")");
                    }
                } catch (SelectSqlException e) {
                    System.out.println("\tErro ao buscar professores para a disciplina " + disciplina.getNomeDisciplina() + ": " + e.getMessage());
                }
            }

            System.out.print("Digite os IDs das disciplinas separadas por vírgula: ");
            String[] idsDisciplinas = scanner.nextLine().split(",");
            List<Integer> disciplinas = new ArrayList<>();
            for (String idDisciplina : idsDisciplinas) {
                disciplinas.add(Integer.parseInt(idDisciplina.trim()));
            }

            try {
                matriculaAlunoController.matricularAluno(alunoModel, cursoModel, disciplinas);
                System.out.println("Aluno matriculado com sucesso!");
            } catch (InsertSqlException | SelectSqlException e) {
                System.out.println("Erro ao matricular aluno: " + e.getMessage());
            }
        } else {
            System.out.println("Cadastro de aluno não encontrado com esse CPF, realize o cadastro no menu principal");
        }
    }
}
