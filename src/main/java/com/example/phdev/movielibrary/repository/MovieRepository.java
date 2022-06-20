package com.example.phdev.movielibrary.repository;

import com.example.phdev.movielibrary.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll() {
        List<Movie> query = jdbcTemplate.query("SELECT id, name, rating FROM movie",
                BeanPropertyRowMapper.newInstance(Movie.class));
        return query;
    }

    public Movie getById(Long id) {
        Movie query = jdbcTemplate.queryForObject("SELECT id, name, rating FROM movie WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Movie.class), id);
        return query;
    }

    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate
                .update("INSERT INTO movie(name, rating) VALUES(?, ?)",
                        movie.getName(), movie.getRating()
                ));
        return 1;
    }

    public int update(Movie movie) {
        return jdbcTemplate.update("UPDATE movie SET name=?, rating=? WHERE id=?",
                movie.getName(), movie.getRating(), movie.getId());
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM movie WHERE id = ?", id);
    }

}
