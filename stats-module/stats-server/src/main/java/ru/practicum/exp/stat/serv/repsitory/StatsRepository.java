package ru.practicum.exp.stat.serv.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.exp.stat.serv.model.Hit;

@Repository
public interface StatsRepository extends JpaRepository<Hit, Long>  {
}
