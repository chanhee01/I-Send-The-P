package ISTP.dtos.request;

import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class RequestDto {
    private Long requestStatusId;
    private String title;
    private String nickname;
    private String registerNumber;
    private String hospitalName;
    private String hospitalNumber;
    private Long bloodTypeId;
    private String bloodProduct;
    private LocalDateTime deadline;
    private String relationship;
    private String content;
    private Long bloodDonationTypeId;
    private LocalDateTime createdTime;
    private String phone_number;

    public RequestDto(Request request, Member member) {
        this.requestStatusId = request.getRequestStatusId();
        this.title = request.getTitle();
        this.nickname = member.getNickname();
        this.registerNumber = request.getRegisterNumber();
        this.hospitalName = request.getHospitalName();
        this.hospitalNumber = request.getHospitalNumber();
        this.bloodTypeId = request.getBloodTypeId();
        this.bloodProduct = request.getBloodProduct();
        this.deadline = request.getDeadLine();
        this.relationship = request.getRelationship();
        this.content = request.getContent();
        this.bloodDonationTypeId = request.getBloodDonationTypeId();
        this.createdTime = request.getCreateDate();
        this.phone_number = member.getPhoneNumber();
    }
}
