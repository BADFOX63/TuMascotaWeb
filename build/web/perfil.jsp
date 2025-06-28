<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario, dao.MascotaDAO, modelo.Mascota, dao.CitaDAO, modelo.Cita, java.util.*" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    MascotaDAO mascotaDAO = new MascotaDAO();
    List<Mascota> mascotas = mascotaDAO.obtenerMascotasPorUsuario(usuario.getId());

    CitaDAO citaDAO = new CitaDAO();
    List<Cita> citas = citaDAO.obtenerCitasPorUsuario(usuario.getEmail());
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil - TuMascota</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="perfil">

<jsp:include page="header.jsp" />

<main class="perfil-container">
    <h2>Bienvenido, <%= usuario.getNombre() %></h2>

    <!-- DATOS PERSONALES -->
    <section class="perfil-datos">
        <h3>Datos personales</h3>
        <form method="post" action="ActualizarPerfilServlet">
            <label>Nombre:</label><br>
            <input type="text" name="nombre" value="<%= usuario.getNombre() %>" required><br><br>

            <label>Email:</label><br>
            <input type="email" name="email" value="<%= usuario.getEmail() %>" required><br><br>

            <label>Teléfono:</label><br>
            <input type="text" name="telefono" value="<%= usuario.getTelefono() %>" required><br><br>

            <label>Contraseña:</label><br>
            <input type="password" name="password" value="<%= usuario.getContraseña() %>" required><br><br>

            <input type="submit" value="Guardar cambios">
        </form>
    </section>

    <!-- MASCOTAS -->
    <section class="perfil-mascotas">
    <h3>Mis Mascotas</h3>

    <form method="get" action="perfil.jsp">
        <label>Selecciona una mascota para editar:</label>
        <select name="id_mascota" required>
            <option value="">-- Selecciona una mascota --</option>
            <% for (Mascota m : mascotas) { %>
                <option value="<%= m.getId() %>">
                    <%= m.getNombre() %> - <%= m.getEspecie() %> - <%= m.getRaza() %>
                </option>
            <% } %>
        </select>
        <input type="submit" value="Editar">
    </form>
    <hr>

<%
    String idMascotaStr = request.getParameter("id_mascota");
    if (idMascotaStr != null && !idMascotaStr.isEmpty()) {
        int idMascota = Integer.parseInt(idMascotaStr);
        for (Mascota m : mascotas) {
            if (m.getId() == idMascota) {
%>
        <!-- Formulario de edición para la mascota seleccionada -->
        <form method="post" action="ActualizarMascotaServlet" class="form-mascota">
            <input type="hidden" name="id_mascota" value="<%= m.getId() %>">

            <label>Nombre:</label><br>
            <input type="text" name="nombre" value="<%= m.getNombre() %>" required><br>

            <label>Especie:</label><br>
            <input type="text" name="especie" value="<%= m.getEspecie() %>" required><br>

            <label>Raza:</label><br>
            <input type="text" name="raza" value="<%= m.getRaza() %>" required><br><br>

            <input type="submit" value="Actualizar Mascota">
        </form>
<%
            }
        }
    }
%>
</section>

    <!-- CITAS -->
    <section class="perfil-citas">
        <h3>Citas Agendadas</h3>
        <ul>
            <% for (Cita c : citas) { %>
                <li><%= c.getFecha() %> a las <%= c.getHora() %> - Estado: <%= c.getEstado() %></li>
            <% } %>
        </ul>
    </section>
</main>

<jsp:include page="footer.jsp" />
</body>
</html>
