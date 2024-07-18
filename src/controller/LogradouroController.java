package controller;

import dao.LogradouroDAO;
import exceptions.InsertSqlException;
import model.LogradouroModel;

public class LogradouroController {

    private LogradouroDAO logradouroDAO;

    public LogradouroController() {
        this.logradouroDAO = new LogradouroDAO();
    }

    public int cadastrarLogradouro(LogradouroModel logradouro) throws InsertSqlException {
        return logradouroDAO.insert(logradouro);
    }
}
