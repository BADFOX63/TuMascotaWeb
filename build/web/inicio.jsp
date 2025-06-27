<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>TuMascota - Inicio</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

  <jsp:include page="header.jsp" />

  <main class="main-content">
    <section class="bienvenida">
      <h2>Bienvenido a TuMascota</h2>
      <p>En TuMascota te ayudamos a gestionar las citas médicas de tu mascota de forma rápida y sencilla.</p>
      <img src="imagenes/Veterinario.png" alt="Veterinario" class="imagen-principal">
    </section>

    <section class="servicios">
      <h3>Servicios principales</h3>
      <ul>
        <li>Agenda tu cita veterinaria fácilmente</li>
        <li>Consulta la disponibilidad del especialista</li>
        <li>Regístrate para tener acceso completo</li>
      </ul>
    </section>
  </main>

  <jsp:include page="footer.jsp" />

</body>
</html>
