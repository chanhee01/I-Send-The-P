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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"faqType"})
public class FaqTypeCategories {

    @Id
    @GeneratedValue
    @Column(name = "faq_type_id")
    private Long id;
    @Column(name = "faq_type")
    private String faqType;

    public FaqTypeCategories(String faqType) {
        this.faqType = faqType;
    }
}
