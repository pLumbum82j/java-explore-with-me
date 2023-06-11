package ru.practicum.models.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Value
public class UpdateCompilationRequest { //Изменение информации о подборке событий. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    List<Long> events;
    Boolean pinned;
    @Size(max = 50, message = "Максимальное кол-во символов для описания: 50")
    String title;
}
