package by.it.medved.controllers;

import by.it.medved.dto.request.MovieRequest;
import by.it.medved.dto.request.UpdateMovieRequest;
import by.it.medved.dto.response.MovieResponse;
import by.it.medved.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Movie Controller", description = "Responsible for handling queries and managing movie-related activities")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movie")
    @Operation(description = "Saving movie to the database")
    public MovieResponse saveMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return movieService.saveMovie(movieRequest);
    }

    @GetMapping("/movieById/{id}")
    @Operation(description = "Getting movie by ID")
    public MovieResponse getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies")
    @Operation(description = "Getting all movies")
    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }

    @PatchMapping(value = "/movie/{id}")
    @Operation(description = "Update movie")
    public MovieResponse updateMovie(@PathVariable Long id, @Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
        return movieService.updateMovie(id, updateMovieRequest);
    }

    @DeleteMapping(value = "/movie/{id}")
    @Operation(description = "Delete movie by ID")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}