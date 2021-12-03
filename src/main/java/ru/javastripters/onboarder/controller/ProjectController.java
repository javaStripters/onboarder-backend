package ru.javastripters.onboarder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.javastripters.onboarder.dto.MarkdownDTO;
import ru.javastripters.onboarder.dto.ProjectCardDto;
import ru.javastripters.onboarder.model.Diagram;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;
import ru.javastripters.onboarder.repository.DiagramRepo;
import ru.javastripters.onboarder.repository.ProjectRepo;
import ru.javastripters.onboarder.repository.UserRepo;
import ru.javastripters.onboarder.service.ProjectService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;
    private final DiagramRepo diagramRepo;
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    public ProjectController(ProjectService projectService, DiagramRepo diagramRepo, ProjectRepo projectRepo, UserRepo userRepo) {
        this.projectService = projectService;
        this.diagramRepo = diagramRepo;
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("user/{user_id}/")
    public List<ProjectCardDto> getProjects(@PathVariable("user_id") int userId) {
        return projectService.getAllProjects(userId).stream().map(ProjectCardDto::new).collect(Collectors.toList());
    }

    @GetMapping("{projectId}/users")
    public Map<String, List<User>> getUsers(@PathVariable int projectId) {
        return projectRepo.findById(projectId).getUsers().stream().collect(Collectors.groupingBy(v -> v.getDepartment().getName()));
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

    @GetMapping("{projectId}")
    public Project getProject(@PathVariable int projectId) {
        return projectRepo.findById(projectId);
    }

    @GetMapping("{projectId}/goals")
    public MarkdownDTO getProjectGoals(@PathVariable int projectId) {
        return new MarkdownDTO(projectService.getGoals(projectId));
    }

    @PutMapping("{projectId}/goals")
    public MarkdownDTO setProjectGoals(@PathVariable int projectId, @RequestBody MarkdownDTO dto) {
        return new MarkdownDTO(projectService.setGoals(projectId, dto.getContent()));
    }

    @PostMapping("{projectId}/diagram")
    public Diagram addDiagram(@PathVariable int projectId, @RequestBody Diagram diagram) {
        Project project = projectRepo.findById(projectId);

        diagram.setProject(project);
        return diagramRepo.save(diagram);
    }

    @GetMapping("{projectId}/diagram/{diagramId}")
    public Diagram getDiagram(@PathVariable int projectId, @PathVariable int diagramId) {
        return diagramRepo.findById(diagramId).get();
    }

    @PutMapping("{projectId}/diagram/{diagramId}")
    public Diagram updateDiagram(@PathVariable int projectId, @PathVariable int diagramId, @RequestBody Diagram diagram) {
        Project project = projectRepo.findById(projectId);

        diagram.setId(diagramId);
        diagram.setProject(project);

        return diagramRepo.save(diagram);
    }
}
