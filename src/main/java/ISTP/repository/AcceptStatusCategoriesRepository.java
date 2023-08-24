package ISTP.repository;

import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptStatusCategoriesRepository extends JpaRepository<AcceptStatusCategories, Long> {

    AcceptStatusCategories findByAcceptStatus(String acceptStatus);
}
