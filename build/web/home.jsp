<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="modelo.Usuario" %>
<%@ page session="true" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("error_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #F8F8F8;
        }
        .mensaje {
            background-color: #D4EDDA;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            border: 1px solid #C3E6CB;
            width: fit-content;
            margin: 50px auto;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="mensaje">
    ✅ Inicio de sesión exitoso.<br>
    ¡Bienvenido, <strong><%= usuario.getNombre() %></strong>!
</div>

</body>
</html>
