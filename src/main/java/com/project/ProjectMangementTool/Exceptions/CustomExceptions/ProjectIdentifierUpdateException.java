package com.project.ProjectMangementTool.Exceptions.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdentifierUpdateException extends RuntimeException {
  public ProjectIdentifierUpdateException(String message) {
    super(message);
  }
}
