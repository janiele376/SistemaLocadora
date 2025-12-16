package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    
    public static Connection AbrirConexao() {
        Connection con = null;
        try {
            // No NetBeans 8.1 com MySQL 5.x ou bibliotecas antigas, use este driver:
            Class.forName("com.mysql.jdbc.Driver");
            
            // Configuração da URL (Localhost, porta padrão 3306, banco locadora)
            String url = "jdbc:mysql://localhost:3306/locadora";
            
            // Tenta conectar (root e senha vazia é o padrão do XAMPP/WAMP)
            // Se tiver senha, coloque no lugar das aspas vazias ""
            con = DriverManager.getConnection(url, "root", ""); 
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver do Banco não encontrado! \nAdicione a biblioteca mysql-connector.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o Banco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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