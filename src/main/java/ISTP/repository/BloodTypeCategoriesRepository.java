package ISTP.repository;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.help.question.QuestionTypeCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodTypeCategoriesRepository extends JpaRepository<BloodTypeCategories, Long> {

    BloodTypeCategories findByBloodType(String bloodType);
}
