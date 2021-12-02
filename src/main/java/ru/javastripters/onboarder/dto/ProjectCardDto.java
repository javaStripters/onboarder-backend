package ru.javastripters.onboarder.dto;

import lombok.Data;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectCardDto {
    @Data
    static class UserDTO {
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
    String direction = "Какое-то направление";
    List<String> tags = List.of("VueJS", "Java", "Figma");

    List<UserDTO> users;

    public ProjectCardDto(Project project) {
        id = project.getId();
        name = project.getName();
        description = project.getDescription();

        users = project.getUsers().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
