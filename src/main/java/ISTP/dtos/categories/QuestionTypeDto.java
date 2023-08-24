package ISTP.dtos.categories;

import ISTP.domain.help.question.QuestionTypeCategories;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionTypeDto {

    private Long id;
    private String questionType;

    public QuestionTypeDto(QuestionTypeCategories questionTypeCategories) {
        this.id = questionTypeCategories.getId();
        this.questionType = questionTypeCategories.getQuestionType();
    }
}
