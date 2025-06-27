package controlador;

import dao.CitaDAO;
import modelo.Cita;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MisCitasServlet")
public class MisCitasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            CitaDAO dao = new CitaDAO();
            List<Cita> citas = dao.obtenerCitasPorUsuario(usuario.getEmail());  
            request.setAttribute("citas", citas);
            request.getRequestDispatcher("mis_citas.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error al obtener citas");
        }
    }
}
