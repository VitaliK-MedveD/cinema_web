package by.it.medved.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieInfoResponse {

    @JsonProperty(value = "name")
    private String movieTitle;
    @JsonProperty(value = "year")
    private Integer year;
    @JsonInclude(NON_NULL)
    @JsonProperty(value = "shortDescription")
    private String shortDescription;
    @JsonInclude(NON_NULL)
    @JsonProperty(value = "description")
    private String description;
}