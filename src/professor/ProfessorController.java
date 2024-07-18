package professor;

public class ProfessorController {

    private ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    public void addProfessor(ProfessorModel professor) {
        try {
            professorDAO.insert(professor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProfessorModel getProfessor(int id) {
        try {
            return professorDAO.select(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
