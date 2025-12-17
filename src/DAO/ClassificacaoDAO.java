package DAO;

import Modelo.Classificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoDAO extends ExecuteSQL {

    public ClassificacaoDAO(Connection con) {
        super(con);
    }

    // 1. INSERIR (Gravar no Banco)
    public String Inserir_Classificacao(Classificacao a) {
        String sql = "INSERT INTO classificacao VALUES(0,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setDouble(2, a.getPreco());
            
            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso";
            } else {
                return "Erro ao Inserir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 2. LISTAR (Trazer todos para a tabela)
    public List<Classificacao> Listar_Classificacao() {
        String sql = "SELECT * FROM classificacao";
        List<Classificacao> lista = new ArrayList<Classificacao>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Classificacao a = new Classificacao();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setPreco(rs.getDouble(3));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 3. PESQUISAR POR NOME (Usado nos filtros)
    public List<Classificacao> Pesquisar_Nome_Classificacao(String nome) {
        String sql = "SELECT * FROM classificacao WHERE nome LIKE '%" + nome + "%'";
        List<Classificacao> lista = new ArrayList<Classificacao>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Classificacao a = new Classificacao();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setPreco(rs.getDouble(3));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 4. PESQUISAR POR CÓDIGO (Usado para preencher campos ao alterar)
    public List<Classificacao> Pesquisar_Cod_Classificacao(int cod) {
        String sql = "SELECT * FROM classificacao WHERE idclassificacao = '" + cod + "'";
        List<Classificacao> lista = new ArrayList<Classificacao>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    Classificacao a = new Classificacao();
                    a.setCodigo(rs.getInt(1));
                    a.setNome(rs.getString(2));
                    a.setPreco(rs.getDouble(3));
                    
                    lista.add(a);
                }
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // 5. ALTERAR (Atualizar dados)
    public String Alterar_Classificacao(Classificacao a) {
        String sql = "UPDATE classificacao SET nome = ?, preco = ? WHERE idclassificacao = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setDouble(2, a.getPreco());
            ps.setInt(3, a.getCodigo());
            
            if (ps.executeUpdate() > 0) {
                return "Alterado com Sucesso";
            } else {
                return "Erro ao Alterar";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // 6. EXCLUIR (Deletar)
    public String Excluir_Classificacao(Classificacao a) {
        String sql = "DELETE FROM classificacao WHERE idclassificacao = ?";
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