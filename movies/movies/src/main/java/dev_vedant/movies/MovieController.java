package dev_vedant.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity <List<Movie>>( movieService.allMovies(),HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId) {
    Optional<Movie> movie = movieService.singleMovie(imdbId);
    if (movie.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(movie);
    }
}

}