package com.search.gamessearch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Genre")
@NoArgsConstructor
@Getter
@Setter
public class Genre {
    public Genre(String name){this.name=name;}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private long id;

    @Column(name = "genre")
    @Size(min = 1, message = "name must not be empty")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "genres")
    private List<VideoGame> games=new ArrayList<>();

    public void addGames(VideoGame item){ games.add(item);}

    @JsonIgnore
    public List<VideoGame> getGames(){return games;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return getId() == genre.getId() &&
                Objects.equals(getName(), genre.getName()) &&
                Objects.equals(getGames(), genre.getGames());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getGames());
    }
}


