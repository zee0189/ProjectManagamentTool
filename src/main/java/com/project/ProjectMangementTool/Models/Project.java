package com.project.ProjectMangementTool.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "project")
public class Project {

  @Id
  @NotBlank(message = "Project Identifier is Required")
  @Size(min = 4, max = 5, message = "Minimum 4 character and Maximum 5 character")
  @Column(updatable = false, unique = true)
  private String projectIdentifier;

  @NotBlank(message = "Project Name is Required")
  private String projectName;


  private LocalDate startDate;
  private LocalDate endDate;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}

