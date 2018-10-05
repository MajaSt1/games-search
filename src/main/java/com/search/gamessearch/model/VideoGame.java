package com.search.gamessearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// MANY TO MANY
@Entity(name = "Video_Game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VideoGame {

    public VideoGame(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private long id;

    @Column(name = "name", nullable = false)
    @Size(min = 1, message = "name must not be empty")
    private String name;

    @Column(name = "description", nullable = false)
    @Size(min = 1, max = 500)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "game_genre",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "genre_id")}
    )
    private List<Genre> genres = new ArrayList<>();


    public boolean hasGenre(Genre genre){
       for(Genre gameGenre: getGenres()){
           if(gameGenre.getId() == genre.getId()){
               return true;
           }
       }
       return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoGame)) return false;
        VideoGame videoGame = (VideoGame) o;
        return getId() == videoGame.getId() &&
                Objects.equals(getName(), videoGame.getName()) &&
                Objects.equals(getDescription(), videoGame.getDescription()) &&
                Objects.equals(getGenres(), videoGame.getGenres());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getDescription(), getGenres());

    }
}