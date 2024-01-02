package org.examplemovieApp;

import org.examplemovieApp.model.Actor;
import org.examplemovieApp.model.Genre;
import org.examplemovieApp.model.Movie;
import org.examplemovieApp.repository.GenreRepository;
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

        sessionFactory.close();
    }
}