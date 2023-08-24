package ISTP.repository;

import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.help.faq.FaqTypeCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqTypeCategoriesRepository extends JpaRepository<FaqTypeCategories, Long> {
    FaqTypeCategories findByFaqType(String faqType);
}
