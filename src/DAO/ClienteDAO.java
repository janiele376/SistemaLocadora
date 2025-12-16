package DAO;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO extends ExecuteSQL {
    public ClienteDAO(Connection con) {
        super(con);
    }

    // 1. CADASTRAR (INSERT)
    public String Inserir_Cliente(Cliente a) {
        String sql = "INSERT INTO cliente(nome, rg, cpf, telefone, email) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getRg());
            ps.setString(3, a.getCpf());
            ps.setString(4, a.getTelefone());
            ps.setString(5, a.getEmail());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso!";
            } else {
                return "Erro ao inserir!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 2. LISTAR (SELECT para a Tabela)
    public List<Cliente> ListarCliente() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                    a.setCodigo(rs.getInt(1)); // idcliente
                    a.setNome(rs.getString(2));
                    a.setRg(rs.getString(3));
                    a.setCpf(rs.getString(4));
                    // ... pegar outros campos
                    lista.add(a);
                }
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    // 3. ALTERAR (UPDATE)
    public String Alterar_Cliente(Cliente a) {
        String sql = "UPDATE cliente SET nome = ?, rg = ?, cpf = ? WHERE idcliente = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getRg());
            ps.setString(3, a.getCpf());
            ps.setInt(4, a.getCodigo()); // Usa o ID para saber quem alterar
            
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso!";
            } else {
                return "Erro ao alterar!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 4. EXCLUIR (DELETE)
    public String Excluir_Cliente(Cliente a) {
        String sql = "DELETE FROM cliente WHERE idcliente = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCodigo());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso!";
            } else {
                return "Erro ao excluir!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
