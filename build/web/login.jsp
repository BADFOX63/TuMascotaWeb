<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - TuMascota</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="login">
<div class="login-container">
    <h2>Iniciar Sesión</h2>

    <% if (mensaje != null) { %>
        <div class="<%= mensaje.contains("incorrectos") ? "error" : "mensaje" %>">
            <%= mensaje %>
        </div>
    <% } %>

    <% if (usuario != null) { %>
        <p>Bienvenido, <strong><%= usuario.getNombre() %></strong> </p>
        <a href="logout.jsp">Cerrar sesión</a>
    <% } else { %>
        <form action="LoginServlet" method="post">
        <input type="email" name="email" required>
        <input type="password" name="password" required>
        <input type="submit" value="Ingresar">
        </form>
        <a href="registro.jsp">¿No tienes cuenta? Regístrate</a>
    <% } %>
</div>
</body>
</html>
