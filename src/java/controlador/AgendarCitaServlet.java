package controlador;

import dao.CitaDAO;
import dao.MascotaDAO;
import modelo.Mascota;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/AgendarCitaServlet")
public class AgendarCitaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");
            String idMascotaStr = request.getParameter("id_mascota");

            int idMascota;

            // Validación de fecha y hora
            LocalDate fechaSeleccionada = LocalDate.parse(fecha);
            LocalTime horaSeleccionada = LocalTime.parse(hora);
            LocalDate hoy = LocalDate.now();
            LocalTime ahora = LocalTime.now();

            if (fechaSeleccionada.isBefore(hoy)) {
                request.setAttribute("mensaje", "No puedes agendar una cita en una fecha pasada.");
                request.getRequestDispatcher("agendar_cita.jsp").forward(request, response);
                return;
            }

            if (fechaSeleccionada.isEqual(hoy) && horaSeleccionada.isBefore(ahora)) {
                request.setAttribute("mensaje", "No puedes agendar una cita en una hora pasada.");
                request.getRequestDispatcher("agendar_cita.jsp").forward(request, response);
                return;
            }

            // Si no hay ID de mascota, registrar nueva
            if (idMascotaStr == null || idMascotaStr.isEmpty()) {
                String nombreMascota = request.getParameter("nombre_mascota");
                String especie = request.getParameter("especie");
                String raza = request.getParameter("raza");

                Mascota nuevaMascota = new Mascota(nombreMascota, especie, raza, usuario.getId());
                MascotaDAO mascotaDAO = new MascotaDAO();
                mascotaDAO.registrarMascota(nuevaMascota);
                idMascota = nuevaMascota.getId();
            } else {
                idMascota = Integer.parseInt(idMascotaStr);
            }

            // Verificar disponibilidad y agendar
            CitaDAO citaDAO = new CitaDAO();
            boolean disponible = citaDAO.verificarDisponibilidad(fecha, hora);

            if (!disponible) {
                request.setAttribute("mensaje", "Ya hay una cita registrada en ese horario.");
                request.getRequestDispatcher("agendar_cita.jsp").forward(request, response);
                return;
            }

            // Guardar cita
            citaDAO.agendarCita(fecha, hora, "Pendiente", idMascota, usuario.getId());
            request.setAttribute("mensaje", "Cita agendada correctamente.");
            request.getRequestDispatcher("agendar_cita.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al agendar la cita.");
            request.getRequestDispatcher("agendar_cita.jsp").forward(request, response);
        }
    }
}
