package com.project.ProjectMangementTool.Exceptions;

import com.project.ProjectMangementTool.Exceptions.CustomExceptions.ProjectIdentifierUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ControllerExceptionResponse extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponseModel> projectIdentifierUpdateException(WebRequest request,
                                                                                 ProjectIdentifierUpdateException ex) {
    ExceptionResponseModel exception = ExceptionResponseModel.builder()
        .message(ex.getMessage())
        .detail(request.getDescription(false))
        .build();
    return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }
}
