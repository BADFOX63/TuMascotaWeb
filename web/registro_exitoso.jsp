<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registro exitoso</title>
</head>
<body>
  <h1>¡Registro completado exitosamente!</h1>
  <p>Bienvenido, <%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "usuario" %> 👋</p>
  <a href="login.jsp">Iniciar sesión</a>
</body>
</html>
