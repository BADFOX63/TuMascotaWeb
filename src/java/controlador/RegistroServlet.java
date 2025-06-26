package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;           // ✅ Para usar Connection
import dao.Conexion;                  // ✅ Para acceder a tu clase Conexion


@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");

        Usuario usuario = new Usuario(nombre, email, telefono, password);

        try {
            // 🔍 Probar conexión antes de registrar
            try (Connection conn = Conexion.getConnection()) {
                System.out.println("✅ Conexión a la base de datos exitosa");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("❌ No se pudo conectar a la base de datos");
                request.setAttribute("mensaje", "❌ Error de conexión a la base de datos: " + ex.getMessage());
                request.getRequestDispatcher("registro_error.jsp").forward(request, response);
                return;
            }

           
            UsuarioDAO dao = new UsuarioDAO();
            dao.registrarUsuario(usuario);

           
            response.sendRedirect("registro_exitoso.jsp");

        } catch (Exception e) {
            e.printStackTrace(); // Se muestra en la consola Output de NetBeans (pestaña de Tomcat)
            request.setAttribute("mensaje", "❌ Registro fallido\n" + e.getMessage());
            request.getRequestDispatcher("registro_error.jsp").forward(request, response);
        }
    }
}

