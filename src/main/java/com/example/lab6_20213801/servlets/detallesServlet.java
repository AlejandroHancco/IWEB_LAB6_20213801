package com.example.lab6_20213801.servlets;

import com.example.lab6_20213801.beans.Pelicula;
import com.example.lab6_20213801.daos.PeliculaDao;
import com.example.lab6_20213801.daos.GeneroDao;
import com.example.lab6_20213801.beans.Genero;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "detallesServlet", value = "/detallesServlet")
public class detallesServlet extends HttpServlet {
    private PeliculaDao peliculaDao;
    private GeneroDao generoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        peliculaDao = new PeliculaDao();
        generoDao = new GeneroDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String idPeliculaStr = request.getParameter("id");
        if (idPeliculaStr != null && !idPeliculaStr.isEmpty()) {
            try {
                int idPelicula = Integer.parseInt(idPeliculaStr);
                Pelicula pelicula = peliculaDao.getPeliculaById(idPelicula);

                if (pelicula != null) {
                    Genero genero = generoDao.getGeneroById(pelicula.getIdGenero());
                    pelicula.setGenero(genero); // Configurar el género en la película

                    request.setAttribute("pelicula", pelicula);
                    request.getRequestDispatcher("/Pelicula/viewPelicula.jsp").forward(request, response);
                } else {
                    response.getWriter().println("Pelicula no encontrada");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("ID de película no válido");
            }
        } else {
            response.getWriter().println("ID de película no especificado");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
