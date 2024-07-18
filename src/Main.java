import exceptions.SelectSqlException;
import view.MenuView;

public class Main {
    public static void main(String[] args) throws SelectSqlException {
        MenuView menuView = new MenuView();
        menuView.showMenu();
    }
}