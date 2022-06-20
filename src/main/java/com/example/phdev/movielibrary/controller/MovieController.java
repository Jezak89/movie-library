package com.example.phdev.movielibrary.controller;

import com.example.phdev.movielibrary.Movie;
import com.example.phdev.movielibrary.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") Long id) {
        return movieRepository.getById(id);
    }

    @PostMapping("/")
    public int addMovie(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);

        if (movie != null) {
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int pertiallylUpdate(@PathVariable("id") Long id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);

        if (movie != null) {
            if (updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id) {
        return movieRepository.delete(id);
    }


}
