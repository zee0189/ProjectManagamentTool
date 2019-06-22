package com.project.ProjectMangementTool.Dao;

import com.project.ProjectMangementTool.Models.Project;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProjectDao {

  private final ProjectRepo projectRepo;

  @Autowired
  public ProjectDao(ProjectRepo projectRepo) {
    this.projectRepo = projectRepo;
  }

  public void save(Project project) {
    project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase(Locale.US));
    projectRepo.save(project);
    log.info("Project With Id: {} is Stored.", project.getProjectIdentifier());
  }

  public Optional<Project> getProjectById(String id) {
    return projectRepo.findById(id);
  }

  public boolean isProjectWithIdExists(String id) {
    return projectRepo.existsById(id);
  }

  public Stream<Project> getAllProjects() {
    return StreamSupport.stream(projectRepo.findAll().spliterator(), false);
  }

  public boolean removeProjectById(String id) {
    if (projectRepo.existsById(id)) {
      projectRepo.deleteById(id);
      log.info("Project With Id: {} is deleted.", id);
      return true;
    }
    log.info("Project With Id: {} is not found.", id);
    return false;
  }
}
