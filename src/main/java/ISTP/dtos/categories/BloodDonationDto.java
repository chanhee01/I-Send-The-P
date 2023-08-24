package ISTP.dtos.categories;

import ISTP.domain.bloodDonation.BloodDonationCategories;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BloodDonationDto {

    private Long id;
    private String bloodDonation;

    public BloodDonationDto(BloodDonationCategories bloodDonationCategories) {
        this.id = bloodDonationCategories.getId();
        this.bloodDonation = bloodDonationCategories.getBloodDonationCategories();
    }
}
