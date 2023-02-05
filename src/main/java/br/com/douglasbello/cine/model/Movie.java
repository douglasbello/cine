package br.com.douglasbello.cine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity(name = "tb_movie")
public class Movie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title can't be null.")
    private String title;

    @ManyToMany
    @JoinTable(name = "tb_movies_genres", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<User> users = new HashSet<>();

    @NotBlank(message = "Title can't be null.")
    private String overview;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotBlank(message = "The release date can't be null.")
    private Date releaseDate;

    @NotBlank
    private String originalLanguague;

    public Movie() {
    }

    public Movie(Long id, String title, String overview, Date releaseDate, String originalLanguague) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalLanguague = originalLanguague;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalLanguague() {
        return originalLanguague;
    }

    public void setOriginalLanguague(String originalLanguague) {
        this.originalLanguague = originalLanguague;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
