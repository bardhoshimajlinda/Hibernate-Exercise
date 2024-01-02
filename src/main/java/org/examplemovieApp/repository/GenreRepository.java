package org.examplemovieApp.repository;
import org.examplemovieApp.model.Genre;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GenreRepository {

    private final EntityManager entityManager;
    public GenreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //adding Genre records to the database
    public void addGenre(Genre genre) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(genre);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //deleting records of type Genre from the database
    public void deleteGenre(UUID genreId) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Genre genre = entityManager.find(Genre.class, genreId);
            if (genre != null) {
                entityManager.remove(genre);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    //searching records by name
    public List<Genre> getGenresByName(String name) {
       return entityManager.createQuery("SELECT g FROM genres g WHERE g.name = :name", Genre.class)
               .setParameter("name", name)
               .getResultList();
    }

    //searching for records by identifier
    public Optional<Genre> findGenresById(final UUID id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    //returning all records
    public List<Genre> getAll() {
        return entityManager.createQuery("SELECT g FROM genres g", Genre.class).getResultList();
    }
}
