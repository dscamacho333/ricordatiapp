package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OneTimePinRegistrationValidationRequestDTO {

    @NotBlank(message = "Correo no puede estar vacío")
    @Email(message = "Formato de correo es obligatorio")
    private String email;

    @NotBlank(message = "PIN no puede estar vacío")
    @Size(min = 6, max = 6, message = "PIN debe tener 6 digitos")
    private String otp;


}
