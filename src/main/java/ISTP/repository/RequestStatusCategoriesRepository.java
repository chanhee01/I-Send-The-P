package ISTP.repository;

import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.help.question.QuestionTypeCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestStatusCategoriesRepository extends JpaRepository<RequestStatusCategories, Long> {
    RequestStatusCategories findByRequestStatus(String requestStatus);
}
