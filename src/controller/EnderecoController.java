package controller;

import dao.*;
import exceptions.InsertSqlException;
import model.EnderecoModel;

public class EnderecoController {
    private EnderecoDAO enderecoDAO;
    private UnidadeFederacaoController unidadeFederacaoController;
    private CidadeController cidadeController;
    private BairroController bairroController;
    private TipoLogradouroController tipoLogradouroController;
    private LogradouroController logradouroController;

    public EnderecoController() {
        this.enderecoDAO = new EnderecoDAO();
        this.unidadeFederacaoController = new UnidadeFederacaoController();
        this.cidadeController = new CidadeController();
        this.bairroController = new BairroController();
        this.tipoLogradouroController = new TipoLogradouroController();
        this.logradouroController = new LogradouroController();
    }

    public int cadastrarEndereco(EnderecoModel endereco) throws InsertSqlException {
        // Inserir Unidade Federativa
        String siglaUF = unidadeFederacaoController.cadastrarUnidadeFederacao(endereco.getCidadeModel().getUnidadeFederacaoModel());
        endereco.getCidadeModel().getUnidadeFederacaoModel().setSiglaUF(siglaUF);

        // Inserir Cidade
        int idCidade = cidadeController.cadastrarCidade(endereco.getCidadeModel());
        endereco.getCidadeModel().setIdCidade(idCidade);

        // Inserir Bairro
        int idBairro = bairroController.cadastrarBairro(endereco.getBairroModel());
        endereco.getBairroModel().setIdBairro(idBairro);

        // Inserir Tipo de Logradouro
        String siglaTipoLogradouro = tipoLogradouroController.cadastrarTipoLogradouro(endereco.getLogradouroModel().getTipoLogradouroModel());
        endereco.getLogradouroModel().getTipoLogradouroModel().setSiglaTipoLogradouro(siglaTipoLogradouro);

        // Inserir Logradouro
        int idLogradouro = logradouroController.cadastrarLogradouro(endereco.getLogradouroModel());
        endereco.getLogradouroModel().setIdLogradouro(idLogradouro);

        // Inserir Endereço
        return enderecoDAO.insert(endereco);
    }
}
