<%@ page import="modelo.Cita, dao.CitaDAO, java.util.*, modelo.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || usuario.getIdRol() != 2) {
        response.sendRedirect("login.jsp");
        return;
    }

    CitaDAO citaDAO = new CitaDAO();
    String filtroCorreo = request.getParameter("filtroCorreo");
    List<Cita> citas;
    if (filtroCorreo != null && !filtroCorreo.trim().isEmpty()) {
        citas = citaDAO.obtenerCitasPorCorreo(filtroCorreo.trim());
} else {
        citas = citaDAO.obtenerTodasLasCitas();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Panel del Veterinario</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="veterinario">
<jsp:include page="header.jsp"/>

<h2>Panel de Gestión de Citas</h2>
<form method="get" action="veterinario.jsp" class="form-filtro">
    <label for="filtroCorreo">Filtrar por correo:</label>
    <input type="text" name="filtroCorreo" id="filtroCorreo" maxlength="100"
           value="<%= request.getParameter("filtroCorreo") != null ? request.getParameter("filtroCorreo") : "" %>">
    <input type="submit" value="Buscar" class="boton-buscar">
    
</form>
    <a href="calendario.jsp" class="boton-calendario">Modificar calendario</a>
<table>
    <tr>
        <th>Fecha</th>
        <th>Hora</th>
        <th>Estado</th>
        <th>Dueño</th>
        <th>Email</th>
        <th>Teléfono</th>
        <th>Mascota</th>
        <th>Raza</th>
        <th>Especie</th>
        <th>Motivo Cancelación</th>
        <th>Acciones</th>
    </tr>
    <% for (Cita c : citas) { %>
        <tr>
            <td><%= c.getFecha() %></td>
            <td><%= c.getHora() %></td>
            <td><%= c.getEstado() %></td>
            <td><%= c.getNombreUsuario() %></td>
            <td><%= c.getEmailUsuario() %></td>
            <td><%= c.getTelefonoUsuario() %></td>
            <td><%= c.getNombreMascota() %></td>
            <td><%= c.getRaza() %></td>
            <td><%= c.getEspecie() %></td>
            <td>
                <% if ("Cancelado".equalsIgnoreCase(c.getEstado())) { %>
                    <%= c.getMotivoCancelacion() != null ? c.getMotivoCancelacion() : "Sin motivo" %>
                <% } else { %>
                    -
                <% } %>
            </td>
            <td>
                <form action="ActualizarCitaServlet" method="post" class="form-action">
                    <input type="hidden" name="id_cita" value="<%= c.getId() %>">

                    <select name="estado" onchange="mostrarMotivo(this, <%= c.getId() %>)">
                        <option <%= c.getEstado().equals("Pendiente") ? "selected" : "" %>>Pendiente</option>
                        <option <%= c.getEstado().equals("Atendido") ? "selected" : "" %>>Atendido</option>
                        <option <%= c.getEstado().equals("Cancelado") ? "selected" : "" %>>Cancelado</option>
                    </select>

                    <div id="motivo_<%= c.getId() %>" style="display: none;">
                        <label>Motivo cancelación:</label><br>
                        <textarea name="motivo" rows="2" cols="30"></textarea>
                    </div>

                    <input type="submit" value="Actualizar" class="boton-actualizar">
                </form>

                <script>
                    function mostrarMotivo(select, id) {
                        var motivoBox = document.getElementById("motivo_" + id);
                        if (select.value === "Cancelado") {
                            motivoBox.style.display = "block";
                        } else {
                            motivoBox.style.display = "none";
                        }
                    }
                </script>
            </td>
        </tr>
    <% } %>
</table>

<jsp:include page="footer.jsp"/>
</body>
</html>
