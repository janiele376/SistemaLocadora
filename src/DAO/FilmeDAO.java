package DAO;

import Modelo.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO extends ExecuteSQL {

    public FilmeDAO(Connection con) {
        super(con);
    }

    // 1. INSERIR
    public String Inserir_Filme(Filme a) {
        String sql = "INSERT INTO filme VALUES(0,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getTitulo());
            ps.setInt(2, a.getAno());
            ps.setString(3, a.getDuracao());
            ps.setInt(4, a.getCod_categoria());
            ps.setInt(5, a.getCod_classificacao());
            ps.setString(6, a.getCapa());
            
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
    public List<Filme> Listar_Filme() {
        String sql = "SELECT * FROM filme";
        List<Filme> lista = new ArrayList<Filme>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Filme a = new Filme();
                    a.setCodigo(rs.getInt(1));
                    a.setTitulo(rs.getString(2));
                    a.setAno(rs.getInt(3));
                    a.setDuracao(rs.getString(4));
                    a.setCod_categoria(rs.getInt(5));
                    a.setCod_classificacao(rs.getInt(6));
                    a.setCapa(rs.getString(7));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 3. PESQUISAR POR TÍTULO
    public List<Filme> Pesquisar_Nome_Filme(String nome) {
        String sql = "SELECT * FROM filme WHERE titulo LIKE '%" + nome + "%'";
        List<Filme> lista = new ArrayList<Filme>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Filme a = new Filme();
                    a.setCodigo(rs.getInt(1));
                    a.setTitulo(rs.getString(2));
                    a.setAno(rs.getInt(3));
                    a.setDuracao(rs.getString(4));
                    a.setCod_categoria(rs.getInt(5));
                    a.setCod_classificacao(rs.getInt(6));
                    a.setCapa(rs.getString(7));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
    
    // 4. PESQUISAR POR CÓDIGO (Para alterar)
    public List<Filme> Pesquisar_Cod_Filme(int cod) {
        String sql = "SELECT * FROM filme WHERE idfilme = '" + cod + "'";
        List<Filme> lista = new ArrayList<Filme>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Filme a = new Filme();
                    a.setCodigo(rs.getInt(1));
                    a.setTitulo(rs.getString(2));
                    a.setAno(rs.getInt(3));
                    a.setDuracao(rs.getString(4));
                    a.setCod_categoria(rs.getInt(5));
                    a.setCod_classificacao(rs.getInt(6));
                    a.setCapa(rs.getString(7));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 5. ALTERAR
    public String Alterar_Filme(Filme a) {
        String sql = "UPDATE filme SET titulo=?, ano=?, duracao=?, idcategoria=?, idclassificacao=?, capa=? WHERE idfilme=?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getTitulo());
            ps.setInt(2, a.getAno());
            ps.setString(3, a.getDuracao());
            ps.setInt(4, a.getCod_categoria());
            ps.setInt(5, a.getCod_classificacao());
            ps.setString(6, a.getCapa());
            ps.setInt(7, a.getCodigo());
            
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
    public String Excluir_Filme(Filme a) {
        String sql = "DELETE FROM filme WHERE idfilme = ?";
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