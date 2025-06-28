package controlador;

import dao.CitaDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/ActualizarCitaServlet")
public class ActualizarCitaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idCita = Integer.parseInt(request.getParameter("id_cita"));
            String nuevoEstado = request.getParameter("estado");
            String motivo = request.getParameter("motivo");

            if (motivo == null) {
                motivo = "";
            }

            CitaDAO dao = new CitaDAO();
            dao.actualizarEstadoCita(idCita, nuevoEstado, motivo);

            response.sendRedirect("veterinario.jsp");

        } catch (Exception e) {
            e.printStackTrace(); // Esto es lo más importante para saber el error
            response.sendRedirect("error.jsp");
        }
    }
}
