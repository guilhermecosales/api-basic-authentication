package br.com.github.apibasicauthentication.resource.exception;

import br.com.github.apibasicauthentication.service.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ProblemDetail> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException error, HttpServletRequest httpServletRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(412);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatusCode, MethodArgumentNotValidException.errorsToStringList(error.getAllErrors()).toString());
        problemDetail.setType(URI.create(httpServletRequest.getRequestURL().toString()));
        return ResponseEntity.status(httpStatusCode).body(problemDetail);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ProblemDetail> notfoundExceptionHandler(NotFoundException error, HttpServletRequest httpServletRequest) {
        HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(404);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatusCode, error.getMessage());
        problemDetail.setType(URI.create(httpServletRequest.getRequestURL().toString()));
        return ResponseEntity.status(httpStatusCode).body(problemDetail);
    }

}
