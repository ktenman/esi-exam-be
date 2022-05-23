package ee.tenman.exam.controller;

import ee.tenman.exam.domain.dto.TourDto;
import ee.tenman.exam.domain.enums.TourStatus;
import ee.tenman.exam.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tours")
@AllArgsConstructor
public class TourController {
    private final TourService tourService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TourDto save(@Valid @RequestBody TourDto tourDto) {
        return tourService.save(tourDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TourDto update(@Valid @RequestBody TourDto tourDto) {
        return tourService.update(tourDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    public List<TourDto> findAll() {
        return tourService.findAll();
    }

    @GetMapping("/guests")
    @PreAuthorize("hasRole('USER') or hasRole('PARTNER') or hasRole('ADMIN')")
    public List<TourDto> findAllForGuests() {
        return tourService.findAll();
    }

    @GetMapping("/guests/available")
    @PreAuthorize("hasRole('USER') or hasRole('PARTNER') or hasRole('ADMIN')")
    public List<TourDto> findAllAvailable() {
        return tourService.findAllByStatus(TourStatus.AVAILABLE);
    }

    @PostMapping("/guests/book/{tourId}")
    @PreAuthorize("hasRole('USER') or hasRole('PARTNER') or hasRole('ADMIN')")
    public List<TourDto> bookTour(@PathVariable Long tourId) {
        return tourService.bookTour(tourId);
    }

    @GetMapping("/guests/booked")
    @PreAuthorize("hasRole('USER') or hasRole('PARTNER') or hasRole('ADMIN')")
    public List<TourDto> findAllBooked() {
        return tourService.findAllBooked();
    }

    @DeleteMapping("/guests/booked/{tourId}")
    @PreAuthorize("hasRole('USER') or hasRole('PARTNER') or hasRole('ADMIN')")
    public void removeFromTour(@PathVariable Long tourId) {
        tourService.removeFromTour(tourId);
    }
}
