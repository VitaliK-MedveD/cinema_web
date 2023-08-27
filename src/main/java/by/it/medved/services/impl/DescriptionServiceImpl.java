package by.it.medved.services.impl;

import by.it.medved.dto.DescriptionResponse;
import by.it.medved.feingClients.DescriptionClient;
import by.it.medved.services.DescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionClient descriptionClient;

    @Override
    public DescriptionResponse getDescription(String fileName) {
        String description = descriptionClient.getDescription(fileName);
        return DescriptionResponse.builder()
                .description(description)
                .build();
    }
}
