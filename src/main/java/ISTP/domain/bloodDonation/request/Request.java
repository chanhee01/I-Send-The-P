package ISTP.domain.bloodDonation.request;

import ISTP.domain.BaseEntity;
import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static ISTP.domain.bloodDonation.request.RequestStatusName.*;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
public class Request extends BaseEntity { // 헌혈 요청

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private Long id;

    private String sickness;
    private String title;
    private String content; // 요청 사연
    private LocalDateTime duration; // 마감 날짜
    private String number; //환자 등록 번호
    private String hospital;
    @Column(name = "request_status_id")
    private Long requestStatusId; //요청글 처리 상태 요청신청, 요청진행, 요청완
    private Long bloodTypeId;
    private String relationship;
    private String requests_blood_type; // 무슨 헌혈인지
    private String address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Request() {
    }

    public Request(Member member, String sickness, String title, String content, LocalDateTime duration, String number, String hospital,
                   RequestStatusCategories requestStatus, BloodTypeCategories bloodType, String relationship, String requests_blood_type, String address) {
        this.member = member;
        this.sickness = sickness;
        this.title = title;
        this.content = content;
        this.duration = duration;
        this.number = number;
        this.hospital = hospital;
        this.requestStatusId = requestStatus.getId();
        this.bloodTypeId = bloodType.getId();
        this.relationship = relationship;
        this.requests_blood_type = requests_blood_type;
        this.address = address;
    } // createdTime도 생성자에서 받기


    public void update_request() {
        this.requestStatusId = APPLICATION_ID;
    }

    public void update_status() {
        this.requestStatusId = PROGRESS_ID;
    }

    public void update_finish() {
        this.requestStatusId = COMPLETED_ID;
    }

    public void changeRequest(Member member) {
        this.member = member;
    }
}
