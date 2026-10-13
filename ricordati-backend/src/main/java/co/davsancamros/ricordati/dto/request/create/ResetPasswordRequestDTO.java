package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordRequestDTO {

    @NotBlank(message = "Correo no puede estar vacío")
    @Email(message = "Formato de correo es obligatorio")
    private String email;



}
