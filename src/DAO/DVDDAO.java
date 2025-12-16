package DAO;

import Modelo.DVD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DVDDAO extends ExecuteSQL {

    public DVDDAO(Connection con) {
        super(con);
    }

    // --- INSERIR ---
    public String Inserir_DVD(DVD a) {
        String sql = "INSERT INTO dvd(idfilme, preco_compra, data_compra, situacao) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCod_filme());
            ps.setDouble(2, a.getPreco_compra());
            ps.setString(3, a.getData_compra());
            ps.setString(4, a.getSituacao()); // Ex: "Disponível"

            if (ps.executeUpdate() > 0) {
                return "Inserido com Sucesso";
            } else {
                return "Erro ao Inserir";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // --- LISTAR TODOS ---
    public List<DVD> Listar_DVD() {
        String sql = "SELECT * FROM dvd";
        List<DVD> lista = new ArrayList<>();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    DVD a = new DVD();
                    a.setCodigo(rs.getInt(1)); // iddvd
                    a.setCod_filme(rs.getInt(2)); // idfilme
                    a.setPreco_compra(rs.getDouble(3));
                    a.setData_compra(rs.getString(4));
                    a.setSituacao(rs.getString(5));

                    lista.add(a);
                }
                return lista;
            } else {
                return new ArrayList<>();
            }
        } catch (SQLException e) {
            return null;
        }
    }

    // --- PESQUISAR POR CÓDIGO (Para preencher campos na alteração) ---
    public DVD Pesquisar_Cod_DVD(int cod) {
        String sql = "SELECT * FROM dvd WHERE iddvd = ?";
        DVD a = new DVD();
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, cod);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a.setCodigo(rs.getInt(1));
                a.setCod_filme(rs.getInt(2));
                a.setPreco_compra(rs.getDouble(3));
                a.setData_compra(rs.getString(4));
                a.setSituacao(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    // --- VERIFICAR EXISTÊNCIA (Usado no Aluguel) ---
    public boolean CheckExist(int codigo) {
        String sql = "SELECT iddvd FROM dvd WHERE iddvd = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Boa prática: mostrar o erro no console
        }
        return false;
    }

    // --- ALTERAR ---
    public String Alterar_DVD(DVD a) {
        String sql = "UPDATE dvd SET idfilme = ?, preco_compra = ?, data_compra = ?, situacao = ? WHERE iddvd = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setInt(1, a.getCod_filme());
            ps.setDouble(2, a.getPreco_compra());
            ps.setString(3, a.getData_compra());
            ps.setString(4, a.getSituacao());
            ps.setInt(5, a.getCodigo()); // WHERE iddvd

            if (ps.executeUpdate() > 0) {
                return "Atualizado com Sucesso";
            } else {
                return "Erro ao Atualizar";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    // --- ATUALIZAR SITUAÇÃO (Específico para quando Alugar/Devolver) ---
    public void Atualizar_Situacao(int idDVD, String novaSituacao) {
        String sql = "UPDATE dvd SET situacao = ? WHERE iddvd = ?";
        try {
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, novaSituacao);
            ps.setInt(2, idDVD);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- EXCLUIR ---
    public String Excluir_DVD(DVD a) {
        String sql = "DELETE FROM dvd WHERE iddvd = ?";
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