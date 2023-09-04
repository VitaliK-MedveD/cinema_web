package by.it.medved.services;

import by.it.medved.dto.response.DescriptionResponse;

public interface DescriptionService {

    DescriptionResponse getDescription(String fileName);
}