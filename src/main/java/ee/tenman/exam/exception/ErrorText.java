package ee.tenman.exam.exception;


import lombok.Getter;

@Getter
public enum ErrorText {

    OK("Successful"),
    ERROR("Internal Error"),
    VALIDATION_FAILED("Some of field validations are failed"),
    ERR_USER_NOT_FOUND("User is not found"),
    DB_EXCEPTION("Exception is occurred regarding the database"),
    ERR_OTHER("Contact administrator");

    private final String description;

    ErrorText(String description) {
        this.description = description;
    }
}
