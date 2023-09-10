package by.it.medved.feingClients;

import by.it.medved.dto.response.MovieInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static by.it.medved.util.Message.*;

@Component
@FeignClient(value = "descriptionClient", url = "${clients.description.url}")
public interface DescriptionClient {

    @GetMapping(value = "/{id}", headers = TOKEN)
    MovieInfoResponse getMovieInfo(@PathVariable int id);
}