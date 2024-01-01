package org.examplemovieApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "movies")
@ToString(exclude = "movies")
public class Genre {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Movie> movies;
}
