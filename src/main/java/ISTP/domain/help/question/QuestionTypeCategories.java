package ISTP.domain.help.question;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"questionType"})
public class QuestionTypeCategories {

    @Id
    @GeneratedValue
    @Column(name = "question_type_id")
    private Long id;
    @Column(name = "question_type_name")
    private String questionType;


    public QuestionTypeCategories(String questionType) {
        this.questionType = questionType;
    }

}
