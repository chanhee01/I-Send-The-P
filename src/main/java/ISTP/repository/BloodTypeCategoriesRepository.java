package ISTP.repository;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodTypeCategoriesRepository extends JpaRepository<BloodTypeCategories, Long> {

    BloodTypeCategories findByBloodType(String bloodType);
}
