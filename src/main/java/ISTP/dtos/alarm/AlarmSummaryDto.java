package ISTP.dtos.alarm;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlarmSummaryDto {

    private String title;
    private String content;
    private Long requestStatusId;
    private LocalDateTime createdTime;
    private boolean isRead;

    public AlarmSummaryDto(AcceptAndIsReadDto acceptAndIsReadDto) {
        title = acceptAndIsReadDto.getRequest().getTitle();
        content = acceptAndIsReadDto.getRequest().getContent();
        requestStatusId = acceptAndIsReadDto.getRequest().getRequestStatusId();
        createdTime = acceptAndIsReadDto.getRequest().getCreateDate();
        isRead = acceptAndIsReadDto.isRead();
    }
}
