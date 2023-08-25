package ISTP.domain.bloodDonation.request;

import ISTP.domain.BaseEntity;
import ISTP.domain.bloodDonation.BloodDonationCategories;
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
    private String title;
    private String registerNumber;
    private String hospitalName; // 병원명
    private String hospitalNumber;
    private Long bloodTypeId;
    private String bloodProduct; // 혈액제제
    private LocalDateTime deadLine; // 마감 날짜
    private String relationship;
    private String content; // 요청 사연
    private Long bloodDonationTypeId; // 어떤 헌혈인지;
    @Column(name = "request_status_id")
    private Long requestStatusId; //요청글 처리 상태 요청신청, 요청진행, 요청완료

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Request() {
    }

    public Request(String title, String registerNumber, String hospitalName, String hospitalNumber,
                   Long bloodTypeCategories, String bloodProduct, LocalDateTime deadLine,
                   String relationship, String content, Long bloodDonationCategories,
                   Long requestStatusCategories, Member member) {
        this.title = title;
        this.registerNumber = registerNumber;
        this.hospitalName = hospitalName;
        this.hospitalNumber = hospitalNumber;
        this.bloodTypeId = bloodTypeCategories;
        this.bloodProduct = bloodProduct;
        this.deadLine = deadLine;
        this.relationship = relationship;
        this.content = content;
        this.bloodDonationTypeId = bloodDonationCategories;
        this.requestStatusId = requestStatusCategories;
        this.member = member;
    }


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
