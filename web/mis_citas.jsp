<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario, java.util.List, modelo.Cita" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Cita> citas = (List<Cita>) request.getAttribute("citas");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Citas - TuMascota</title>
    <link rel="stylesheet" href="estilos.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 40px;
            background-color: #f2f2f2;
        }

        table {
            width: 80%;
            margin: auto;
            background-color: white;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        .no-citas {
            text-align: center;
            color: #888;
        }
    </style>
</head>
<body>
    <h2>Mis Citas Agendadas</h2>

    <%
        if (citas == null || citas.isEmpty()) {
    %>
        <p class="no-citas">No tienes citas agendadas.</p>
    <%
        } else {
    %>
        <table>
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>Estado</th>
                    <th>ID Mascota</th>
                </tr>
            </thead>
            <tbody>
                <% for (Cita c : citas) { %>
                    <tr>
                        <td><%= c.getFecha() %></td>
                        <td><%= c.getHora() %></td>
                        <td><%= c.getEstado() %></td>
                        <td><%= c.getIdMascota() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <%
        }
    %>
</body>
</html>
