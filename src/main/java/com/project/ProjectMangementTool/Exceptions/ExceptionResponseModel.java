package com.project.ProjectMangementTool.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
class ExceptionResponseModel {
  private String message;
  private String detail;
}
