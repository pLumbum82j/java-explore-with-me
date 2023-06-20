package ru.practicum.models;

import lombok.*;
import org.mapstruct.Mapper;

import javax.persistence.*;

/**
 * Модель объекта Category
 */
@Getter
@Setter
@Entity
@Builder
@Mapper(componentModel = "spring")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
}
