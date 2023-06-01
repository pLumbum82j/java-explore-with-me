package ru.practicum.exp.stat.serv.service;

import ru.practicum.exp.stat.dto.StatsDto;

import java.util.List;

public interface StatService {
    List<StatsDto> get(String start, String end, List<String> uris, boolean unique);
}
