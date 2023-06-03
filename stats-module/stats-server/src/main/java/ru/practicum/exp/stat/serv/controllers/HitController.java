package ru.practicum.exp.stat.serv.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.exp.stat.dto.HitDto;
import ru.practicum.exp.stat.serv.services.HitService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hit")
public class HitController {

    private final HitService hitService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addHit(@Validated @RequestBody HitDto hitDto) {
        hitService.create(hitDto);
    }
}
