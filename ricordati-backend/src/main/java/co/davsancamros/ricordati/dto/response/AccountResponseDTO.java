package co.davsancamros.ricordati.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AccountResponseDTO {

    private Long id;
    private String email;
    private AccountStatusResponseDTO accountStatus;
    private LocalDate createdAt;

}
