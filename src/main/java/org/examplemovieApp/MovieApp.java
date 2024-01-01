package org.examplemovieApp;

import org.examplemovieApp.model.Actor;
import org.examplemovieApp.model.Genre;
import org.examplemovieApp.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MovieApp {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
    }
}