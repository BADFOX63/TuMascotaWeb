package controlador;

import dao.CitaDAO;
import modelo.Cita;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/VerCitasServlet")
public class VerCitasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            try {
                CitaDAO dao = new CitaDAO();
                List<Cita> citas = dao.obtenerCitasPorUsuario(usuario.getEmail());

                request.setAttribute("citas", citas);
                request.getRequestDispatcher("ver_citas.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
