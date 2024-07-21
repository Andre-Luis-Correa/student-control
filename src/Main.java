import exceptions.SelectSqlException;
import menu.MenuView;

public class Main {
    public static void main(String[] args) throws SelectSqlException {
        MenuView menuView = new MenuView();
        menuView.showMenu();
    }
}
