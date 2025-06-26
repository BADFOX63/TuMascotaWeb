<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error de Registro</title>
</head>
<body>
    <h1>❌ Registro fallido</h1>
    <h3>${mensaje}</h3>
    <p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "Ocurrió un error inesperado." %></p>
    <a href="registro.html">Volver a intentarlo</a>
</body>
</html>
