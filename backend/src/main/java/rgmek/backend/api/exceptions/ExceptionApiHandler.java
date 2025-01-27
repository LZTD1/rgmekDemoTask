package rgmek.backend.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionApiHandler {

    private static List<String> getStackTraceList(RuntimeException exception) {
        return Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .toList();
    }

    @ExceptionHandler(BadBodyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse badBodyException(BadBodyException exception) {
        return new ApiErrorResponse() {{
            setCode(HttpStatus.BAD_REQUEST.toString());
            setExceptionName("BadBodyException");
            setStacktrace(getStackTraceList(exception));
            setExceptionMessage(exception.getMessage());
        }};
    }

    @ExceptionHandler(NotFoundByFiasException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse notFoundByFiasException(NotFoundByFiasException exception) {
        return new ApiErrorResponse() {{
            setCode(HttpStatus.NOT_FOUND.toString());
            setExceptionName("NotFoundByFiasException");
            setStacktrace(getStackTraceList(exception));
            setExceptionMessage(exception.getMessage());
        }};
    }
}
