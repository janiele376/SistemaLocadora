package DAO;

import Modelo.Classificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoDAO extends ExecuteSQL {
    public ClassificacaoDAO(Connection con) { super(con); }

    public String Inserir_Classificacao(Classificacao a) {
        String sql = "INSERT INTO classificacao(nome, preco) VALUES(?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setDouble(2, a.getPreco());
            if (ps.executeUpdate() > 0) return "Inserido com Sucesso";
            else return "Erro ao Inserir";
        } catch (Exception e) { return e.getMessage(); }
    }
    
    public List<Classificacao> ListarClassificacao() {
        String sql = "SELECT * FROM classificacao";
        List<Classificacao> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classificacao a = new Classificacao();
                a.setCodigo(rs.getInt(1));
                a.setNome(rs.getString(2));
                a.setPreco(rs.getDouble(3));
                lista.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}