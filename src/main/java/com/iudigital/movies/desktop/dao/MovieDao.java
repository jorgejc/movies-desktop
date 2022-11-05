package com.iudigital.movies.desktop.dao;

import com.iudigital.movies.desktop.domain.Movie;
import com.iudigital.movies.desktop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    private static final String GET_MOVIES = "select movie.*, genre.name "
            + "from movie "
            + "inner join genre on movie.genre_id = genre.genre_id";
    private static final String CREATE_MOVIES = "insert into movie(titulo, anio, actor,"
            + "genre_id, fecha_creacion) values(?, ?, ?, ?, ?)";
    private static final String GET_MOVIE_BY_ID = "select movie.*, genre.name "
            + "from movie "
            + "inner join genre on movie.genre_id = genre.genre_id "
            + "where movie.movie_id = ?";
    private static final String UPDATE_MOVIE = "update movie set titulo = ? , anio = ?, "
            + "actor = ?, genre_id  = ?, fecha_creacion = ? where movie_id = ?";

    private static final String DELETE_MOVIE = "delete from movie where movie_id = ?";

    public List<Movie> getMovies() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Movie> movies = new ArrayList<>();
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_MOVIES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("movie_id"));
                movie.setTitulo(resultSet.getString("titulo"));
                movie.setAnio(resultSet.getString("anio"));
                movie.setActor(resultSet.getString("actor"));
                movie.setGenre(resultSet.getString("genre_id"));
                movie.setGenreName(resultSet.getString("genre.name"));
                movie.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                movies.add(movie);
            }
            return movies;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
    
    public void create(Movie movie) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREATE_MOVIES);
            preparedStatement.setString(1, movie.getTitulo());
            preparedStatement.setString(2, movie.getAnio());
            preparedStatement.setString(3, movie.getActor());
 //           preparedStatement.setInt(4, movie.getGenre().getId());
            preparedStatement.setString(4, movie.getGenre());
            preparedStatement.setObject(5, LocalDateTime.now());
            preparedStatement.executeUpdate();

        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    public Movie getMovie(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Movie movie = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_MOVIE_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                
                movie = new Movie();
                movie.setId(resultSet.getInt("movie_id"));
                movie.setTitulo(resultSet.getString("titulo"));
                movie.setAnio(resultSet.getString("anio"));
                movie.setActor(resultSet.getString("actor"));
                movie.setGenre(resultSet.getString("genre_id"));
                movie.setGenreName(resultSet.getString("genre.name"));
                movie.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
            }
            return movie;
        } finally {

            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateMovie(int id, Movie movie) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_MOVIE);
            preparedStatement.setString(1, movie.getTitulo());
            preparedStatement.setString(2, movie.getAnio());
            preparedStatement.setString(3, movie.getActor());
            preparedStatement.setString(4, movie.getGenre());
            preparedStatement.setObject(5, LocalDateTime.now());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                preparedStatement.close();
            }
        }
    }

    public void deleteMovie(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_MOVIE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
