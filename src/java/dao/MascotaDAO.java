package dao;

import modelo.Mascota;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    public void registrarMascota(Mascota mascota) throws Exception {
        String sql = "INSERT INTO mascota (nombre, especie, raza, id_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getIdUsuario());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                mascota.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al registrar mascota", e);
        }
    }

    public List<Mascota> obtenerMascotasPorUsuario(int idUsuario) throws Exception {
        List<Mascota> lista = new ArrayList<>();

        String sql = "SELECT * FROM mascota WHERE id_usuario = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Mascota m = new Mascota(
                    rs.getInt("id_mascota"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("id_usuario")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al consultar mascotas", e);
        }

        return lista;
    }

public void actualizarMascota(Mascota mascota) throws Exception {
    String sql = "UPDATE mascota SET nombre = ?, especie = ?, raza = ? WHERE id_mascota = ?";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, mascota.getNombre());
        stmt.setString(2, mascota.getEspecie());
        stmt.setString(3, mascota.getRaza());
        stmt.setInt(4, mascota.getId());

        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new Exception("Error al actualizar mascota", e);
        }
    }
}