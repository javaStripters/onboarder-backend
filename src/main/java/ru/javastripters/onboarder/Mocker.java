package ru.javastripters.onboarder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.javastripters.onboarder.model.*;
import ru.javastripters.onboarder.repository.DiagramRepo;
import ru.javastripters.onboarder.repository.ProjectRepo;
import ru.javastripters.onboarder.repository.QuestionRepo;
import ru.javastripters.onboarder.repository.UserRepo;

import java.util.List;

@Component
public class Mocker implements CommandLineRunner {
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    private final DiagramRepo diagramRepo;
    private final QuestionRepo questionRepo;

    public Mocker(ProjectRepo projectRepo, UserRepo userRepo, ProjectRepo projectRepo1, DiagramRepo diagramRepo, QuestionRepo questionRepo) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo1;
        this.diagramRepo = diagramRepo;
        this.questionRepo = questionRepo;
    }


    @Override
    public void run(String... args) {
        Department frontend = new Department("Frontend");
        Department backend = new Department("Backend");
        Department design = new Department("Дизайн");
        Department community = new Department("Связи с общественностью");
        Department managment = new Department("Управление");

        User u1 = User.builder()
                .fullName("Christian Cameron Clark")
                .profession("Senior-разработчик")
                .avatar("avatars/vasya.jpg")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(frontend)
                .build();
        frontend.getWorkers().add(u1);

        User u2 = User.builder()
                .fullName("Ryan Miguel Walker")
                .profession("Middle-разработчик")
                .avatar("avatars/luda.jpg")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(backend)
                .build();
        backend.getWorkers().add(u2);

        User u3 = User.builder()
                .fullName("Patrick Richard Bailey")
                .profession("UI-дизайнер")
                .avatar("avatars/patrick.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(design)
                .build();
        design.getWorkers().add(u3);

        User u4 = User.builder()
                .fullName("Aidan Adrian Walker")
                .profession("Менеджер")
                .avatar("avatars/aidan.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(managment)
                .build();
        managment.getWorkers().add(u4);

        User u5 = User.builder()
                .fullName("Eric Gavin Bryant")
                .profession("Контент-менеджер")
                .avatar("avatars/eric.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(community)
                .build();
        community.getWorkers().add(u5);

        User u6 = User.builder()
                .fullName("Lucas William Anderson")
                .profession("Junior-разработчик")
                .avatar("avatars/lucas.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(frontend)
                .build();
        frontend.getWorkers().add(u6);

        User u7 = User.builder()
                .fullName("Jack Mason King")
                .profession("Middle-разработчик")
                .avatar("avatars/jack.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(backend)
                .build();
        backend.getWorkers().add(u7);

        User u8 = User.builder()
                .fullName("Adrian Elijah Howard")
                .profession("UI/UX-дизайнер")
                .avatar("avatars/adrian.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(design)
                .build();
        design.getWorkers().add(u8);

        User u9 = User.builder()
                .fullName(" Isaiah Christopher Diaz")
                .profession("Менеджер")
                .avatar("avatars/isaiah.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(community)
                .build();
        community.getWorkers().add(u9);

        User u10 = User.builder()
                .fullName("Luke James Perry")
                .profession("Стажер-разработчик")
                .avatar("avatars/luke.png")
                .about("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis lorem quis nisi mollis pretium. Proin accumsan tellus quis ante pharetra ornare quis iaculis tellus.")
                .contacts("{\"phoneNumber\":\"8 (406) 555-0120\",\"telegramId\":\"@felicia\",\"emailAdress\":\"tim.jennings@example.com\",\"facebookId\":\"Robert_Fox\"}")
                .department(frontend)
                .build();
        frontend.getWorkers().add(u10);

        Project project1 = Project.builder()
                .name("StayConnected")
                .description("Данный проект ориентирован на разработку портала, который поспособствует объединению людей в единой среде с использованием компонентов, принятыми пользователями на опыте других, уже существующих приложений")
                .users(List.of(u2, u3, u8, u9))
                .direction("Web")
                .tags(List.of("JS", "Figma"))
                .build();

        Project project2 = Project.builder()
                .name("Quizzer!")
                .description("Основная задача проекта заключается в разработке развлекательной Quiz-игры, которая поможет весело провести свободное время с близкими или друзьями")
                .users(List.of(u1, u2, u4, u6, u8, u10))
                .direction("Desktop")
                .tags(List.of("C#", "GameDev", "Unity"))
                .build();

        Project project3 = Project.builder()
                .name("Message&Convenience")
                .description("Разработка мобильного мессенджера для поддержания контакта с людьми в приятном интерфейсе с практичным функционалом")
                .users(List.of(u3, u5, u7, u10, u2))
                .direction("Android-приложение")
                .tags(List.of("Java", "WebSockets", "NoSQL"))
                .build();

        projectRepo.saveAll(List.of(project1, project2, project3));

        Diagram classDiagram = new Diagram(Diagram.Type.CLASS, "class.jpg", "# Диаграмма классов \n вот тут диаграмма");
        classDiagram.setProject(project1);

        Diagram deploymentDiagram = new Diagram(Diagram.Type.DEPLOYMENT, "deployment.jpg", "# Deployment-диаграмма \n и тут тоже");
        deploymentDiagram.setProject(project1);

        diagramRepo.saveAll(List.of(classDiagram, deploymentDiagram));
    }
}
