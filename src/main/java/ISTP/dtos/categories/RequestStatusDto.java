package ISTP.dtos.categories;

import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestStatusDto {

    private Long id;
    private String requestStatus;

    public RequestStatusDto(RequestStatusCategories requestStatusCategories) {
        this.id = requestStatusCategories.getId();
        this.requestStatus = requestStatusCategories.getRequestStatus();
    }
}
