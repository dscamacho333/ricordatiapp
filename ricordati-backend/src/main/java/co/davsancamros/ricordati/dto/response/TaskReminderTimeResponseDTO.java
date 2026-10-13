package co.davsancamros.ricordati.dto.response;

import co.davsancamros.ricordati.enums.TimeUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskReminderTimeResponseDTO {

    private Long id;
    private Integer time;
    private TimeUnit timeUnit;

}
