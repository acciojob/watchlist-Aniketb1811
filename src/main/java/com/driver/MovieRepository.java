package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> movieMap = new HashMap<>();
    Map<String, Director> directorMap = new HashMap<>();
    Map<String, ArrayList<Movie>> movieDirectorMap = new HashMap<>();

    public String addMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        directorMap.put(director.getName(), director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movie, String director) {
        if(!movieDirectorMap.containsKey(director)){
            movieDirectorMap.put(director, new ArrayList<Movie>());
        }

        movieDirectorMap.get(director).add(getMovieByName(movie));
        return "Movie director pair added successfully";
    }

    public Movie getMovieByName(String name) {
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String name) {
        List<String> movies_list = new ArrayList<>();

        for (Movie movie : movieDirectorMap.get(name)) {
            movies_list.add(movie.getName());
        }
        return movies_list;
    }

    public List<String> findAllMovies() {
//        List<String> list = new ArrayList<>();
//        for(Map.Entry<String, Movie> entry : movieMap.entrySet()){
//            list.add(entry.getKey());
//        }

//        for(Movie movie: movieMap.values()){
//            list.add(movie.getName());
//        }
//        return list;

        return new ArrayList<>(movieMap.keySet());
    }

    public String deleteDirectorByName(String name) throws NullPointerException{

        for (Movie movie : movieDirectorMap.get(name)){
            movieMap.remove(movie.getName());
        }
        movieDirectorMap.remove(name);
        directorMap.remove(name);
        return "Director deleted successfully";
    }

    public String deleteAllDirectors() throws NullPointerException{

        for(String directorName : directorMap.keySet()){
            for (Movie movie : movieDirectorMap.get(directorName)){
                movieMap.remove(movie.getName());
            }
            directorMap.remove(directorName);
        }

        directorMap.clear();
        return "All directors deleted successfully";
    }
}
