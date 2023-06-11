package ru.practicum.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.models.Location;
import ru.practicum.models.dto.LocationDto;

@UtilityClass
public class LocationMapper {
    public Location locationDtoToLocation(LocationDto locationDto) {
        return Location.builder()
                .lat(locationDto.getLat())
                .lon(locationDto.getLon())
                .build();
    }

    public LocationDto locationToLocationDto(Location location) {
        return LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}
