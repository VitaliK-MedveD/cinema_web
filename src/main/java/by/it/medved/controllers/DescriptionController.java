package by.it.medved.controllers;

import by.it.medved.dto.response.MovieInfoResponse;
import by.it.medved.services.DescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class DescriptionController {

    private final DescriptionService descriptionService;

    @GetMapping("/movieInfo/{id}")
    public MovieInfoResponse getMovieInfo(@PathVariable int id) {
        return descriptionService.getMovieInfo(id);
    }
}