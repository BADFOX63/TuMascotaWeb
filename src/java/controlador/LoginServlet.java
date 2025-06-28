package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validar que se recibieron correctamente
        System.out.println("Email: " + email);
        System.out.println("Contraseña: " + password);

        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.validarUsuario(email, password);

           if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            if (usuario.getIdRol() == 2) {
                response.sendRedirect("veterinario.jsp"); // Si es veterinario
            }   else {
        response.sendRedirect("inicio.jsp"); // Usuario normal
    }
} else {
    request.setAttribute("mensaje", "Correo o contraseña incorrectos.");
    request.getRequestDispatcher("login.jsp").forward(request, response);
}

        } catch (Exception e) {
            e.printStackTrace();  // Se mostrará en la consola del servidor
            request.setAttribute("mensaje", "Error al validar usuario");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
