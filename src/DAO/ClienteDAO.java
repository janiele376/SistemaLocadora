package DAO;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ExecuteSQL {

    public ClienteDAO(Connection con) {
        super(con);
    }

    // 1. INSERIR
    public String Inserir_Cliente(Cliente a) {
        String sql = "INSERT INTO cliente VALUES(0,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getRg());
            ps.setString(3, a.getCpf());
            ps.setString(4, a.getTelefone());
            ps.setString(5, a.getEmail());
            // Se você tiver adicionado Rua, Numero, Bairro, CEP na classe Modelo:
            ps.setString(6, a.getRua());
            ps.setInt(7, a.getNumero());
            ps.setString(8, a.getBairro());
            ps.setString(9, a.getCep());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso";
            } else {
                return "Erro ao Inserir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 2. LISTAR
    public List<Cliente> Listar_Cliente() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setRg(rs.getString(3));
                    a.setCpf(rs.getString(4));
                    a.setTelefone(rs.getString(5));
                    a.setEmail(rs.getString(6));
                    // Recuperar endereços se necessário
                    a.setRua(rs.getString(7));
                    a.setNumero(rs.getInt(8));
                    a.setBairro(rs.getString(9));
                    a.setCep(rs.getString(10));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 3. PESQUISAR POR NOME
    public List<Cliente> Pesquisar_Nome_Cliente(String nome) {
        String sql = "SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'";
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setRg(rs.getString(3));
                    a.setCpf(rs.getString(4));
                    a.setTelefone(rs.getString(5));
                    a.setEmail(rs.getString(6));
                    a.setRua(rs.getString(7));
                    a.setNumero(rs.getInt(8));
                    a.setBairro(rs.getString(9));
                    a.setCep(rs.getString(10));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 4. PESQUISAR POR CÓDIGO
    public List<Cliente> Pesquisar_Cod_Cliente(int cod) {
        String sql = "SELECT * FROM cliente WHERE idcliente = '" + cod + "'";
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Cliente a = new Cliente();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setRg(rs.getString(3));
                    a.setCpf(rs.getString(4));
                    a.setTelefone(rs.getString(5));
                    a.setEmail(rs.getString(6));
                    a.setRua(rs.getString(7));
                    a.setNumero(rs.getInt(8));
                    a.setBairro(rs.getString(9));
                    a.setCep(rs.getString(10));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 5. ALTERAR
    public String Alterar_Cliente(Cliente a) {
        String sql = "UPDATE cliente SET nome=?, rg=?, cpf=?, telefone=?, email=?, rua=?, numero=?, bairro=?, cep=? WHERE idcliente=?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getRg());
            ps.setString(3, a.getCpf());
            ps.setString(4, a.getTelefone());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getRua());
            ps.setInt(7, a.getNumero());
            ps.setString(8, a.getBairro());
            ps.setString(9, a.getCep());
            ps.setInt(10, a.getCodigo());
            
            if (ps.executeUpdate() > 0) {
                return "Alterado com Sucesso";
            } else {
                return "Erro ao Alterar";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 6. EXCLUIR
    public String Excluir_Cliente(Cliente a) {
        String sql = "DELETE FROM cliente WHERE idcliente = ?";
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