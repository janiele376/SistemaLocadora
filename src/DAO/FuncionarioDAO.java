package DAO;

import Modelo.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; // Necessário para Listar
import java.util.List;      // Necessário para Listar

public class FuncionarioDAO extends ExecuteSQL {

    public FuncionarioDAO(Connection con) {
        super(con);
    }

    // 1. LOGAR (O método que você já tinha, mantido aqui)
    public boolean Logar(String login, String senha) {
        boolean finalResult = false;
        try {
            String consulta = "SELECT login, senha FROM funcionario WHERE login = ? AND senha = ?";
            PreparedStatement ps = getCon().prepareStatement(consulta);
            ps.setString(1, login);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Funcionario a = new Funcionario();
                    a.setLogin(rs.getString(1));
                    a.setSenha(rs.getString(2));
                    finalResult = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return finalResult;
    }

    // 2. INSERIR FUNCIONÁRIO
    public String Inserir_Funcionario(Funcionario a) {
        String sql = "INSERT INTO funcionario VALUES(0,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getLogin());
            ps.setString(3, a.getSenha());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso";
            } else {
                return "Erro ao Inserir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 3. LISTAR FUNCIONÁRIOS
    public List<Funcionario> Listar_Funcionario() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Funcionario a = new Funcionario();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setLogin(rs.getString(3));
                    a.setSenha(rs.getString(4));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
    
    // 4. PESQUISAR POR NOME
    public List<Funcionario> Pesquisar_Nome_Funcionario(String nome) {
        String sql = "SELECT * FROM funcionario WHERE nome LIKE '%" + nome + "%'";
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Funcionario a = new Funcionario();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setLogin(rs.getString(3));
                    a.setSenha(rs.getString(4));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 5. ALTERAR FUNCIONÁRIO
    public String Alterar_Funcionario(Funcionario a) {
        String sql = "UPDATE funcionario SET nome=?, login=?, senha=? WHERE idfuncionario=?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getLogin());
            ps.setString(3, a.getSenha());
            ps.setInt(4, a.getCodigo());
            
            if (ps.executeUpdate() > 0) {
                return "Alterado com Sucesso";
            } else {
                return "Erro ao Alterar";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 6. EXCLUIR FUNCIONÁRIO
    public String Excluir_Funcionario(Funcionario a) {
        String sql = "DELETE FROM funcionario WHERE idfuncionario=?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCodigo());
            
            if (ps.executeUpdate() > 0) {
                return "Excluído com Sucesso";
            } else {
                return "Erro ao Excluir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}