package ru.practicum.exp.stat.serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.exp.stat.dto.ViewStatsDto;
import ru.practicum.exp.stat.serv.models.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Hit, Long>  {
    @Query("SELECT NEW ru.practicum.exp.stat.dto.ViewStatsDto(h.app, h.uri, COUNT (h.ip)) FROM Hit AS h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT (h.ip) DESC")
    List<ViewStatsDto> findByDate(LocalDateTime start, LocalDateTime end);

    @Query("SELECT NEW ru.practicum.exp.stat.dto.ViewStatsDto(h.app, h.uri, COUNT (DISTINCT h.ip)) FROM Hit AS h " +
            "WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT (h.ip) DESC")
    List<ViewStatsDto> findByDateAndUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query("SELECT NEW ru.practicum.exp.stat.dto.ViewStatsDto(h.app, h.uri, COUNT (h.ip)) FROM Hit AS h " +
            "WHERE h.timestamp BETWEEN :start AND :end AND h.uri IN :uris " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT (h.ip) DESC")
    List<ViewStatsDto> findByDateAndUris(LocalDateTime start, LocalDateTime end, @Param("uris")List<String> uris);

    @Query("SELECT NEW ru.practicum.exp.stat.dto.ViewStatsDto(h.app, h.uri, COUNT (DISTINCT h.ip)) FROM Hit AS h " +
            "WHERE h.timestamp BETWEEN :start AND :end AND h.uri IN :uris " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT (h.ip) DESC")
    List<ViewStatsDto> findByDateAndUrisWithUniqueIp(LocalDateTime start, LocalDateTime end, @Param("uris") List<String> uris);
}
