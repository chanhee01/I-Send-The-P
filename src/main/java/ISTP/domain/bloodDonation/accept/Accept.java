package ISTP.domain.bloodDonation.accept;

import ISTP.domain.BaseEntity;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
public class Accept extends BaseEntity { // 헌혈 해주는 사람

    @Id
    @GeneratedValue
    @Column(name = "accept_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "accept_status_id")
    private Long acceptStatusId; //요청 수락 상태, 요청 수락, 요청 취소, 요청 완료

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Accept() {
    }

    public Accept(Member member, Request request, AcceptStatusCategories acceptStatus) {
        this.member = member;
        this.request = request;
        this.acceptStatusId = acceptStatus.getId();
    }

    public void update_request() {
        this.acceptStatusId = AcceptStatusName.ACCEPT_ID;
    }

    public void update_cancel() {
        this.acceptStatusId = AcceptStatusName.CANCEL_ID;
    }

    public void update_finish() {
        this.acceptStatusId = AcceptStatusName.COMPLETE_ID;
    }

    //==연관관계 메서드==//
    public void changeAccept(Member member) {
        this.member = member;
    }
}
