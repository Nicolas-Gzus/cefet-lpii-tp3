package br.cefetmg.inf.hosten.controller.listar;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.ManterServicoAreaProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ListarServicoAreas {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        String tipoAcao = request.getParameter("tipoAcao");
        
        try {
            IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
            List<ServicoArea> listaServicoAreas = manterServicoArea.listarTodos();
            
            request.setAttribute("listaServicoAreas", listaServicoAreas);
            
            if (tipoAcao.equals("Padrao")) {
                jsp = "/view/servicos-areas.jsp";
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", e.getMessage());
            jsp = "erro.jsp";
        }
        return jsp;
    }
    
}
