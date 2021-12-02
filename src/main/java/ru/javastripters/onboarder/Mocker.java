package ru.javastripters.onboarder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.javastripters.onboarder.model.Department;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;
import ru.javastripters.onboarder.repository.ProjectRepo;
import ru.javastripters.onboarder.repository.UserRepo;

import java.util.List;
import java.util.Set;

@Component
public class Mocker implements CommandLineRunner {
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;

    public Mocker(ProjectRepo projectRepo, UserRepo userRepo, ProjectRepo projectRepo1) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo1;
    }


    @Override
    public void run(String... args) throws Exception {
        Department managers = new Department("Управление");
        Department developers = new Department("Разработка");

        User m1 = new User("Вася Пупки", "Менеджер", managers);
        User m2 = new User("Rober Fox", "Старший менеджере", managers);
        User dev = new User("Eleanor Pena", "Фулкек разработчик", developers);

        Project project = Project.builder()
                .name("Крутой проект")
                .description(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi gravida eget ipsum fermentum mollis. Donec eget erat est. Cras lacinia sit amet nisl id egestas. Curabitur dui purus, blandit eu leo eget, volutpat fringilla erat. Mauris nec ante elementum, consequat magna et, fermentum diam. Integer vestibulum mollis elit at dapibus. Curabitur efficitur arcu vitae velit bibendum placerat. ")
                .users(Set.of(m1, m2, dev))
                .build();

        Project project1 = Project.builder()
                .name("Тоже крутой проект")
                .description("Не такое длинное описание")
                .users(Set.of(dev))
                .build();

        projectRepo.saveAll(List.of(project, project1));
    }
}
