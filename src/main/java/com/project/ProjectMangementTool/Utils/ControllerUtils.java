package com.project.ProjectMangementTool.Utils;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ControllerUtils {

  private ControllerUtils() {
  }

  public static ResponseEntity<?> getErrorMapResponse(BindingResult result) {
    Map<String, String> errorList = result.getFieldErrors()
        .stream()
        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
  }
}
