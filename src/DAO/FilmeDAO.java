package DAO;

import Modelo.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class FilmeDAO extends ExecuteSQL {
    public FilmeDAO(Connection con) { super(con); }

    public String Inserir_Filme(Filme a) {
        String sql = "INSERT INTO filme(titulo, ano, duracao, idcategoria, idclassificacao, capa) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getTitulo());
            ps.setInt(2, a.getAno());
            ps.setString(3, a.getDuracao());
            ps.setInt(4, a.getCod_categoria());
            ps.setInt(5, a.getCod_classificacao());
            ps.setString(6, a.getCapa());
            
            if (ps.executeUpdate() > 0) return "Inserido com Sucesso";
            else return "Erro ao Inserir";
        } catch (Exception e) { return e.getMessage(); }
    }
    
    public List<Filme> ListarFilme() {
        String sql = "SELECT * FROM filme";
        List<Filme> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Filme a = new Filme();
                a.setCodigo(rs.getInt(1));
                a.setTitulo(rs.getString(2));
                // Setar os outros campos...
                lista.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}