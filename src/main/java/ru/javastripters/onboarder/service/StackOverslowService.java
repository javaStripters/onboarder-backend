package ru.javastripters.onboarder.service;

import org.springframework.stereotype.Service;
import ru.javastripters.onboarder.dto.AnswerDto;
import ru.javastripters.onboarder.dto.QuestionDto;
import ru.javastripters.onboarder.model.Answer;
import ru.javastripters.onboarder.model.Question;
import ru.javastripters.onboarder.model.Rating;
import ru.javastripters.onboarder.model.User;
import ru.javastripters.onboarder.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StackOverslowService {
    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    private final RatingRepo ratingRepo;
    private final AnswerRepo answerRepo;

    public StackOverslowService(QuestionRepo questionRepo, UserRepo userRepo, ProjectRepo projectRepo, RatingRepo ratingRepo, AnswerRepo answerRepo) {
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
        this.ratingRepo = ratingRepo;
        this.answerRepo = answerRepo;
    }

    public Question createQuestion(QuestionDto dto) {
        Question newQuestion = Question.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .author(userRepo.findUsersById(dto.getAuthorId()))
                .project(projectRepo.findById(dto.getProjectId()))
                .tags(dto.getTags())
                .build();

        return questionRepo.save(newQuestion);
    }

    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    public Question getQuestion(int questionId) {
        return questionRepo.findById(questionId);
    }

    public Rating vote(int questionId, int userId, boolean plus) {
        Question question = questionRepo.findById(questionId);
        User voter = userRepo.findUsersById(userId);

        Rating rating = ratingRepo.findByUserAndQuestion(voter, question).orElse(new Rating(voter, question, 0));

        if (plus && rating.getVote() < 1)
            rating.setVote(rating.getVote() + 1);
        else if (!plus && rating.getVote() > -1)
            rating.setVote(rating.getVote() - 1);

        if (!question.getRatings().contains(rating))
            question.getRatings().add(rating);

        rating = ratingRepo.save(rating);
        questionRepo.save(question);
        return rating;
    }

    public Answer createAnswer(int questionId, AnswerDto answerDto, int userId) {
        Question question = questionRepo.findById(questionId);
        Answer answer = Answer.builder()
                .author(userRepo.findUsersById(userId))
                .question(question)
                .content(answerDto.getContent())
                .build();

        answer = answerRepo.save(answer);

        question.getAnswers().add(answer);
        questionRepo.save(question);

        return answer;
    }

    public List<Answer> getAnswers(int questionId) {
        return questionRepo.findById(questionId).getAnswers();
    }

    public List<Answer> setRightAnswer(int questionId, int answerId, int userId) {
        List<Answer> answers = questionRepo.findById(questionId).getAnswers().stream().map(ans -> {
            ans.setRight(false);
            if (ans.getId() == answerId)
                ans.setRight(true);

            return ans;
        }).collect(Collectors.toList());

        answerRepo.saveAll(answers);
        return answers;
    }
}
