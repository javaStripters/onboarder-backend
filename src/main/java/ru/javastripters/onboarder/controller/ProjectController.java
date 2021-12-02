package ru.javastripters.onboarder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javastripters.onboarder.dto.ProjectCardDto;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("user/{user_id}/")
    public List<ProjectCardDto> getProjects(@PathVariable("user_id") int userId) {
        return projectService.getAllProjects(userId).stream().map(ProjectCardDto::new).collect(Collectors.toList());
    }
}
