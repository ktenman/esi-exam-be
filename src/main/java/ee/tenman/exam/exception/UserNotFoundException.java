package ee.tenman.exam.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found with the given id");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        super(String.format("User not found with if of %s", id));
    }
}
