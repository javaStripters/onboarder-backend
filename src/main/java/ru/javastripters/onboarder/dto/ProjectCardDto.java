package ru.javastripters.onboarder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProjectCardDto {
    @Data
    @NoArgsConstructor
    public static class UserDTO {
        int id;
        String fullname;

        public UserDTO(User user) {
            id = user.getId();
            fullname = user.getFullName();
        }
    }

    Integer id;
    String name;
    String description;
    String direction;
    List<String> tags;

    List<UserDTO> users;

    public ProjectCardDto(Project project) {
        id = project.getId();
        name = project.getName();
        description = project.getDescription();
        direction = project.getDirection();
        tags = project.getTags();

        users = project.getUsers().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public Project toEntity() {
        return Project.builder()
                .name(name)
                .description(description)
                .direction(direction)
                .tags(tags)
                .users(new ArrayList<>())
                .build();
    }
}
