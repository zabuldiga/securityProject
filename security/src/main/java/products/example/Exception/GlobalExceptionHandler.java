package products.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
          return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<AppError> catchForbiddenException(ForbiddenException e){
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(), e.getMessage()),HttpStatus.FORBIDDEN);

    }
}
