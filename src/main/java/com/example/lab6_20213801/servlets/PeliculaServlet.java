package com.example.lab6_20213801.servlets;
import com.example.lab6_20213801.beans.Pelicula;
import com.example.lab6_20213801.daos.PeliculaDao;
import com.example.lab6_20213801.daos.GeneroDao;
import com.example.lab6_20213801.beans.Genero;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PeliculaServlet", value = "/PeliculaServlet")
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();
        GeneroDao generoDao = new GeneroDao();

        if (action.equals("lista")) {
            int limit = 100;
            int offset = 0;

            String limitParam = request.getParameter("limit");
            String offsetParam = request.getParameter("offset");

            if (limitParam != null && !limitParam.isEmpty()) {
                limit = Integer.parseInt(limitParam);
            }

            if (offsetParam != null && !offsetParam.isEmpty()) {
                offset = Integer.parseInt(offsetParam);
            }

            request.setAttribute("limit", limit);
            request.setAttribute("offset", offset);

            ArrayList<Pelicula> list = peliculaDao.list(limit, offset);

            // Iterar sobre la lista y obtener el género para cada película
            for (Pelicula pelicula : list) {
                Genero genero = generoDao.getGeneroById(pelicula.getIdGenero());
                pelicula.setGenero(genero);
            }

            request.setAttribute("lista", list);
            RequestDispatcher rd = request.getRequestDispatcher("/Pelicula/listaPeliculas.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();
        GeneroDao generoDao = new GeneroDao();

        if (action.equals("s")) {
            String textoBuscar = request.getParameter("textoBuscar");
            int limit = 100;
            int offset = 0;

            String limitParam = request.getParameter("limit");
            String offsetParam = request.getParameter("offset");

            if (limitParam != null && !limitParam.isEmpty()) {
                limit = Integer.parseInt(limitParam);
            }

            if (offsetParam != null && !offsetParam.isEmpty()) {
                offset = Integer.parseInt(offsetParam);
            }

            request.setAttribute("limit", limit);
            request.setAttribute("offset", offset);

            ArrayList<Pelicula> list = peliculaDao.searchByTitulo(textoBuscar, limit, offset);

            // Iterar sobre la lista y obtener el género para cada película
            for (Pelicula pelicula : list) {
                Genero genero = generoDao.getGeneroById(pelicula.getIdGenero());
                pelicula.setGenero(genero);
            }

            request.setAttribute("lista", list);
            request.setAttribute("busqueda", textoBuscar);
            RequestDispatcher rd = request.getRequestDispatcher("/Pelicula/listaPeliculas.jsp");
            rd.forward(request, response);
        }
    }
}
