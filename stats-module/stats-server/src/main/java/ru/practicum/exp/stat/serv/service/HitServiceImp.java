package ru.practicum.exp.stat.serv.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exp.stat.dto.HitDto;
import ru.practicum.exp.stat.serv.mapper.HitMapper;
import ru.practicum.exp.stat.serv.model.Hit;
import ru.practicum.exp.stat.serv.repsitory.HitRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class HitServiceImp implements HitService {

    private final HitRepository hitRepository;

    @Override
    @Transactional
    public void create(HitDto hitDto) {
        log.info("Сохранение информации о запросе " + hitDto.getUri());
        Hit hit = HitMapper.toHit(hitDto);
        hitRepository.save(hit);
    }
}
