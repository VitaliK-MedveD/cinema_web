package by.it.medved.dto.response;

import by.it.medved.errors.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
public class ErrorResponse {

    @JsonInclude(NON_NULL)
    private List<Error> errors;
    @JsonInclude(NON_NULL)
    private Integer errorsCount;
    @JsonInclude(NON_NULL)
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime time;
}