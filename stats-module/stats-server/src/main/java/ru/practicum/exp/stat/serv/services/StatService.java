package ru.practicum.exp.stat.serv.services;

import ru.practicum.exp.stat.dto.ViewStatsDto;

import java.util.List;

public interface StatService {
    List<ViewStatsDto> get(String start, String end, List<String> uris, boolean unique);
}
