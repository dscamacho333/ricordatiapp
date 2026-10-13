package co.davsancamros.ricordati.dto.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateRequestDTO {

    @NotBlank
    @Size(max = 25)
    private String firstName;

    @Size(max = 25)
    private String middleName;

    @NotBlank
    @Size(max = 25)
    private String lastName;

    @Size(max = 25)
    private String secondLastName;

    @NotNull
    @Valid
    private AccountUpdateRequestDTO account;


}
