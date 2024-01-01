package org.examplemovieApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "actors")
@ToString(exclude = "actors")
public class Movie {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    @Column(name = "year_of_release")
    private int yearOfRelease;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();

    @ManyToOne
    private Genre genre;

}
