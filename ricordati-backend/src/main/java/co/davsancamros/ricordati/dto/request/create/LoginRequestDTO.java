package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Correo no puede estar vacío")
    @Email(message = "Formato de correo es obligatorio")
    private String email;

    @NotBlank(message = "Contraseña no puede estar vacía")
    private String password;

}
