package ISTP.dtos.request;


import ISTP.domain.bloodDonation.request.Request;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestListDto {
    private Long bloodTypeId;
    private String title;
<<<<<<< HEAD
    private Long requestStatusId;
=======
    private String content;
    private RequestStatus status;
>>>>>>> a8c8b6f183ea0272b4f7dba5526b5692a82b01de
    private LocalDateTime createdTime;

    public RequestListDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
<<<<<<< HEAD
        this.requestStatusId = request.getRequestStatusId();
=======
        this.content = request.getContent();
        this.status = request.getStatus();
>>>>>>> a8c8b6f183ea0272b4f7dba5526b5692a82b01de
        this.createdTime = request.getCreateDate();
    }
}
