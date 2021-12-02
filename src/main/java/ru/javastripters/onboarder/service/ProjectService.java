package ru.javastripters.onboarder.service;

import org.springframework.stereotype.Service;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.repository.ProjectRepo;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public List<Project> getAllProjects(int userId) {
        return (List<Project>) projectRepo.findAll();
    }
}
