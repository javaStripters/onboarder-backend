package ru.javastripters.onboarder.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.Question;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
    List<Question> findAll();
    Question findById(int id);

    @Query("from Question where project in ?1 order by createdAt desc")
    List<Question> findNew(Collection<Project> projects);

    @Query("from Question q where q.project in ?1 order by size(q.answers) desc")
    List<Question> findActive(Collection<Project> projects);

    @Query("from Question q where size(q.answers) = 0 and q.project in ?1 order by q.id")
    List<Question> findWithoutAnswer(Collection<Project> projects);

    @Query(value = "select * from question " +
            "inner join answer a on question.id = a.question_id " +
            "where a.is_right " +
            "and project_id in ?1", nativeQuery = true)
    List<Question> findSolved(Collection<Project> projects);

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
