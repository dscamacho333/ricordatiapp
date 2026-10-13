package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OneTimePinResetPasswordValidationRequestDTO extends OneTimePinRegistrationValidationRequestDTO {

    @NotBlank(message = "Contraseña no puede estar vacía")
    @Size(min = 8, max = 15, message = "Contraseña debe contener mínimo 8 y máximo 15 caracteres")
    private String newPassword;

    @NotBlank(message = "Contraseña no puede estar vacía")
    @Size(min = 8, max = 15, message = "Contraseña debe contener mínimo 8 y máximo 15 caracteres")
    private String newPasswordConfirmation;

}
