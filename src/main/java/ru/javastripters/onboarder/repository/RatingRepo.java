package ru.javastripters.onboarder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.Question;
import ru.javastripters.onboarder.model.Rating;
import ru.javastripters.onboarder.model.User;

import java.util.Optional;

public interface RatingRepo extends CrudRepository<Rating, Integer> {
    Optional<Rating> findByUserAndQuestion(User user, Question question);
}
