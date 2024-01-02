package org.examplemovieApp.repository;
import org.examplemovieApp.model.Movie;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/*Implement the MovieRepository class which will be responsible for:
 - adding Movie records to the database
 - removing Movie records from the database
 - searching records by title
 - searching for records by identifier
 - returning all records
 - retrieving all records with actors appearing in it (do left join fetch in the query)*/
public class MovieRepository {
    private final EntityManager entityManager;
    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addMovie(Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    public void deleteMovie(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Movie movie = entityManager.find(Movie.class,id);
            if (movie != null) {
                entityManager.remove(movie);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    public List<Movie> findByTitle(String title) {
        return entityManager.createQuery("SELECT m FROM movies m WHERE m.title = :title", Movie.class)
                .setParameter("title", title)
                .getResultList();
    }
    public Optional<Movie> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(Movie.class,id));
    }

    public List<Movie> getAllMovies() {
        return entityManager.createQuery("FROM movies", Movie.class).getResultList();
    }

    public List<Movie> recordsWithActors() {
        return entityManager.createQuery("SELECT m FROM movies m LEFT JOIN fetch m.actors ", Movie.class)
                .getResultList();
    }
}
