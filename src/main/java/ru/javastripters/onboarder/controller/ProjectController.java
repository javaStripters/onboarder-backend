package ru.javastripters.onboarder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javastripters.onboarder.dto.MarkdownDTO;
import ru.javastripters.onboarder.dto.ProjectCardDto;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectCardDto createProject(@RequestBody ProjectCardDto dto) {
        return new ProjectCardDto(projectService.createProject(dto));
    }

    @PutMapping()
    public ProjectCardDto updateProjectCard(@RequestBody ProjectCardDto dto) {
        return new ProjectCardDto(projectService.updateProjectCard(dto));
    }

    @GetMapping("{projectId}/goals")
    public MarkdownDTO getProjectGoals(@PathVariable int projectId) {
        return new MarkdownDTO(projectService.getGoals(projectId));
    }

    @PutMapping("{projectId}/goals")
    public MarkdownDTO setProjectGoals(@PathVariable int projectId, @RequestBody MarkdownDTO dto) {
        return new MarkdownDTO(projectService.setGoals(projectId, dto.getContent()));
    }
}
