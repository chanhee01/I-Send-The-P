package ISTP.dtos.request;

import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDto {
    private Long requestStatusId;
    private String title;
    private String sickness;
    private String number;
    private String hospital;
    private Long bloodTypeId;
    private LocalDateTime duration;
    private String relationship;
    private String content;
    private String requestsBloodType;
    private LocalDateTime createdTime;
    private String nickname;
    private String phone_number;

    public RequestDto(Request request, Member member) {
        this.requestStatusId = request.getRequestStatusId();
        this.title = request.getTitle();
        this.sickness = request.getSickness();
        this.number = request.getNumber();
        this.hospital = request.getHospital();
        this.bloodTypeId = request.getBloodTypeId();
        this.duration = request.getDuration();
        this.relationship = request.getRelationship();
        this.content = request.getContent();
        this.requestsBloodType = request.getRequestsBloodType();
        this.createdTime = request.getCreateDate();
        this.nickname = member.getNickname();
        this.phone_number = member.getPhoneNumber();
    }
}
