package ru.javastripters.onboarder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.javastripters.onboarder.model.Department;
import ru.javastripters.onboarder.model.Diagram;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;
import ru.javastripters.onboarder.repository.DiagramRepo;
import ru.javastripters.onboarder.repository.ProjectRepo;
import ru.javastripters.onboarder.repository.UserRepo;

import java.util.List;

@Component
public class Mocker implements CommandLineRunner {
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    private final DiagramRepo diagramRepo;

    public Mocker(ProjectRepo projectRepo, UserRepo userRepo, ProjectRepo projectRepo1, DiagramRepo diagramRepo) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo1;
        this.diagramRepo = diagramRepo;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Department managers = new Department("Управление");
        Department developers = new Department("Разработка");

        User m1 = User.builder()
                .fullName("Вася Пупкин")
                .profession("Менеджер")
                .avatar("avatars/vasya.jpg")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(managers)
                .build();

        User m2 = User.builder()
                .fullName("Rober Fox")
                .profession("Старшие менеджер")
                .avatar("avatars/arkasha.jpg")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(managers)
                .build();

        managers.getWorkers().addAll(List.of(m1, m2));

        User dev = User.builder()
                .fullName("Eleanor Pena")
                .profession("Фулкек разработчик")
                .avatar("avatars/luda.jpg")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(developers)
                .build();

        developers.getWorkers().add(dev);

        Project project = Project.builder()
                .name("Крутой проект")
                .description(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi gravida eget ipsum fermentum mollis. Donec eget erat est. Cras lacinia sit amet nisl id egestas. Curabitur dui purus, blandit eu leo eget, volutpat fringilla erat. Mauris nec ante elementum, consequat magna et, fermentum diam. Integer vestibulum mollis elit at dapibus. Curabitur efficitur arcu vitae velit bibendum placerat. ")
                .users(List.of(m1, m2, dev))
                .direction("Mobile")
                .tags(List.of("Android", "Kotlin"))
                .build();

        Project project1 = Project.builder()
                .name("Тоже крутой проект")
                .description("Не такое длинное описание")
                .users(List.of(dev))
                .direction("Web")
                .tags(List.of("VueJS", "Java", "JS"))
                .build();

        projectRepo.saveAll(List.of(project, project1));

        Diagram classDiagram = new Diagram(Diagram.Type.CLASS, "class.jpg", "# Диаграмма классов \n вот тут диаграмма");
        classDiagram.setProject(project);

        Diagram deploymentDiagram = new Diagram(Diagram.Type.DEPLOYMENT, "deployment.jpg", "# Deployment-диаграмма \n и тут тоже");
        deploymentDiagram.setProject(project);

        diagramRepo.saveAll(List.of(classDiagram, deploymentDiagram));


    }
}
