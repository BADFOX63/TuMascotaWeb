package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DisponibilidadDAO {

    public void bloquearHorario(String fecha, String hora, int idUsuario) throws Exception {
        String sql = "INSERT INTO disponibilidad (fecha, hora, id_usuario) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fecha);
            stmt.setString(2, hora);
            stmt.setInt(3, idUsuario);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al bloquear horario", e);
        }
    }

    public List<String> obtenerHorasNoDisponibles(String fecha) throws Exception {
        List<String> horas = new ArrayList<>();
        String sql = "SELECT hora FROM disponibilidad WHERE fecha = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fecha);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                horas.add(rs.getString("hora"));
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener horas no disponibles", e);
        }

        return horas;
    }
    // Método para bloquear todo un día (todas las horas de 08:00 a 18:00)
public void bloquearDiaCompleto(String fecha, int idUsuario) throws Exception {
    try (Connection conn = Conexion.getConnection()) {
        String sql = "INSERT INTO disponibilidad (fecha, hora, id_usuario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int h = 8; h <= 18; h++) {
                String hora = (h < 10 ? "0" + h : String.valueOf(h)) + ":00:00";
                stmt.setString(1, fecha);
                stmt.setString(2, hora);
                stmt.setInt(3, idUsuario);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new Exception("Error al bloquear el día completo", e);
    }
}
// Obtener todas las fechas completamente bloqueadas por un veterinario
public List<String> obtenerFechasBloqueadas(int idUsuario) throws Exception {
    List<String> fechasBloqueadas = new ArrayList<>();

    String sql = """
        SELECT fecha
        FROM disponibilidad
        WHERE id_usuario = ?
        GROUP BY fecha
        HAVING COUNT(*) >= 11
    """;

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            fechasBloqueadas.add(rs.getString("fecha"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw new Exception("Error al obtener fechas bloqueadas", e);
    }

    return fechasBloqueadas;
}

}
