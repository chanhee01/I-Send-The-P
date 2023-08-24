package ISTP.repository;

import ISTP.domain.bloodDonation.BloodDonationCategories;
import ISTP.domain.bloodDonation.BloodTypeCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodDonationCategoriesRepository extends JpaRepository<BloodDonationCategories, Long> {

    BloodDonationCategories findByBloodDonationCategories(String bloodDonationCategories);
}
