package ru.practicum.exp.stat.serv.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.exp.stat.dto.StatsDto;
import ru.practicum.exp.stat.serv.repsitory.StatsRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImp implements StatService {

    private final StatsRepository statsRepository;

    @Override
    public List<StatsDto> get(String start, String end, List<String> uris, boolean unique) {
        return null;
    }
}
