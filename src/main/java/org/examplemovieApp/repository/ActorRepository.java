package org.examplemovieApp.repository;
import org.examplemovieApp.model.Actor;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*Implement the ActorRepository class which will be able to:
- saving objects of type Actor to the database
- look for objects in the database of type Actor by identifier
- search for objects in the Actor type database that were born after a certain year (i.e. the year is a method parameter)
- look for objects in the database of the Actor type, the names of which end with the specified value of theString type object*/
public class ActorRepository {
    private final EntityManager entityManager;
    public ActorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Actor save(Actor actor) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            entityManager.persist(actor);
            transaction.commit();
            return actor;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public Optional<Actor> findById(UUID uuid) {
        return Optional.ofNullable(entityManager.find(Actor.class, uuid));
    }

    public List<Actor> findActorsBornAfterYear(int birthYear) {
        return entityManager.createQuery("SELECT a FROM actors a WHERE yearOfBirth > :yearOfBirth",Actor.class)
                .setParameter("yearOfBirth", birthYear)
                .getResultList();
    }

    public List<Actor> findActorsByNameEndsWith(String suffix) {
        return entityManager.createQuery("SELECT a FROM actors a WHERE a.lastName ilike :lastName", Actor.class)
                .setParameter("lastName", "%" + suffix)
                .getResultList();
    }
}
