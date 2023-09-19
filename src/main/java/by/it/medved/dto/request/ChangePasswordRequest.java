package by.it.medved.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static by.it.medved.util.Message.*;
import static by.it.medved.util.Patterns.*;

@Data
@Builder
public class ChangePasswordRequest {

    @Schema(example = EXAMPLE_PASSWORD)
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String currentPassword;
    @Schema(example = EXAMPLE_NEW_PASSWORD)
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String newPassword;
    @Schema(example = EXAMPLE_NEW_PASSWORD)
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String repeatNewPassword;
}