package by.it.medved.services;

import by.it.medved.dto.response.MovieInfoResponse;

public interface DescriptionService {

    MovieInfoResponse getMovieInfo(int id);
}