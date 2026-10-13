package co.davsancamros.ricordati.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate dateOfBirth;
    private String governmentId;
    private AccountResponseDTO account;

}
