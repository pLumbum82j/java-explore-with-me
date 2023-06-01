package ru.practicum.exp.stat.serv.mapper;

import ru.practicum.exp.stat.dto.HitDto;
import ru.practicum.exp.stat.serv.model.Hit;

public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .ip(hitDto.getIp())
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                //.timestamp(DateFormatter.formatDate(hitDto.getTimestamp()))
                .build();
    }
}