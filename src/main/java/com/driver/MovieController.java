package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie") //1
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add-director") //2
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        String response = movieService.addDirector(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair") //3
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        String response = movieService.addMovieDirectorPair(movie, director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}") //4
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie movie = movieService.getMovie(name);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}") //5
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}") //6
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String name){
        List<String> list = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies") //7
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> list = movieService.findAllMovies();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name") //8
    public  ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String name){
        String response = movieService.deleteDirectorByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String response = movieService.deleteAllDirectors();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
