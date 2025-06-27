package dao;

import modelo.Usuario;
import java.sql.*;

public class UsuarioDAO {

    // REGISTRO DE USUARIO
    public void registrarUsuario(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuario (nombre, email, telefono, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefono());
            stmt.setString(4, usuario.getContraseña());

            int filas = stmt.executeUpdate();

            // Validar si se insertó correctamente
            if (filas > 0) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("No se insertó ningún usuario.");
            }

        } catch (SQLException e) {
            System.err.println("Error en registrarUsuario: " + e.getMessage());
            throw new Exception("Error al registrar usuario", e);
        }
    }

    // VALIDAR USUARIO PARA LOGIN
    public Usuario validarUsuario(String email, String password) throws Exception {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND password = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            System.out.println("Ejecutando login con email: " + email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("password")
                );
                System.out.println("Usuario encontrado: " + usuario.getNombre());
            } else {
                System.out.println("Usuario no encontrado o password incorrecta.");
            }

        } catch (SQLException e) {
            System.err.println("Error en validarUsuario: " + e.getMessage());
            throw new Exception("Error al validar usuario", e);
        }

        return usuario;
    }
}
