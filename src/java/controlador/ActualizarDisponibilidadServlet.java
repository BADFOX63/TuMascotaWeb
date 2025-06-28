package controlador;

import dao.DisponibilidadDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ActualizarDisponibilidadServlet")
public class ActualizarDisponibilidadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || usuario.getIdRol() != 2) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        try {
            DisponibilidadDAO dao = new DisponibilidadDAO();

            if ("Eliminar Día Completo".equals(accion)) {
                String fechaCompleta = request.getParameter("fechaCompleta");
                dao.bloquearDiaCompleto(fechaCompleta, usuario.getId());

            } else if ("Eliminar Horas Seleccionadas".equals(accion)) {
                String fechaHora = request.getParameter("fechaHora");
                String[] horas = request.getParameterValues("horas");

                if (horas != null) {
                    for (String hora : horas) {
                        dao.bloquearHorario(fechaHora, hora, usuario.getId());
                    }
                }
            }

            response.sendRedirect("calendario.jsp?mensaje=ok");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
