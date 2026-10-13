package co.davsancamros.ricordati.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskCategoryResponseDTO {

    private Long id;
    private String name;
    private String description;

}
