package ISTP.dtos.request;


import ISTP.domain.bloodDonation.request.Request;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestListDto {
    private Long bloodTypeId;
    private String title;
    private Long requestStatusId;
    private String content;
    private LocalDateTime createdTime;

    public RequestListDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
        this.requestStatusId = request.getRequestStatusId();
        this.content = request.getContent();
        this.createdTime = request.getCreateDate();
    }
}
