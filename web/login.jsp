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
    <link rel="stylesheet" href="estilos.css"> <!-- Asegúrate de que esta ruta sea correcta -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: white;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
            text-align: center;
            width: 300px;
        }

        .login-container h2 {
            margin-bottom: 20px;
            color: #007BFF;
        }

        .login-container input[type="email"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .login-container input[type="submit"] {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-container input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .login-container a {
            display: block;
            margin-top: 15px;
            text-decoration: none;
            color: #555;
            font-size: 14px;
        }

        .mensaje {
            margin-top: 10px;
            color: green;
        }

        .error {
            margin-top: 10px;
            color: red;
        }
    </style>
</head>
<body>
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
