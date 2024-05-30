package com.example.lab6_20213801.servlets;

import com.example.lab6_20213801.beans.Actor;
import com.example.lab6_20213801.beans.Pelicula;
import com.example.lab6_20213801.daos.ActorDao;
import com.example.lab6_20213801.daos.PeliculaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "actorServlet", value = "/actorServlet")
public class actorServlet extends HttpServlet {
    private ActorDao actorDao;
    private PeliculaDao peliculaDao;

    @Override
    public void init() throws ServletException {
        super.init();
        actorDao = new ActorDao();
        peliculaDao = new PeliculaDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Obtener el idPelicula desde el request
        String idPeliculaStr = request.getParameter("idPelicula");

        if (idPeliculaStr != null && !idPeliculaStr.isEmpty()) {
            try {
                int idPelicula = Integer.parseInt(idPeliculaStr);

                // Obtener la película por su ID
                Pelicula pelicula = peliculaDao.getPeliculaById(idPelicula);
                if (pelicula != null) {
                    // Obtener la lista de actores para la película a traves del ActorDAO
                    ArrayList<Actor> listaActores = actorDao.getActoresByIdPelicula(idPelicula);

                    // Setear la lista de actores y el nombre de la película como atributos en el request para usarlos en listaActores.jsp
                    request.setAttribute("listaActores", listaActores);
                    request.setAttribute("nombrePelicula", pelicula.getTitulo());


                    request.getRequestDispatcher("/Pelicula/listaActores.jsp").forward(request, response);
                } else {
                    //Error por si no hay ninguna pelicula en la base de datos
                    response.getWriter().println("Película no encontrada");
                }
            } catch (NumberFormatException e) {
                //Error por si el ID es incorrecto
                response.getWriter().println("ID de película no válido");
            }
        } else {
            //Error por si no hay IDs
            response.getWriter().println("ID de película no especificado");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
