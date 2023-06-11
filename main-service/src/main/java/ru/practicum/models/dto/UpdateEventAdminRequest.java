package ru.practicum.models.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import ru.practicum.models.enums.ActionStateDto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Value
@Builder
public class UpdateEventAdminRequest { // Данные для изменения информации о событии. Если поле в запросе не указано (равно null) - значит изменение этих данных не треубется.
    @Size(min = 20, max = 2000, message = "Минимальное кол-во символов для аннотации: 20. Максимальное: 2000")
    String annotation;
    Long category;
    @Size(min = 20, max = 7000, message = "Минимальное кол-во символов для описания: 20. Максимальное: 7000")
    String description;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Invalid date format")
    String eventDate;
    LocationDto location;
    Boolean paid;
    @PositiveOrZero
    Integer participantLimit;
    Boolean requestModeration;
    ActionStateDto stateAction;
    @Size(min = 3, max = 120, message = "Минимальное кол-во символов для заголовка: 5. Максимальное: 120")
    String title;
}
