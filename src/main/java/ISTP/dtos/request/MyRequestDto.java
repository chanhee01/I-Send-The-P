package ISTP.dtos.request;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

//내가 요청한 리스트 - 마이페이제이서 보는 리스트임
@Data
public class MyRequestDto {
    private Long bloodTypeId;
    private String title;
    private RequestStatus status;
    private LocalDateTime createdTime;

    public MyRequestDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
        this.status = request.getStatus();
        this.createdTime = request.getCreateDate();
    }
}
