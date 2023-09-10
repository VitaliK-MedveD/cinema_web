package by.it.medved.services.impl;

import by.it.medved.dto.response.MovieInfoResponse;
import by.it.medved.feingClients.DescriptionClient;
import by.it.medved.services.DescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionClient descriptionClient;

    @Override
    @Transactional(readOnly = true)
    public MovieInfoResponse getMovieInfo(int id) {
        return descriptionClient.getMovieInfo(id);
    }
}