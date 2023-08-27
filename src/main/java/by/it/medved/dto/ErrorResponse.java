package by.it.medved.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private List<String> errorMessages;
    private int errorCount;
    private HttpStatus httpStatus;
    private LocalDateTime time;
}