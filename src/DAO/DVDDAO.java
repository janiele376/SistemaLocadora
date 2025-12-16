package DAO;

import Modelo.DVD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DVDDAO extends ExecuteSQL {
    public DVDDAO(Connection con) { super(con); }

    public String Inserir_DVD(DVD a) {
        String sql = "INSERT INTO dvd(idfilme, preco_compra, data_compra, situacao) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCod_filme());
            ps.setDouble(2, a.getPreco_compra());
            ps.setString(3, a.getData_compra());
            ps.setString(4, a.getSituacao()); // Geralmente começa como "Disponível"
            
            if (ps.executeUpdate() > 0) return "Inserido com Sucesso";
            else return "Erro ao Inserir";
        } catch (Exception e) { return e.getMessage(); }
    }
    
    // Método para verificar se um DVD existe pelo código (Usado no Aluguel)
    public boolean CheckExist(int codigo) {
        // Lógica similar ao Login, retorna true se encontrar o ID
        String sql = "SELECT iddvd FROM dvd WHERE iddvd = ?";
        try {
             PreparedStatement ps = getCon().prepareStatement(sql);
             ps.setInt(1, codigo);
             ResultSet rs = ps.executeQuery();
             if (rs.next()) return true;
        } catch (Exception e) {}
        return false;
    }
}