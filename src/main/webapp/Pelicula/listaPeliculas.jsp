<%@ page import="com.example.lab6_20213801.beans.Pelicula" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Pelicula> lista = (ArrayList<Pelicula>) request.getAttribute("lista");
    String offset = (String) request.getParameter("offset");
    String limit = (String) request.getParameter("limit");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Películas</title>

    <style>

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Lista de Películas</h1>

<!-- Buscador de películas -->
<form action="PeliculaServlet" method="POST">
    <label for="textoBuscar"></label>
    <input type="text" id="textoBuscar" name="textoBuscar" placeholder="Buscar película..." value="${busqueda}">
    <input type="hidden" name="action" value="s">
    <button type="submit">Buscar</button>
</form>

<!-- Lista completa de películas -->
<table>
    <thead>
    <tr>
        <th>Título</th>
        <th>Director</th>
        <th>Año de Publicación</th>
        <th>Rating</th>
        <th>Box Office</th>
        <th>Género</th>
        <th>Actores</th>
    </tr>
    </thead>
    <tbody>
    <!-- Iterador sobre la lista de películas -->
    <% for (Pelicula pelicula : lista) { %>
    <tr>
        <!-- Crea un link con el parametro IdPelicula para que el detallesServlet analice de que Pelicula se trata -->
        <td><a href="detallesServlet?id=<%= pelicula.getIdPelicula() %>"><%= pelicula.getTitulo() %></a></td>
        <td><%= pelicula.getDirector() %></td>
        <td><%= pelicula.getAnoPublicacion() %></td>
        <td><%= pelicula.getRating() %></td>
        <td><%= pelicula.getBoxOfficeDolar() %></td>
        <td><%= pelicula.getGenero().getNombre() %></td>
        <!-- Crea un link con el parametro IdPelicula para que el actorServlet analice de que Pelicula se trata -->
        <td><a href="actorServlet?idPelicula=<%= pelicula.getIdPelicula() %>">Ver actores</a></td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
