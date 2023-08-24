package ISTP.repository;

import ISTP.domain.help.faq.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    List<Faq> findAllByFaqTypeId(Long faqTypeId);
}
