package ISTP.dtos.categories;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BloodTypeDto {

    private Long id;
    private String bloodType;

    public BloodTypeDto(BloodTypeCategories bloodTypeCategories) {
        this.id = bloodTypeCategories.getId();
        this.bloodType = bloodTypeCategories.getBloodType();
    }
}
