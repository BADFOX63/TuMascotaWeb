<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    modelo.Usuario usuario = (modelo.Usuario) session.getAttribute("usuario");
%>
<header class="header">
  <div class="logo-nav-container">
      <img src="imagenes/Logo.png" alt="Logo TuMascota" class="logo">
  </div>
  <h1>TuMascota</h1>
  <nav class="nav">
    <ul>
      <li><a href="inicio.jsp">Inicio</a></li>
      <li><a href="perfil.jsp">Mi perfil</a></li>
      <li><a href="agendar_cita.jsp">Agendar Cita</a></li>
      <% if (usuario != null) { %>
          <li><a href="CerrarSesionServlet">Cerrar sesión</a></li>
      <% } else { %>
          <li><a href="login.jsp">Iniciar Sesión</a></li>
          <li><a href="registro.jsp">Registro</a></li>
      <% } %>
    </ul>
  </nav>
</header>
