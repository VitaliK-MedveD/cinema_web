package by.it.medved.controllers;

import by.it.medved.dto.response.MovieInfoResponse;
import by.it.medved.services.DescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Description Controller",
        description = "Responsible for handling queries and managing actions related to description or descriptive information")
public class DescriptionController {

    private final DescriptionService descriptionService;

    @GetMapping("/movieInfo/{id}")
    @Operation(description = "Getting the description of the film from the site \"kinopoisk.ru\" by ID")
    public MovieInfoResponse getMovieInfo(@PathVariable int id) {
        return descriptionService.getMovieInfo(id);
    }
}