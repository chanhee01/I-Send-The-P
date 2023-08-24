package ISTP.dtos.request;

import ISTP.domain.bloodDonation.request.Request;
import lombok.Data;

import java.time.LocalDateTime;

//내가 요청 수락한 리스 - 마이페이지에서 보는 리스트

@Data
public class MyAcceptDto {

    private Long bloodTypeId;
    private String title;
    private Long requestStatusId;
    private LocalDateTime createdTime;

    public MyAcceptDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
        this.requestStatusId = request.getRequestStatusId();
        this.createdTime = request.getCreateDate();
    }
}
