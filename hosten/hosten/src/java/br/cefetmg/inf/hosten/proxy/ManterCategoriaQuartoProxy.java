package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.util.CallableClient;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;


public class ManterCategoriaQuartoProxy implements IManterCategoriaQuarto {

    public ManterCategoriaQuartoProxy() {
    }

    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto, 
            List<ItemConforto> itensCategoria) 
            throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("CategoriaQuarto");
        lista.add("Inserir");
        lista.add(categoriaQuarto);
        lista.add(itensCategoria);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("CategoriaQuarto");
        lista.add("Listar");
        lista.add(dadoBusca);
        lista.add(coluna);
        
        return (List<CategoriaQuarto>)operacaoRegistro(lista);
    }

    @Override
    public List<CategoriaQuarto> listarTodos() throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("CategoriaQuarto");
        lista.add("ListarTodos");
        
        return (List<CategoriaQuarto>)operacaoRegistro(lista);
    }

    @Override
    public boolean alterar(String codRegistro, 
            CategoriaQuarto categoriaQuarto,
            List<ItemConforto> itensCategoria) 
            throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("CategoriaQuarto");
        lista.add("Alterar");
        lista.add(codRegistro);
        lista.add(categoriaQuarto);
        lista.add(itensCategoria);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("CategoriaQuarto");
        lista.add("Excluir");
        lista.add(codRegistro);
        
        return (boolean)operacaoRegistro(lista);
    }
    
    public Object operacaoRegistro (ArrayList lista) {
        try {
            FutureTask retornoCallableClient = new FutureTask(new CallableClient(lista));
            Thread t = new Thread(retornoCallableClient);
            t.start();
            
            ArrayList listaRecebida = ((ArrayList)retornoCallableClient.get());
            
            String tipoObjeto = (String)listaRecebida.get(0);
            switch (tipoObjeto) {
                case "Boolean":
                    return (boolean)listaRecebida.get(1);
                case "List<CategoriaQuarto>":
                    return (List<CategoriaQuarto>)listaRecebida.get(1);
                case "Exception":
                    throw (Exception)listaRecebida.get(1);
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}