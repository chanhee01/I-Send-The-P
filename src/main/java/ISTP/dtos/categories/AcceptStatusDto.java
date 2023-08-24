package ISTP.dtos.categories;

import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcceptStatusDto {

    private Long id;
    private String acceptStatus;

    public AcceptStatusDto(AcceptStatusCategories acceptStatusCategories) {
        this.id = acceptStatusCategories.getId();
        this.acceptStatus = acceptStatusCategories.getAcceptStatus();
        //adfs
    }
}
