package com.project.ProjectMangementTool.Service;

import com.project.ProjectMangementTool.Dao.ProjectDao;
import com.project.ProjectMangementTool.Models.Project;
import com.project.ProjectMangementTool.Utils.ServiceUtils;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProjectService {

  public static final String EMPTY_STRING = "";
  private final ProjectDao projectDao;

  @Autowired
  public ProjectService(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  public void addProject(Project project) {
    projectDao.save(project);
  }

  public Optional<Project> getProject(String id) {
    Optional<Project> projectById = projectDao.getProjectById(id);
    if (!projectById.isPresent()) {
      log.error("No record found for Project with Id: {}", id);
      return Optional.empty();
    }
    return projectById;
  }

  public Stream<Project> getAllProject() {
    return projectDao.getAllProjects();
  }

  public String removeProject(String id) {
    boolean isDeleted = projectDao.removeProjectById(id);
    if (isDeleted) {
      return id;
    }
    return EMPTY_STRING;
  }
}

