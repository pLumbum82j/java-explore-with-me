package ru.practicum.exp.stat.serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.exp.stat.serv.models.Hit;

/**
 * Интерфейс HitRepository для обработки запросов к БД
 */
@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
}
