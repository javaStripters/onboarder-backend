package ru.javastripters.onboarder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import ru.javastripters.onboarder.model.Question;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Integer id;
    private int authorId;
    private int projectId;
    private String name;
    private String content;
    private int rating;
    private int answers;
    private List<String> tags;
    private Date createdAt;

    public QuestionDto(Question question) {
        id = question.getId();
        authorId = question.getAuthor().getId();
        projectId = question.getProject().getId();
        name = question.getName();
        content = question.getContent();
        rating = question.countRating();
        answers = question.getAnswers().size();
        tags = question.getTags();
        createdAt = question.getCreatedAt();
    }
}
