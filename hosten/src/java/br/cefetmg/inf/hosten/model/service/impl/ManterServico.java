package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.ServicoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.QuartoConsumoDAO;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.dao.rel.IQuartoConsumoDAO;
import br.cefetmg.inf.hosten.model.service.IManterServico;

public class ManterServico implements IManterServico {

    ServicoDAO objetoDAO;

    public ManterServico() {
        objetoDAO = ServicoDAO.getInstance();
    }

    @Override
    public boolean inserir(Servico servico)
            throws NegocioException, SQLException {
        // confere se já existe um serviço com aquela descrição naquela área
        List<Servico> servicosPesquisados
                = objetoDAO.buscaServico(
                        servico.getCodServicoArea(),
                        "codServicoArea");
        if (!servicosPesquisados.isEmpty()) {
            for (Servico s : servicosPesquisados) {
                if ((s.getDesServico()).equals(servico.getDesServico())) {
                    throw new NegocioException(
                            "Já existe um serviço na mesma "
                            + "área com a mesma descrição!");
                }
            }
        }

        return objetoDAO.adicionaServico(servico);
    }

    @Override
    public boolean alterar(String codRegistro, Servico servico)
            throws SQLException, NegocioException {
        // confere se já existe um serviço com aquela descrição naquela área
        List<Servico> servicosPesquisados = objetoDAO.buscaServico(
                servico.getCodServicoArea(), "codServicoArea");
        if (!servicosPesquisados.isEmpty()) {
            for (Servico s : servicosPesquisados) {
                if ((s.getDesServico()).equals(servico.getDesServico())) {
                    throw new NegocioException("Já existe um serviço na mesma "
                            + "área com a mesma descrição!");
                }
            }
        }

        return objetoDAO.atualizaServicoPorPk(codRegistro, servico);
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        // confere se o servico é usado em quartoconsumo
        IQuartoConsumoDAO dao = QuartoConsumoDAO.getInstance();
        List<QuartoConsumo> listaREL = dao.busca(
                Integer.parseInt(codRegistro),
                "seqServico");
        if (listaREL.isEmpty()) {
            return objetoDAO.deletaServicoPorPk(codRegistro);
        } else {
            throw new NegocioException(
                    "Não é possível excluir esse serviço. "
                    + "Ele já foi consumido!");
        }
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            if (coluna.equals("desServico")
                    || coluna.equals("vlrUnit")
                    || coluna.equals("codServicoArea")) {
                return objetoDAO.buscaServico(dadoBusca, coluna);
            } else {
                throw new NegocioException(
                        "Não existe essa informação em serviço! "
                        + "Busque pela descrição, "
                        + "pelo valor ou pela área de serviço!");
            }
        } else {
            throw new NegocioException("Nenhum serviço buscado!");
        }
    }

    @Override
    public List<Servico> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodosServicos();
    }
}