package DAO;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends ExecuteSQL {

    public CategoriaDAO(Connection con) {
        super(con);
    }

    // 1. INSERIR
    public String Inserir_Categoria(Categoria a) {
        String sql = "INSERT INTO categoria VALUES(0,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso";
            } else {
                return "Erro ao Inserir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 2. LISTAR (Tudo)
    public List<Categoria> Listar_Categoria() {
        String sql = "SELECT * FROM categoria";
        List<Categoria> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Categoria a = new Categoria();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 3. PESQUISAR POR NOME
    public List<Categoria> Pesquisar_Nome_Categoria(String nome) {
        String sql = "SELECT * FROM categoria WHERE nome LIKE '%" + nome + "%'";
        List<Categoria> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Categoria a = new Categoria();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
    
    // 4. PESQUISAR POR CÓDIGO (Para alterar)
    public List<Categoria> Pesquisar_Cod_Categoria(int cod) {
        String sql = "SELECT * FROM categoria WHERE idcategoria = '" + cod + "'";
        List<Categoria> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Categoria a = new Categoria();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 5. ALTERAR
    public String Alterar_Categoria(Categoria a) {
        String sql = "UPDATE categoria SET nome = ? WHERE idcategoria = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getCodigo());
            
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
    public String Excluir_Categoria(Categoria a) {
        String sql = "DELETE FROM categoria WHERE idcategoria = ?";
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