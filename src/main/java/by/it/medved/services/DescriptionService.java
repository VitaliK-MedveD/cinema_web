package by.it.medved.services;

import by.it.medved.dto.DescriptionResponse;

public interface DescriptionService {

    DescriptionResponse getDescription(String fileName);
}