package by.it.medved.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static by.it.medved.util.Message.*;
import static by.it.medved.util.Patterns.*;

@Data
@Builder
public class ChangePasswordRequest {

    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String currentPassword;
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String newPassword;
    @Pattern(regexp = PASSWORD, message = PASSWORD_REQUIREMENTS)
    private String repeatNewPassword;
}