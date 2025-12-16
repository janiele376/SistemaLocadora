package DAO;

import Modelo.Aluguel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane; 

public class AluguelDAO extends ExecuteSQL {

    public AluguelDAO(Connection con) {
        super(con);
    }

    // Verifica se o código do DVD existe no banco
    public boolean Testar_DVD(int cod) {
        boolean resultado = false;
        try {
            String sql = "SELECT iddvd FROM dvd WHERE iddvd = " + cod;
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    resultado = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Verifica se o DVD está Disponível (não alugado)
    public boolean Testar_Situacao(int cod) {
        boolean resultado = false;
        try {
            String sql = "SELECT iddvd FROM dvd WHERE iddvd = " + cod + " AND situacao = 'Disponível'";
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    resultado = true; // Está disponível
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Registra o Aluguel e muda a situação do DVD para 'Alugado'
    public void Inserir_Aluguel(Aluguel a) {
        String sql = "INSERT INTO aluguel(iddvd, idcliente, hora_aluguel, data_aluguel) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCod_dvd());      
            ps.setInt(2, a.getCod_cliente());
            ps.setString(3, a.getHorario());
            ps.setString(4, a.getData_aluguel());
            
            ps.execute();
            
            // ATUALIZA A SITUAÇÃO DO DVD
            String sqlUpdate = "UPDATE dvd SET situacao = 'Alugado' WHERE iddvd = ?";
            PreparedStatement psUp = getCon().prepareStatement(sqlUpdate);
            psUp.setInt(1, a.getCod_dvd());
            
            psUp.execute();
            
            JOptionPane.showMessageDialog(null, "Locação realizada com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na Locação: " + e.getMessage());
        }
    }
}