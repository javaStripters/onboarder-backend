package ru.javastripters.onboarder.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.javastripters.onboarder.dto.ProjectCardDto;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;
import ru.javastripters.onboarder.repository.ProjectRepo;
import ru.javastripters.onboarder.repository.UserRepo;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    public ProjectService(ProjectRepo projectRepo, UserRepo userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    public List<Project> getAllProjects(int userId) {
        return projectRepo.findAllByUsersContains(userRepo.findUsersById(userId));
    }

    public Project createProject(ProjectCardDto dto) {
        Project project = dto.toEntity();

        List<Integer> userIds = dto.getUsers().stream().map(ProjectCardDto.UserDTO::getId).collect(Collectors.toList());
        project = addUsers(project, userIds);

        projectRepo.save(project);
        return project;
    }

    public Project updateProjectCard(ProjectCardDto dto) {
        Project project = projectRepo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        if (dto.getName() != null && !dto.getName().isBlank())
            project.setName(dto.getName());
        if (dto.getDescription() != null && !dto.getDescription().isBlank())
            project.setDescription(dto.getDescription());
        if (dto.getDirection() != null && !dto.getDirection().isBlank())
            project.setDirection(dto.getDirection());
        if (!dto.getTags().isEmpty())
            project.setTags(dto.getTags());
        if (!dto.getUsers().isEmpty()) {
            List<Integer> userIds = dto.getUsers().stream().map(ProjectCardDto.UserDTO::getId).collect(Collectors.toList());
            project = setUsers(project, userIds);
        }

        return project;
    }

    public Project addUsers(Project project, Iterable<Integer> userIds) {
        List<User> users = userRepo.findUsersByIdIn(userIds);
        project.addUsers(users);
        projectRepo.save(project);
        return project;
    }

    public Project setUsers(Project project, Iterable<Integer> userIds) {
        List<User> users = userRepo.findUsersByIdIn(userIds);
        project.setUsers(users);
        projectRepo.save(project);
        return project;
    }

    public String getGoals(int projectId) {
        Project project = projectRepo.findById(projectId);

        return project.getGoals();
    }

    public String setGoals(int projectId, String content) {
        Project project = projectRepo.findById(projectId);

        project.setGoals(content);
        projectRepo.save(project);

        return project.getGoals();
    }

    public List<String> getInstruments(int projectId) {
        Project project = projectRepo.findById(projectId);

        return project.getInstruments();
    }

    public List<String> setInstruments(int projectId, List<String> instruments) {
        Project project = projectRepo.findById(projectId);

        project.setInstruments(instruments);
        projectRepo.save(project);

        return project.getInstruments();
    }
}
