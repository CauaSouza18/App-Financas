package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Ganho;

public class GanhoDAO {

    public ArrayList<Ganho> listGanhos() throws SQLException {
        String sql = "SELECT * FROM ganhos";
        ArrayList<Ganho> ganhos = new ArrayList<>();

        try (Connection conn = Conexao.conectar(); PreparedStatement comando = conn.prepareStatement(sql); var resultado = comando.executeQuery()) {

            while (resultado.next()) {
                int id = resultado.getInt("id");
                double valor = resultado.getDouble("valor");
                String plataforma = resultado.getString("plataforma");
                LocalDate Cadastrado_Em = resultado.getDate("Cadastrado_Em").toLocalDate();

                Ganho ganho = new Ganho(id, valor, plataforma, Cadastrado_Em);
                ganhos.add(ganho);
            }
        }
        return ganhos;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE from ganhos WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement comando = conn.prepareStatement(sql)) {
            comando.setInt(1, id);
            comando.executeUpdate();

        }

    }

    public void salvar(Ganho ganho) throws SQLException {
        String sql = "INSERT INTO ganhos (valor, plataforma, Cadastrado_Em) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement comando = conn.prepareStatement(sql)) {
            comando.setDouble(1, ganho.getValor());
            comando.setString(2, ganho.getPlataforma());
            comando.setObject(3, ganho.getCadastrado_Em());
            comando.executeUpdate();
        }
    }

}
