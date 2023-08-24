package ISTP.dtos.request;
import ISTP.domain.bloodDonation.request.Request;
import lombok.Data;

import java.time.LocalDateTime;

//내가 요청한 리스트 - 마이페이제이서 보는 리스트임
@Data
public class MyRequestDto {
    private Long bloodTypeId;
    private String title;
    private String content;
    private Long requestStatusId;
    private LocalDateTime createdTime;
    private Long id;

    public MyRequestDto(Request request) {
        this.bloodTypeId = request.getBloodTypeId();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.requestStatusId = request.getRequestStatusId();
        this.createdTime = request.getCreateDate();
        this.id = request.getId();
    }
}
