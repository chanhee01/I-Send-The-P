package ISTP.dtos.faq;


import ISTP.domain.help.faq.Faq;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class FaqDto {

    private String question;
    @Length(max = 10000)
    private String answer;

    public FaqDto(Faq faq) {
        this.question = faq.getQuestion();
        this.answer = faq.getAnswer();
    }
}
