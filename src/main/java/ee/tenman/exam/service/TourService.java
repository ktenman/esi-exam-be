package ee.tenman.exam.service;

import ee.tenman.exam.domain.Tour;
import ee.tenman.exam.domain.User;
import ee.tenman.exam.domain.dto.TourDto;
import ee.tenman.exam.domain.enums.TourStatus;
import ee.tenman.exam.exception.TourNotFoundException;
import ee.tenman.exam.mapper.TourMapper;
import ee.tenman.exam.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
public record TourService(TourRepository tourRepository, TourMapper tourMapper, UserService userService) {
    public TourDto save(TourDto tourDto) {
        Tour tour = tourMapper.toTourEntity(tourDto);
        Tour savedTour = tourRepository.save(tour);
        return tourMapper.toTourDto(savedTour);
    }

    public TourDto update(TourDto tourDto) {
        Tour tour = tourRepository.findById(tourDto.getTourId())
                .orElseThrow(() -> new TourNotFoundException(tourDto.getTourId()));
        tour.setName(tourDto.getName());
        tour.setBeginDate(Instant.parse(tourDto.getBeginDate()));
        tour.setEndDate(Instant.parse(tourDto.getEndDate()));
        tour.setLocation(tourDto.getLocation());
        Tour updatedTour = tourRepository.save(tour);
        return tourMapper.toTourDto(updatedTour);
    }

    public List<TourDto> findAll() {
        return tourRepository.findAll().stream()
                .map(tourMapper::toTourDto)
                .toList();
    }

    public List<TourDto> findAllByStatus(TourStatus tourStatus) {
        return tourRepository.findAllByStatusIn(Set.of(tourStatus)).stream()
                .map(tourMapper::toTourDto)
                .toList();
    }

    public List<TourDto> findAllBooked() {
        User user = userService.getUserWithRoles();
        return user.getTours().stream()
                .map(tourMapper::toTourDto)
                .toList();
    }

    public void removeFromTour(Long tourId) {
        User user = userService.getUserWithRoles();
        List<Tour> userTours = user.getTours();
        Tour tour = userTours.stream()
                .filter(t -> t.getId().equals(tourId))
                .findFirst()
                .orElseThrow();
        userTours.remove(tour);
        user.setTours(userTours);
        userService.save(user);
    }

    public List<TourDto> bookTour(Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new TourNotFoundException(tourId));
        User user = userService.getUserWithRoles();
        List<Tour> tours = user.getTours();
        tours.add(tour);
        user.setTours(tours);
        User savedUser = userService.save(user);
        return savedUser.getTours().stream()
                .map(tourMapper::toTourDto)
                .toList();
    }
}
