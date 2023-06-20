package ru.practicum.models.dto;

import lombok.*;
import org.mapstruct.Mapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Модель объекта NewCompilation Data Transfer Object
 * (Данные для добавления новой подборки событий)
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class NewCompilationDto {
    List<Long> events;
    boolean pinned;
    @Size(max = 50, message = "Максимальное кол-во символов для описания: 50")
    @NotBlank(message = "title не может быть пустым")
    String title;
}
