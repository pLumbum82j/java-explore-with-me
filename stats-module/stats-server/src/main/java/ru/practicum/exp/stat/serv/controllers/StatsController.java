package ru.practicum.exp.stat.serv.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.exp.stat.dto.StatsDto;
import ru.practicum.exp.stat.serv.service.HitService;
import ru.practicum.exp.stat.serv.service.StatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {

    private final HitService hitService;
    private final StatService statService;

    @GetMapping
    public List<StatsDto> getStats(@RequestParam String start,
                                   @RequestParam String end,
                                   @RequestParam(required = false) List<String> uris,
                                   @RequestParam(defaultValue = "false") Boolean unique) {
        // List<StatsDto> list = statService.get(start, end, uris, unique);
        return null;
    }
}
