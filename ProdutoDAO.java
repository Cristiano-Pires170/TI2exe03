package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    private Connection conexao;

    public ProdutoDAO() {
        try {
            conexao = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/teste",
                "postgres",
                "1234"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean inserir(Produto p) {
        String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";

        try (PreparedStatement st = conexao.prepareStatement(sql)) {
            st.setString(1, p.getNome());
            st.setDouble(2, p.getPreco());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Produto get(int id) {
        Produto p = null;
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (PreparedStatement st = conexao.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Produto> getAll() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Statement st = conexao.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean atualizar(Produto p) {
        String sql = "UPDATE produto SET nome=?, preco=? WHERE id=?";

        try (PreparedStatement st = conexao.prepareStatement(sql)) {
            st.setString(1, p.getNome());
            st.setDouble(2, p.getPreco());
            st.setInt(3, p.getId());
            st.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM produto WHERE id=?";

        try (PreparedStatement st = conexao.prepareStatement(sql)) {
            st.setInt(1, id);
            st.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
