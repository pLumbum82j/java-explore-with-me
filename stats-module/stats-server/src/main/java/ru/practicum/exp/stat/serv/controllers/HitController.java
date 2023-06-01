package ru.practicum.exp.stat.serv.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.exp.stat.dto.HitDto;
import ru.practicum.exp.stat.serv.service.HitService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hit")
public class HitController {

    private final HitService hitService;
    @PostMapping
    public void addHit(@Validated @RequestBody HitDto hitDto) {
        hitService.create(hitDto);
    }
}
