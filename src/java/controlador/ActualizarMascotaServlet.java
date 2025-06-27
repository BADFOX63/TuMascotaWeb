package controlador;

import dao.MascotaDAO;
import modelo.Mascota;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ActualizarMascotaServlet")
public class ActualizarMascotaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idMascota = Integer.parseInt(request.getParameter("id_mascota"));
            String nombre = request.getParameter("nombre");
            String especie = request.getParameter("especie");
            String raza = request.getParameter("raza");

            Mascota mascota = new Mascota(idMascota, nombre, especie, raza);
            MascotaDAO dao = new MascotaDAO();
            dao.actualizarMascota(mascota);

            response.sendRedirect("perfil.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
