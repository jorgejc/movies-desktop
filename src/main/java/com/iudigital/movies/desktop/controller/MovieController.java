package com.iudigital.movies.desktop.controller;

import com.iudigital.movies.desktop.dao.MovieDao;
import com.iudigital.movies.desktop.domain.Movie;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MovieController {

    private MovieDao movieDao;
    
    public MovieController(){
        movieDao = new MovieDao();
    }
    
    public List<Movie> getMovies() throws SQLException{
       return movieDao.getMovies();
    }
    
    public void create(Movie movie) throws SQLException{
        movie.setFechaCreacion(LocalDateTime.now());
        movieDao.create(movie);
    }
    
    public Movie getMovie(int id) throws SQLException{
        return movieDao.getMovie(id);
    }
    
    public void updateMovie(int id, Movie movie) throws SQLException{
        movie.setFechaCreacion(LocalDateTime.now());
        movieDao.updateMovie(id, movie);
    }
    
    public void deleteMovie(int id) throws SQLException{
        movieDao.deleteMovie(id);
    }
}
