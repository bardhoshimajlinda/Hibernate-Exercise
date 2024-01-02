package org.examplemovieApp;

import org.examplemovieApp.model.Actor;
import org.examplemovieApp.model.Genre;
import org.examplemovieApp.model.Movie;
import org.examplemovieApp.repository.ActorRepository;
import org.examplemovieApp.repository.GenreRepository;
import org.examplemovieApp.repository.MovieRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.UUID;

public class MovieApp {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        final GenreRepository genreRepository = new GenreRepository(sessionFactory.createEntityManager());
        genreRepository.addGenre(new Genre("Action", null));

        Genre comedyGenre = new  Genre("Comedy", null);
        genreRepository.addGenre(comedyGenre);

        System.out.println(genreRepository.getAll().size());
        System.out.println(genreRepository.getGenresByName("Comedy"));

        UUID genreId = comedyGenre.getId();
        System.out.println("Genre found by id: " +  genreRepository.findGenresById(genreId));

        List<Genre> allGenres = genreRepository.getAll();
        System.out.println("All Genres: " + allGenres);

        genreRepository.deleteGenre(genreId);
        System.out.println("Genre deleted");


        final ActorRepository actorRepository = new ActorRepository(sessionFactory.createEntityManager());
        Actor savedActor = actorRepository.addActor(new Actor("John", "Smith", 1970, null));

        List<Actor> actorsBornAfter1960 = actorRepository.findActorsBornAfterYear(1960);
        System.out.println(actorsBornAfter1960);


        final MovieRepository movieRepository = new MovieRepository(sessionFactory.createEntityManager());
        movieRepository.addMovie(new Movie("Predator", 1980, List.of(savedActor), null));
        System.out.println(movieRepository.getAllMovies());
        System.out.println(movieRepository.recordsWithActors());

        sessionFactory.close();
    }
}