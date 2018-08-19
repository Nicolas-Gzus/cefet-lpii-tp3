package br.cefetmg.inf.hosten.controller.alterar;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConforto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;

public class AlterarItemConforto {

    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            ManterItemConforto manterItem = new ManterItemConforto();
            
            String codItemEditar = request.getParameter("codItemAntigo");
            
            List<ItemConforto> itemPesquisar = manterItem.listar(codItemEditar, "codItem");
            if (!itemPesquisar.isEmpty()) {
                String codItem = request.getParameter("codItem");
                String desItem = request.getParameter("desItem");

                ItemConforto itemConforto = new ItemConforto(codItem, desItem);
                manterItem.alterar(codItemEditar, itemConforto);
                jsp = "/view/itens-conforto.jsp";
            } else {
                String erro = "Ocorreu erro ao alterar o item de conforto!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}