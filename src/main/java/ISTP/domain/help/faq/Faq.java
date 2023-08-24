package ISTP.domain.help.faq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(of = {"id", "question", "answer", "faqTypeId" })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq {

    @Id
    @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    private String question;
    @Column(length = 1000)
    private String answer;

    @Column(name = "faq_type_id")
    private Long faqTypeId;

    public Faq(String question, String answer, Long faqTypeId) {
        this.question = question;
        this.answer = answer;
        this.faqTypeId = faqTypeId;
    }
}
