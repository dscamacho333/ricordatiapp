package co.davsancamros.ricordati.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskCategoryCreateRequestDTO {

    @Size(max = 25)
    @NotBlank
    private String name;

    @Size(max = 150)
    @NotBlank
    private String description;

}
