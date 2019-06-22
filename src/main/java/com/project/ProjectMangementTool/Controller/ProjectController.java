package com.project.ProjectMangementTool.Controller;

import com.project.ProjectMangementTool.Exceptions.CustomExceptions.ProjectIdentifierUpdateException;
import com.project.ProjectMangementTool.Models.Project;
import com.project.ProjectMangementTool.Service.ProjectService;
import com.project.ProjectMangementTool.Utils.ControllerUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping("/post")
  public ResponseEntity<?> addProject(@Valid @RequestBody Project project, BindingResult result) {
    if (result.hasErrors()) {
      return ControllerUtils.getErrorMapResponse(result);
    }
    try {
      projectService.addProject(project);
    } catch (Exception ex) {
      throw new ProjectIdentifierUpdateException(
          String.format("Project with identifier: %s already exists.",
                        project.getProjectIdentifier()));
    }
    log.info("New Project with Id:{} and ProjectName:{} is added",
             project.getProjectIdentifier(),
             project.getProjectName());
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

  @GetMapping("fetch/{id}")
  public ResponseEntity<?> getProjectById(@PathVariable(name = "id") String id) {
    Optional<Project> project = projectService.getProject(id);
    if (!project.isPresent()) {
      log.warn("No Project found with id : {}", id);
      return new ResponseEntity<>(id,
                                  HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

  @GetMapping("/fetchall")
  public ResponseEntity<?> getAllProject() {
    List<Project> projectList = projectService.getAllProject().collect(Collectors.toList());
    if (projectList.isEmpty()) {
      log.warn("No Project records found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(projectList, HttpStatus.OK);
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<?> removeProjectById(@PathVariable(name = "projectId") String id) {
    String removedProject = projectService.removeProject(id);
    if (Strings.isBlank(removedProject)) {
      return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
