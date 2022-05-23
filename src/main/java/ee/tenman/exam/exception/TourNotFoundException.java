package ee.tenman.exam.exception;

public class TourNotFoundException extends RuntimeException {

    public TourNotFoundException(Long id) {
        super(String.format("Tour not found with if of %s", id));
    }
}
