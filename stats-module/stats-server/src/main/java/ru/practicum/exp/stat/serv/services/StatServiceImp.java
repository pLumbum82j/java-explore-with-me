package ru.practicum.exp.stat.serv.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.exp.stat.dto.ViewStatsDto;
import ru.practicum.exp.stat.serv.exceptions.ValidationDateException;
import ru.practicum.exp.stat.serv.repositories.StatsRepository;
import ru.practicum.exp.stat.serv.util.DateFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImp implements StatService {

    private final StatsRepository statsRepository;

    @Override
    public List<ViewStatsDto> get(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime newStart = DateFormatter.formatDate(start);
        LocalDateTime newEnd = DateFormatter.formatDate(end);
        if (newEnd.isBefore(newStart) || newStart.isAfter(newEnd)) {
            throw new ValidationDateException("Неверно заданы даты для поиска");
        }
        if (uris == null && !unique) {
            return statsRepository.findByDate(newStart, newEnd);
        }
        if (uris == null && unique) {
            return statsRepository.findByDateAndUniqueIp(newStart, newEnd);
        }
        if (!uris.isEmpty() && !unique) {
            return statsRepository.findByDateAndUris(newStart, newEnd, uris);
        }
        if (!uris.isEmpty() && unique) {
            return statsRepository.findByDateAndUrisWithUniqueIp(newStart, newEnd, uris);
        }
        return new ArrayList<>();
    }

}
