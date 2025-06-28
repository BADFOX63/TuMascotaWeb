<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario, modelo.Mascota, dao.MascotaDAO, dao.CitaDAO, dao.DisponibilidadDAO, java.util.*" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String fechaSeleccionada = request.getParameter("fecha");

    List<String> horasDisponibles = new ArrayList<>();
    List<String> horasOcupadas = new ArrayList<>();
    List<String> horasNoDisponibles = new ArrayList<>();
    List<String> fechasNoDisponibles = new ArrayList<>();
    boolean diaBloqueado = false;

    CitaDAO citaDAO = new CitaDAO();
    DisponibilidadDAO disponibilidadDAO = new DisponibilidadDAO();

    if (fechaSeleccionada != null && !fechaSeleccionada.isEmpty()) {
        try {
            horasOcupadas = citaDAO.obtenerHorasOcupadas(fechaSeleccionada);
            horasNoDisponibles = disponibilidadDAO.obtenerHorasNoDisponibles(fechaSeleccionada);

            for (int h = 8; h <= 18; h++) {
                String hora = (h < 10 ? "0" + h : "" + h) + ":00:00";
                if (!horasOcupadas.contains(hora) && !horasNoDisponibles.contains(hora)) {
                    horasDisponibles.add(hora);
                }
            }

            diaBloqueado = horasDisponibles.isEmpty(); // No hay horas disponibles
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    try {
        fechasNoDisponibles = disponibilidadDAO.obtenerFechasBloqueadas(2);
    } catch (Exception e) {
        e.printStackTrace();
    }

    MascotaDAO mascotaDAO = new MascotaDAO();
    List<Mascota> mascotas = mascotaDAO.obtenerMascotasPorUsuario(usuario.getId());
%>


<!DOCTYPE html>
<html>
    
<head>
    <meta charset="UTF-8">
    <title>Agendar Cita</title>
    <link rel="stylesheet" href="css/estilos.css">
    <script>
        function mostrarCamposMascota() {
            const seleccion = document.getElementById("id_mascota").value;
            const contenedor = document.getElementById("nuevaMascota");
            contenedor.style.display = (seleccion === "") ? "block" : "none";
        }

        window.onload = mostrarCamposMascota;
    </script>
    <script>
    const fechasBloqueadas = [
        <% for (String f : fechasNoDisponibles) { %>
            "<%= f %>",
        <% } %>
    ];
</script>
</head>
<body>
    <h2>Agendar una nueva cita</h2>

    <form method="post" action="AgendarCitaServlet" class="agendar-form">
        <label>Fecha:</label>
        <input type="date" name="fecha" value="<%= fechaSeleccionada != null ? fechaSeleccionada : "" %>" required onchange="this.form.submit();">
        <br><br>

        <label>Hora:</label>
        <% if (diaBloqueado && fechaSeleccionada != null) { %>
            <p style="color: red; font-weight: bold;">Este día no está disponible para agendar citas.</p>
            <select name="hora" disabled>
                <option value="">No disponible</option>
            </select>
        <% } else { %>
            <select name="hora" required>
                <% for (String hora : horasDisponibles) { %>
                    <option value="<%= hora %>"><%= hora %></option>
                <% } %>
            </select>
        <% } %>
        <br><br>

        <label>Mascota:</label>
        <select name="id_mascota" id="id_mascota" onchange="mostrarCamposMascota()">
            <option value="">Nueva mascota</option>
            <% for (Mascota m : mascotas) { %>
                <option value="<%= m.getId() %>"><%= m.getNombre() %> - <%= m.getEspecie() %> - <%= m.getRaza() %></option>
            <% } %>
        </select>
        <br><br>

        <div id="nuevaMascota" style="display:none;">
            <h3>Registrar nueva mascota</h3>
            <label>Nombre:</label><input type="text" name="nombre_mascota"><br>
            <label>Especie:</label><input type="text" name="especie"><br>
            <label>Raza:</label><input type="text" name="raza"><br>
        </div>

        <br>
        <input type="submit" value="Agendar Cita" <%= diaBloqueado ? "disabled" : "" %>>
        <br><br>
        <a href="inicio.jsp">Volver al inicio</a>
    </form>

</body>
</html>
