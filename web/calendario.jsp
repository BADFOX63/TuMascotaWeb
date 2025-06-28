<%@ page import="modelo.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || usuario.getIdRol() != 2) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Calendario</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="calendario">
<jsp:include page="header.jsp" />

<div class="calendario-container">
    <h2>Gestionar Disponibilidad</h2>

    <!-- Formulario para eliminar fecha completa -->
    <form action="ActualizarDisponibilidadServlet" method="post" class="form-calendario">
        <h3>Eliminar día completo</h3>
        <label for="fechaCompleta">Selecciona la fecha:</label>
        <input type="date" id="fechaCompleta" name="fechaCompleta" required>

        <input type="submit" name="accion" value="Eliminar Día Completo" class="boton-eliminar">
    </form>

    <hr style="margin: 40px 0;">

    <!-- Formulario para eliminar solo algunas horas -->
    <form action="ActualizarDisponibilidadServlet" method="post" class="form-calendario">
        <h3>Eliminar horas específicas</h3>
        <label for="fechaHora">Fecha:</label>
        <input type="date" id="fechaHora" name="fechaHora" required>

        <label for="horas">Selecciona las horas a eliminar:</label>
        <div class="horas-grid">
            
            <label><input type="checkbox" name="horas" value="08:00"> 08:00</label>
            <label><input type="checkbox" name="horas" value="09:00"> 09:00</label>
            <label><input type="checkbox" name="horas" value="10:00"> 10:00</label>
            <label><input type="checkbox" name="horas" value="11:00"> 11:00</label>
            <label><input type="checkbox" name="horas" value="12:00"> 12:00</label>
            <label><input type="checkbox" name="horas" value="13:00"> 13:00</label>
            <label><input type="checkbox" name="horas" value="14:00"> 14:00</label>
            <label><input type="checkbox" name="horas" value="15:00"> 15:00</label>
            <label><input type="checkbox" name="horas" value="16:00"> 16:00</label>
            <label><input type="checkbox" name="horas" value="16:00"> 18:00</label>
        </div>

        <input type="submit" name="accion" value="Eliminar Horas Seleccionadas" class="boton-eliminar">
    </form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
