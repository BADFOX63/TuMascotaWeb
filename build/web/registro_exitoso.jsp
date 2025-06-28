<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="modelo.Usuario" %>
<%
    String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "usuario";
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registro Exitoso - TuMascota</title>
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="registro-exitoso">
  <main class="main-content" style="text-align: center; padding: 40px;">
    <h1>¡Registro exitoso!</h1>
    <p>Bienvenido, <strong><%= nombre %></strong> </p>
    <a href="login.jsp" class="boton-principal">Ir a iniciar sesión</a>
  </main>
</body>
</html>
