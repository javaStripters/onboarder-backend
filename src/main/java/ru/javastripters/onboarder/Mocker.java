package ru.javastripters.onboarder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.javastripters.onboarder.model.*;
import ru.javastripters.onboarder.repository.*;
import ru.javastripters.onboarder.service.StackOverslowService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mocker implements CommandLineRunner {
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    private final DiagramRepo diagramRepo;
    private final QuestionRepo questionRepo;
    private final StackOverslowService stackOverslowService;
    private final AnswerRepo answerRepo;

    public Mocker(ProjectRepo projectRepo, UserRepo userRepo, ProjectRepo projectRepo1, DiagramRepo diagramRepo, QuestionRepo questionRepo, StackOverslowService stackOverslowService, AnswerRepo answerRepo) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo1;
        this.diagramRepo = diagramRepo;
        this.questionRepo = questionRepo;
        this.stackOverslowService = stackOverslowService;
        this.answerRepo = answerRepo;
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
                .tags(List.of("Java", "WebSockets", "Android"))
                .build();

        projectRepo.saveAll(List.of(project1, project2, project3));

        Diagram classDiagram = new Diagram(Diagram.Type.CLASS, "class.jpg", "# Диаграмма классов \n вот тут диаграмма");
        classDiagram.setProject(project1);

        Diagram deploymentDiagram = new Diagram(Diagram.Type.DEPLOYMENT, "deployment.jpg", "# Deployment-диаграмма \n и тут тоже");
        deploymentDiagram.setProject(project1);

        diagramRepo.saveAll(List.of(classDiagram, deploymentDiagram));


        Question question1 = Question.builder()
                .name("Потеря контекста вызова")
                .content("""
                        Объясните, пожалуйста, почему после присвоения var f = obj1.f теряется контекст вызова и выводится undefined?
                        ```
                        var obj1 = {
                                                
                          x: 3,
                                                
                          f: function() {
                            return (this.x);
                          }
                        };
                                                
                        alert(obj1.f());
                        var f = obj1.f;
                        alert(f());
                        ```
                        """)
                .project(project1)
                .author(u2)
                .tags(List.of("JS"))
                .build();

        List<Answer> answers1 = List.of(
                Answer.builder()
                        .content("""
                                        Вкратце: в первом случае Вы вызываете функцию как метод объекта, во втором - берете функцию и саму по себе.
                                                                                
                                        Более многословно:
                                        Функции в javascript, в отличие от некоторых других популярных языков, являются так называемыми объектами первого класса. То есть они существуют и имеют смысл сами по себе, без привязки к объекту.
                                                                                
                                        Однако иногда возникает естественное желание вызвать функцию как метод какого-то объекта. Это значит, что функции нужен доступ к объекту, методом которого ее хотят сделать, чтобы пользоваться свойствами этого объекта например. Но функция у нас же сама по себе, то есть может вызываться как метод разных объектов, что же делать? Вот для этого было придумано ключевое слово this. Это можно понимать как объект, методом которого считается данная функция при данном конкретном вызове.
                                                                                
                                        Вызов функции сразу через точку myObject.myFunction() это просто сокращенный способ задания this сразу, этакий сахар. Когда Вы вызываете через точку на самом деле происходит примерно следующее:
                                        ```
                                        var func = myObject.myFunction; //Получаем функцию-свойство объекта myObject
                                        func.call(myObject); // Вызываем эту функцию с нужным контекстом.
                                        ```
                                        """)
                        .author(u3)
                        .question(question1)
                        .build(),
                Answer.builder()
                        .content("""
                                        Функция вызывается в контексте объета НЕ потому что она создана внутри объекта. Она вызывается в контексте объекта, потому что она вызывается как метод obj.func()
                                                                                
                                        Когда функция вызывется как метод, идентификатор this автоматически устанавливается на объект этого метода.
                                                                                
                                        Одна и та же функция может быть вызвана как методы разных объектов.
                                                                                
                                        ```
                                        function foo() {
                                          blah blah
                                        }
                                                                                
                                        x = {}; x.foo=foo; x.foo() //тут this будет установлен на объект х
                                        x2 = {}; x2.foo=foo; x2.foo() //тут this будет установлен на объект х2
                                        foo(); //тут this не будет установлен
                                        ```
                                        
                                        То же самое если функция изначально создается внутри объекта.Значения не имеет. Имеет значение только то как вызывается данная функция.
                                        """)
                        .author(u9)
                        .question(question1)
                        .build()
        );

        answerRepo.saveAll(answers1);


        question1.setAnswers(answers1);
        question1 = questionRepo.save(question1);
        stackOverslowService.vote(question1.getId(), u2.getId(), true);
        stackOverslowService.vote(question1.getId(), u3.getId(), true);
        stackOverslowService.setRightAnswer(question1.getId(), 1, u2.getId());

        Question question2 = Question.builder()
                .name("Java и Kotlin в одном android проекте")
                .content("""
                        Есть довольно большой Android проект, целиком написанный на Java. Сейчас мне очень интересен Kotlin, и я хотел бы продолжать проект на нём. Я знаю, что это возможно, что Kotlin, как Java при компиляции компилируется в байткод.
                                                
                        Однако, интересно, с какими "подводными камнями" можно при этом столкнуться. Например, увеличенное время компиляции, может быть повешение сложности поддержки проекта и т.д.
                        """)
                .author(u3)
                .project(project3)
                .tags(List.of("Android", "Kotlin"))
                .build();

        question2 = questionRepo.save(question2);
        stackOverslowService.vote(question2.getId(), u5.getId(), false);
        stackOverslowService.vote(question2.getId(), u8.getId(), false);

        Question question3 = Question.builder()
                .project(project2)
                .author(u6)
                .name("Многопоточное vs асинхронное программирование")
                .content("""
                        Хотелось бы узнать разницу между этими подходами. Разве асинхронное программирование не подразумевает из себя уже многопоточность, ведь Task где-то там по любому выполняется в отдельном потоке ?
                                                
                        В каких случаях нужно прибегать к многопоточному, а в каких к асинхронному программированию ?
                                                
                        И еще ко всему этому есть параллельное программирование, которая тоже вносит путаницу для меня. В чем её отличие ?
                        """)
                .tags(List.of("C#", "concurrency"))
                .build();

        question3 = questionRepo.save(question3);

        Question question4 = Question.builder()
                .project(project1)
                .author(u9)
                .name("На какие уязвимости, помимо SQL-инъекций и XSS, стоит проверить сайт?")
                .content("""
                        Давно не интересовался методами взлома, так что солидно отстал от жизни. Кто с чем сталкивался?
                        """)
                .build();

        question4 = questionRepo.save(question4);
    }
}
