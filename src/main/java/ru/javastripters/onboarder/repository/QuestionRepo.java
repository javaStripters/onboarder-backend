package ru.javastripters.onboarder.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.Question;

import java.util.List;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
    List<Question> findAll();
    Question findById(int id);

    @Query("from Question order by createdAt desc")
    List<Question> findNew();

    @Query("from Question q order by q.answers.size desc")
    List<Question> findActive();

    @Query("from Question where answers.size = 0 order by id")
    List<Question> findWithoutAnswer();

    @Query(value = "select * from question " +
            "inner join answer a on question.id = a.question_id " +
            "where a.is_right", nativeQuery = true)
    List<Question> findSolved();

    @Query(value = """
            select * from question
            where to_tsvector('russian', content || question.name) @@ plainto_tsquery('russian', ?1)
            or to_tsvector('english', content || question.name) @@ plainto_tsquery('english', ?1)
            order by greatest(
                ts_rank(to_tsvector('russian', content || question.name), plainto_tsquery('russian', ?1)),
                ts_rank(to_tsvector('english', content || question.name), plainto_tsquery('english', ?1))
            )""", nativeQuery = true)
    List<Question> fulltextSearch(String query);
}
