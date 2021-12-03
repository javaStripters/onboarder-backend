package ru.javastripters.onboarder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.javastripters.onboarder.model.Answer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    int id;
    int authorId;
    int questionId;
    String content;
    boolean right;

    public AnswerDto(Answer answer) {
        id = answer.getId();
        authorId = answer.getAuthor().getId();
        questionId = answer.getQuestion().getId();
        content = answer.getContent();
        right = answer.isRight();
    }
}
