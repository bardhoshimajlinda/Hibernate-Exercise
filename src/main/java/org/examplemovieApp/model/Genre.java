package org.examplemovieApp.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "movies", callSuper = true)
@ToString(exclude = "movies")
public class Genre extends BaseEntity{

    private String name;

    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Movie> movies;
}
