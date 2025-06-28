<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String mensaje = request.getParameter("mensaje");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <% if ("sesion_cerrada".equals(mensaje)) { %>
    <div class="mensaje">Has cerrado sesión exitosamente.</div>
<% } %>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - TuMascota</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="login">
<div class="login-container">
    <h2>Iniciar Sesión</h2>

    <% if (mensaje != null && mensaje.contains("incorrectos")) { %>
        <div class="error">Correo o contraseña incorrectos.</div>
    <% } %>

    <% if (usuario != null) { %>
        <p>Bienvenido, <strong><%= usuario.getNombre() %></strong></p>
        <a href="CerrarSesionServlet">Cerrar sesión</a>
    <% } else { %>
        <form action="LoginServlet" method="post">
            <input type="email" name="email" placeholder="Correo electrónico" required>
            <input type="password" name="password" placeholder="Contraseña" required>
            <input type="submit" value="Ingresar">
        </form>
        <a href="registro.html">¿No tienes cuenta? Regístrate</a>
    <% } %>
</div>

</body>
</html>
