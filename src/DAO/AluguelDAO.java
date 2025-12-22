package DAO;

import Modelo.Aluguel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    // 1. Listar TODOS os Aluguéis
    public List<Aluguel> Listar_Aluguel() {
        String sql = "SELECT * FROM aluguel";
        List<Aluguel> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Aluguel a = new Aluguel();
                    a.setCodigo(rs.getInt("idaluguel"));
                    a.setCod_dvd(rs.getInt("iddvd"));
                    a.setCod_cliente(rs.getInt("idcliente"));
                    a.setHorario(rs.getString("hora_aluguel"));
                    a.setData_aluguel(rs.getString("data_aluguel"));
                    a.setData_devolucao(rs.getString("data_devolucao"));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 2. Pesquisar por Código do ALUGUEL
    public List<Aluguel> Pesquisar_Por_Codigo_Aluguel(int codigo) {
        String sql = "SELECT * FROM aluguel WHERE idaluguel = " + codigo;
        List<Aluguel> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Aluguel a = new Aluguel();
                    a.setCodigo(rs.getInt("idaluguel"));
                    a.setCod_dvd(rs.getInt("iddvd"));
                    a.setCod_cliente(rs.getInt("idcliente"));
                    a.setHorario(rs.getString("hora_aluguel"));
                    a.setData_aluguel(rs.getString("data_aluguel"));
                    a.setData_devolucao(rs.getString("data_devolucao"));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 3. Pesquisar por Código do DVD
    public List<Aluguel> Pesquisar_Por_DVD(int codDVD) {
        String sql = "SELECT * FROM aluguel WHERE iddvd = " + codDVD;
        List<Aluguel> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Aluguel a = new Aluguel();
                    a.setCodigo(rs.getInt("idaluguel"));
                    a.setCod_dvd(rs.getInt("iddvd"));
                    a.setCod_cliente(rs.getInt("idcliente"));
                    a.setHorario(rs.getString("hora_aluguel"));
                    a.setData_aluguel(rs.getString("data_aluguel"));
                    a.setData_devolucao(rs.getString("data_devolucao"));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 4. Pesquisar por Código do CLIENTE
    public List<Aluguel> Pesquisar_Por_Cliente(int codCliente) {
        String sql = "SELECT * FROM aluguel WHERE idcliente = " + codCliente;
        List<Aluguel> lista = new ArrayList<Aluguel>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Aluguel a = new Aluguel();
                    a.setCodigo(rs.getInt("idaluguel"));
                    a.setCod_dvd(rs.getInt("iddvd"));
                    a.setCod_cliente(rs.getInt("idcliente"));
                    a.setHorario(rs.getString("hora_aluguel"));
                    a.setData_aluguel(rs.getString("data_aluguel"));
                    a.setData_devolucao(rs.getString("data_devolucao"));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
}