package ru.javastripters.onboarder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.Answer;

import java.util.List;

public interface AnswerRepo extends CrudRepository<Answer, Integer> {
    List<Answer> findAll();
}
