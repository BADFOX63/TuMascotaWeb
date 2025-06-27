package dao;

import modelo.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    // Obtener todas las citas asociadas a un usuario según su correo
    public List<Cita> obtenerCitasPorUsuario(String emailUsuario) throws Exception {
        List<Cita> citas = new ArrayList<>();

        String sql = """
            SELECT c.fecha, c.hora, c.estado, c.id_mascota
            FROM cita c
            JOIN usuario u ON c.id_usuario = u.id_usuario
            WHERE u.email = ?
        """;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emailUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("estado"),
                        rs.getInt("id_mascota")
                );
                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener citas", e);
        }

        return citas;
    }

    // Verificar horarios ocupados para una fecha
    public List<String> obtenerHorasOcupadas(String fecha) throws Exception {
        List<String> horasOcupadas = new ArrayList<>();
        String sql = "SELECT hora FROM cita WHERE fecha = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fecha);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                horasOcupadas.add(rs.getString("hora"));
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener horas ocupadas", e);
        }

        return horasOcupadas;
    }

    // Agendar una cita
    public void agendarCita(String fecha, String hora, String estado, int idMascota, int idUsuario) throws Exception {
        String sql = "INSERT INTO cita (fecha, hora, estado, id_mascota, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fecha);
            stmt.setString(2, hora);
            stmt.setString(3, estado);
            stmt.setInt(4, idMascota);
            stmt.setInt(5, idUsuario);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agendar cita", e);
        }
    }

    // Verifica si un horario está disponible en una fecha
    public boolean verificarDisponibilidad(String fecha, String hora) throws Exception {
        String sql = "SELECT COUNT(*) FROM cita WHERE fecha = ? AND hora = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fecha);
            stmt.setString(2, hora);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // true si no hay citas en ese horario
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al verificar disponibilidad", e);
        }

        return false;
    }
}
