package by.it.medved.controllers;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.request.UpdateMovieRequest;
import by.it.medved.dto.response.MovieResponse;
import by.it.medved.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movie")
    public MovieResponse saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return movieService.saveMovie(movieRequest);
    }

    @GetMapping("/movieById/{id}")
    public MovieResponse getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies")
    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }

    @PatchMapping(value = "/movie/{id}")
    public MovieResponse updateMovie(@PathVariable Long id, @Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
        return movieService.updateMovie(id, updateMovieRequest);
    }

    @DeleteMapping(value = "/movie/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}