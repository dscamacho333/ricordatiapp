package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountCreateRequestDTO {

    @NotBlank(message = "Email no puede estar vacío")
    @Email(message = "Email debe tener un formato valido")
    @Size(max = 50, message = "Email no puede contener más de 50 caracteres")
    private String email;

    @NotBlank(message = "Contraseña no puede estar vacía")
    @Size(min = 8, max = 15, message = "Contraseña debe contener mínimo 8 y máximo 15 caracteres")
    private String password;

}
