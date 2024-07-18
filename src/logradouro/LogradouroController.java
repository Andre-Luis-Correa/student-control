package logradouro;

import exceptions.InsertSqlException;

public class LogradouroController {

    private LogradouroDAO logradouroDAO;

    public LogradouroController() {
        this.logradouroDAO = new LogradouroDAO();
    }

    public int cadastrarLogradouro(LogradouroModel logradouro) throws InsertSqlException {
        return logradouroDAO.insert(logradouro);
    }
}
