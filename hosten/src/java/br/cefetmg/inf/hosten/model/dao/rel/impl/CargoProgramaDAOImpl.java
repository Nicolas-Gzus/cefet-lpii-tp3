package br.cefetmg.inf.hosten.model.dao.rel.impl;

import br.cefetmg.inf.hosten.model.dao.rel.CargoProgramaDAO;
import br.cefetmg.inf.hosten.model.domain.rel.CargoPrograma;
import br.cefetmg.inf.util.bd.BdUtils;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoProgramaDAOImpl implements CargoProgramaDAO {
    private static CargoProgramaDAOImpl instancia;
    private static Connection con;

    private CargoProgramaDAOImpl() {
        con = new ConnectionFactory().getConnection();
    }
    
    public static synchronized CargoProgramaDAOImpl getInstance() {
        if(instancia == null)
            instancia = new CargoProgramaDAOImpl();
        return instancia;
    }
    
    @Override
    public boolean adiciona(CargoPrograma cargoPrograma) throws SQLException {
        String qry = "INSERT INTO "
                + "CargoPrograma(codPrograma, codCargo) "
                + "VALUES(?,?)";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, cargoPrograma.getCodPrograma());
        pStmt.setString(2, cargoPrograma.getCodCargo());
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<CargoPrograma> busca(String cod, String coluna) 
            throws SQLException {
        String qry;
        if (coluna.equals("codCargo")) {
            qry = "SELECT * FROM CargoPrograma "
                    + "WHERE codCargo = ?";
        } else {
            qry = "SELECT * FROM CargoPrograma "
                    + "WHERE codPrograma = ?";
        }
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, cod);

        ResultSet rs = pStmt.executeQuery();

        List<CargoPrograma> cargoProgramaEncontrados = new ArrayList<>();

        int i = 0;
        while (rs.next()) {
            cargoProgramaEncontrados
                    .add(new CargoPrograma(
                            rs.getString(1), 
                            rs.getString(2)));
            i++;
        }
        return cargoProgramaEncontrados;
    }

    @Override
    public boolean deletaPorColuna(String cod, String coluna) 
            throws SQLException {
        String qry = "DELETE FROM CargoPrograma "
                + "WHERE " + coluna + " = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, cod);
        return pStmt.executeUpdate() > 0;
    }
}
