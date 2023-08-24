package ISTP.dtos.request;

import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestRe {

    private String title;
    private String registerNumber;
    private String hospitalName;
    private String hospitalNumber;
    private Long myBloodTypeId;
    private String bloodProduct;
    private LocalDateTime deadline;
    private String relationship;
    private String content;
    private Long bloodDonationTypeId;

    public RequestRe(Member member, Request request) {
        this.title = request.getTitle();
        this.registerNumber = request.getRegisterNumber();
        this.hospitalName = request.getHospitalName();
        this.hospitalNumber = request.getHospitalNumber();
        this.myBloodTypeId = member.getMyBloodTypeId();
        this.bloodProduct = request.getBloodProduct();
        this.deadline = request.getDeadLine();
        this.relationship = request.getRelationship();
        this.content = request.getContent();
        this.bloodDonationTypeId = request.getBloodDonationTypeId();
    }
}
