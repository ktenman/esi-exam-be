package ee.tenman.exam.mapper;

import ee.tenman.exam.domain.Tour;
import ee.tenman.exam.domain.dto.TourDto;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TourMapper {
    public Tour toTourEntity(TourDto tourDto) {
        return new Tour(t -> {
            t.setName(tourDto.getName());
            t.setLocation(tourDto.getLocation());
            t.setBeginDate(Instant.parse(tourDto.getBeginDate()));
            t.setEndDate(Instant.parse(tourDto.getEndDate()));
        });
    }

    public TourDto toTourDto(Tour tour) {
        return TourDto.builder()
                .tourId(tour.getId())
                .name(tour.getName())
                .location(tour.getLocation())
                .beginDate(tour.getBeginDate().toString())
                .endDate(tour.getEndDate().toString())
                .build();
    }
}
