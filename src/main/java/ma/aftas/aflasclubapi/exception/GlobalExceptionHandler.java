package ma.aftas.aflasclubapi.exception;


import ma.aftas.aflasclubapi.exception.business.AlreadyExistsException;
import ma.aftas.aflasclubapi.exception.business.BadRequestException;
import ma.aftas.aflasclubapi.exception.business.UserNotFoundException;
import ma.aftas.aflasclubapi.exception.response.ErrorResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class, UserNotFoundException.class , AlreadyExistsException.class})
    public ResponseEntity<Object> globalExceptionHundler(Exception ex) {
        ErrorResponse errorRes = new ErrorResponse("400", HttpStatus.BAD_REQUEST, "");
        errorRes.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(errorRes);
    }





    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorResponse> globalExceptionHundler(NullPointerException ex) {
        ErrorResponse errorRes = new ErrorResponse("400", HttpStatus.BAD_REQUEST, "");
        errorRes.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(errorRes);
    }

    @ExceptionHandler({
            BadRequestException.class
    })
    public ResponseEntity<ErrorResponse> badRequestException(BadRequestException exception){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDetails(exception.getDetails());
        errorResponse.setHorodatage(LocalDateTime.now());
        errorResponse.setCode("400");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.badRequest().body(errorResponse);


    }



    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {

        ErrorResponse errorRes = new ErrorResponse("400", HttpStatus.BAD_REQUEST, "Bad Request ");
        errorRes.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(errorRes);
    }

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<Object> dateTimeParseException(ObjectNotFoundException ex, WebRequest request) {

        ErrorResponse errorRes = new ErrorResponse("404", HttpStatus.NOT_FOUND, "Not Found ");
        errorRes.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(errorRes);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<Object> objectNotFoundException(ObjectNotFoundException ex, WebRequest request) {

        ErrorResponse errorRes = new ErrorResponse("404", HttpStatus.NOT_FOUND, "Not Found ");
        errorRes.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorRes) {
        HttpStatus status = HttpStatus.valueOf(errorRes.getStatus());
        System.out.println("status = " + status);

        return new ResponseEntity<>(errorRes, status);
    }
}
