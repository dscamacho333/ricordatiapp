package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequestDTO {

    @NotBlank(message = "Primer nombre no puede estar vacío")
    @Size(max = 25, message = "Primer nombre no puede contener más de 25 caracteres")
    private String firstName;

    @Size(max = 25, message = "Segundo nombre no puede contener más de 25 caracteres")
    private String middleName;

    @NotBlank(message = "Primer apellido no puede estar vacío")
    @Size(max = 25, message = "Primer apellido no puede contener más de 25 caracteres")
    private String lastName;

    @Size(max = 25, message = "Segundo apellido no puede contener más de 25 caracteres")
    private String secondLastName;

    @NotNull(message = "Fecha de nacimiento no puede ser nula")
    @Past
    private LocalDate dateOfBirth;

    @NotBlank(message = "Cédula no puede estar vacía")
    @Size(max = 10, message = "Cédula no puede contener más de 10 digitos")
    @Pattern(regexp = "^[0-9]{10}$", message = "Cédula no puede contener digitos fuera del 0-9")
    private String governmentId;

    @NotNull
    @Valid
    private AccountCreateRequestDTO account;

}
