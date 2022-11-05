package com.iudigital.movies.desktop.view;

import com.iudigital.movies.desktop.controller.MovieController;
import com.iudigital.movies.desktop.domain.Movie;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void getMovies(MovieController movieController) {
        try {
            List<Movie> movies = movieController.getMovies();
            if (movies.isEmpty()) {
                System.out.println("No hay datos ");
            } else {
                movies.forEach(movie -> {
                    System.out.println("id: " + movie.getId());
                    System.out.println("Titulo: " + movie.getTitulo());
                    System.out.println("Año: " + movie.getAnio());
                    System.out.println("Actor: " + movie.getActor());
                    System.out.println("Género Id: " + movie.getGenre());
                    System.out.println("Nombre Género: " + movie.getGenreName());
                    System.out.println("Fecha Creación: " + movie.getFechaCreacion());

                    System.out.println("---------------------------------");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void create(MovieController movieController) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Digite título: ");
            String titulo = sc.nextLine();
            System.out.println("Título: " + titulo);
            System.out.println("----------------------- ");

            System.out.println("Digite año de estreno: ");
            String anio = sc.nextLine();
            System.out.println("el modelo es: " + anio);
            System.out.println("------------------------ ");

            System.out.println("Digite nombre de Actor principal: ");
            String actor = sc.nextLine();
            System.out.println("Actor principal: " + actor);
            System.out.println("------------------------- ");

            System.out.println("Digite el id del genero: ");
            String genre = sc.nextLine();
            System.out.println("genero: " + genre);
            System.out.println("-------------------------- ");


            Movie movie = new Movie();
            movie.setTitulo(titulo);
            movie.setAnio(anio);
            movie.setActor(actor);
            movie.setGenre(genre);
            movieController.create(movie);
            System.out.println("Se ha creado una nueva pelicula: ");
            
            getMovies(movieController);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void getMovie(MovieController movieController) {

        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Digite el id: ");
            int id = sc.nextInt();
            System.out.println("id: " + id);
            System.out.println("-------------------------- ");
            
            Movie movie = movieController.getMovie(id);
            System.out.println("movie = " + movie);
            System.out.println("-------------------------------------------- ");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void editMovie(MovieController movieController){
        
        try {
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Digite ID de la película a actualizar");
            int id = sc.nextInt();
            System.out.println("el ID es: " + id);
            System.out.println("--------------------------------------- ");
            
            Movie movieExits = movieController.getMovie(id);
            if(movieExits == null){
                System.out.println("No existe película");
                return;
            }
            
            Scanner tl = new Scanner(System.in);
            System.out.println("Digite el título: ");
            String titulo = tl.nextLine();
            System.out.println("el título de la película es: " + titulo);
            System.out.println("--------------------------------------- ");
            
            Scanner an = new Scanner(System.in);
            System.out.println("Digite año de estreno: ");
            String anio = an.nextLine();
            System.out.println("El año de estreno es: " + anio);
            System.out.println("--------------------------------------- ");
            
            Scanner ac = new Scanner(System.in);
            System.out.println("Digite el nombre del actor principal");
            String actor = ac.nextLine();
            System.out.println("El actor principal es: " + actor);
            System.out.println("--------------------------------------- ");
            
            Scanner gn = new Scanner(System.in);
            System.out.println("Digite el género de la película");
            String genre = gn.nextLine();
            System.out.println("El género de la película es: " + genre);
            System.out.println("--------------------------------------- ");
            
            Movie movie = new Movie();
            movie.setTitulo(titulo);
            movie.setAnio(anio);
            movie.setActor(actor);
            movie.setGenre(genre);
            movieController.updateMovie(id, movie);
            System.out.println("Se actualizó correctamente la película");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void deleteMovie(MovieController movieController){
            try{
                Scanner sc = new Scanner(System.in);
                
                System.out.println("Digite el ID de la película a eliminar: ");
                int id = sc.nextInt();
                System.out.println("el id de la película es: " + id);
                Movie movieExists = movieController.getMovie(id);
                if(movieExists == null){
                    System.out.println("No existe carro ");
                    return;
                }
                
                movieController.deleteMovie(id);
                System.out.println("Película eliminada con exito ");
                System.out.println("-----------------------------------------");
                getMovies(movieController);
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    
    public static void main(String[] args) {
        var opcion = -1;
        var scanner = new Scanner(System.in);
        MovieController movieController = new MovieController();
        while(opcion != 0){
            System.out.println("----------------------------------");
            System.out.println("ELIGE UNA OPCIÓN");
            System.out.println("----------------------------------");
            
            System.out.println("1. Listar Películas "  );        
            System.out.println("2. Agregar Película");
            System.out.println("3. Listar por Id");
            System.out.println("4. Editar Película");
            System.out.println("5. Eliminar Película");
            
            System.out.println("----------------------------------");
            
            opcion = Integer.parseInt(scanner.nextLine());
            switch(opcion){
                case 0:
                    System.out.println("Ha salido de la aplicación ");
                    break;
                case 1:
                    getMovies(movieController);
                    break;
                case 2:
                    create(movieController);
                    break;
                case 3:
                    getMovie(movieController);
                    break;
                case 4:
                    editMovie(movieController);
                    break;
                case 5:
                    deleteMovie(movieController);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
