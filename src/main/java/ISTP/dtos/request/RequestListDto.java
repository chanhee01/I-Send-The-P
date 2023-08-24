package ISTP.dtos.request;


import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestListDto {
    private Long bloodTypeId;
    private String title;
    private RequestStatus status;
    private LocalDateTime createdTime;

    public RequestListDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
        this.status = request.getStatus();
        this.createdTime = request.getCreateDate();
    }
}
