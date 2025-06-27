package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CerrarSesionServlet")
public class CerrarSesionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // No crea una nueva si no existe
        if (session != null) {
            session.invalidate(); // Cierra sesión
        }

        response.sendRedirect("login.jsp?mensaje=sesion_cerrada");
    }
}
