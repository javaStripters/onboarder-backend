package ru.javastripters.onboarder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javastripters.onboarder.dto.AnswerDto;
import ru.javastripters.onboarder.dto.QuestionDto;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.Question;
import ru.javastripters.onboarder.model.User;
import ru.javastripters.onboarder.repository.QuestionRepo;
import ru.javastripters.onboarder.repository.UserRepo;
import ru.javastripters.onboarder.service.StackOverslowService;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("overslow")
public class StackOverslowController {
    enum Sorting {
        NEW, ACTIVE, NO_ANSWER, SOLVED
    }

    private final StackOverslowService service;
    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;

    public StackOverslowController(StackOverslowService service, QuestionRepo questionRepo, UserRepo userRepo) {
        this.service = service;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto createQuestion(@RequestBody QuestionDto dto) {
        return new QuestionDto(service.createQuestion(dto));
    }

    @GetMapping("questions")
    public List<QuestionDto> getQuestions(
            @RequestParam(value = "sort", defaultValue = "NEW") Sorting sorting,
            @RequestHeader(value = "Authorization", defaultValue = "1") int userId
    ) {
        User user = userRepo.findUsersById(userId);
        Set<Project> projects = user.getProjects();

        List<Question> questions = switch (sorting) {
            case NEW -> questionRepo.findNew(projects);
            case ACTIVE -> questionRepo.findActive(projects);
            case NO_ANSWER -> questionRepo.findWithoutAnswer(projects);
            case SOLVED -> questionRepo.findSolved(projects);
        };

        return questions.stream().map(QuestionDto::new).collect(Collectors.toList());
    }

    @GetMapping("questions/search")
    public List<QuestionDto> search(@RequestParam("query") String query) {
        return questionRepo.fulltextSearch(query).stream().map(QuestionDto::new).collect(Collectors.toList());
    }

    @GetMapping("questions/{questionId}")
    public QuestionDto getQuestion(@PathVariable int questionId) {
        return new QuestionDto(service.getQuestion(questionId));
    }

    @PostMapping("questions/{questionId}/plus")
    public QuestionDto votePlus(@PathVariable int questionId, @RequestHeader(value = "Authorization", defaultValue = "1") int userId) {
        service.vote(questionId, userId, true);
        return new QuestionDto(service.getQuestion(questionId));
    };

    @PostMapping("questions/{questionId}/minus")
    public QuestionDto voteMinus(@PathVariable int questionId, @RequestHeader(value = "Authorization", defaultValue = "1") int userId) {
        service.vote(questionId, userId, false);
        return new QuestionDto(service.getQuestion(questionId));
    }

    @PostMapping("questions/{questionId}/answers")
    public AnswerDto addAnswer(
            @PathVariable int questionId,
            @RequestBody AnswerDto dto,
            @RequestHeader(value = "Authorization", defaultValue = "1") int userId
            ) {
        return new AnswerDto(service.createAnswer(questionId, dto, userId));
    }

    @GetMapping("questions/{questionId}/answers")
    public List<AnswerDto> getAnswers(@PathVariable int questionId) {
        return service.getAnswers(questionId).stream().map(AnswerDto::new)
                .sorted(Comparator.comparing(AnswerDto::getId))
                .collect(Collectors.toList());
    }

    @PostMapping("questions/{questionId}/answers/{answerId}")
    public List<AnswerDto> setRightAnswer(
            @PathVariable int questionId,
            @PathVariable int answerId,
            @RequestHeader(value = "Authorization", defaultValue = "1") int userId
    ) {
        return service
                .setRightAnswer(questionId, answerId, userId).stream().map(AnswerDto::new)
                .sorted(Comparator.comparing(AnswerDto::isRight).reversed().thenComparing(AnswerDto::getId))
                .collect(Collectors.toList());
    }
}
