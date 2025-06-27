package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ActualizarPerfilServlet")
public class ActualizarPerfilServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String nuevoNombre = request.getParameter("nombre");
            String nuevoEmail = request.getParameter("email");
            String nuevoTelefono = request.getParameter("telefono");
            String nuevaPassword = request.getParameter("password");

            usuario.setNombre(nuevoNombre);
            usuario.setEmail(nuevoEmail);
            usuario.setTelefono(nuevoTelefono);
            usuario.setContraseña(nuevaPassword);

            UsuarioDAO dao = new UsuarioDAO();
            dao.actualizarPerfil(usuario);

            session.setAttribute("usuario", usuario); // Actualizar en sesión también

            response.sendRedirect("perfil.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
