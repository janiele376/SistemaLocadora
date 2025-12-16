package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    
    public static Connection AbrirConexao() {
        Connection con = null;
        try {
            // Carrega o driver
            Class.forName("com.mysql.jdbc.Driver");
            // String de conexão com o banco 'locadora'
            String url = "jdbc:mysql://localhost/locadora";
            // Estabelece a conexão (Usuário: root, Senha: ou vazia ou a que você definiu)
            con = DriverManager.getConnection(url, "root", ""); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o Banco: " + e.getMessage());
        }
        return con;
    }
    
    public static void FecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}