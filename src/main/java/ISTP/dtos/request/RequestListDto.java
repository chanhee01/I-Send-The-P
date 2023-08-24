package ISTP.dtos.request;


import ISTP.domain.bloodDonation.request.Request;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestListDto {
    private Long bloodTypeId;
    private String title;
    private String content;
    private Long requestStatusId;
    private LocalDateTime createdTime;
    private Long id;

    public RequestListDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.requestStatusId = request.getRequestStatusId();
        this.createdTime = request.getCreateDate();
        this.id = request.getId();
    }
}
