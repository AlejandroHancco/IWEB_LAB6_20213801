<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.example.lab6_20213801.beans.Pelicula" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pelicula.titulo}</title>

    <style>
        table {
            width: fit-content;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2>${pelicula.titulo}</h2>

<!-- Lista de Peliculas -->
<table>
    <tr>
        <th>ID</th>
        <td>${pelicula.idPelicula}</td>
    </tr>
    <tr>
        <th>Director</th>
        <td>${pelicula.director}</td>
    </tr>
    <tr>
        <th>Año de Publicación</th>
        <td>${pelicula.anoPublicacion}</td>
    </tr>
    <tr>
        <th>Rating</th>
        <td>${pelicula.rating}</td>
    </tr>
    <tr>
        <th>Box Office</th>
        <td>${pelicula.getBoxOfficeDolar()}</td>
    </tr>
    <tr>
        <th>Género</th>
        <td>${pelicula.getGenero().getNombre()}</td>
    </tr>
    <tr>
        <th>Actores</th>
        <td><a href="actorServlet?idPelicula=${pelicula.idPelicula}">Ver actores</a></td>
    </tr>
</table>

</body>
</html>
