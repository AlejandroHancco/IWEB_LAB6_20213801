<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab6_20213801.beans.Actor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Actores</title>
    <style>
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
<!-- Esta parte toma el nombre de la Pelicula y lo coloca en el titulo -->

<h2><%= request.getAttribute("nombrePelicula") %></h2>

<table>
    <thead>
    <tr>
        <th>idActor</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Año de Nacimiento</th>
        <th>Ganador Premio Oscar</th>

    </tr>
    </thead>
    <tbody>
    <!-- Crea un ArrayList de actores para que iteren sobre el ID de una Pelicula -->

    <%

        ArrayList<Actor> listaActores = (ArrayList<Actor>) request.getAttribute("listaActores");
        for (int i = 0; i < listaActores.size(); i++) {
            Actor actor = listaActores.get(i);
    %>
    <tr>
        <td><%= actor.getIdActor() %></td>
        <td><%= actor.getNombre() %></td>
        <td><%= actor.getApellido() %></td>
        <td><%= actor.getAnoNacimiento() %></td>
        <td><%= actor.getPremioOscar()%></td>


    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
