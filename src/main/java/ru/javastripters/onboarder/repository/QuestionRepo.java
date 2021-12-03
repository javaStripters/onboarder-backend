package ru.javastripters.onboarder.repository;


import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
}
