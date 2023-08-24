package ISTP.service;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.BloodTypeName;
import ISTP.domain.bloodDonation.accept.Accept;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusName;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.bloodDonation.request.RequestStatusName;
import ISTP.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static ISTP.domain.bloodDonation.request.RequestStatusName.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AcceptServiceTest {

    @Autowired
    RequestService requestService;

    @Autowired
    MemberService memberService;

    @Autowired
    AcceptService acceptService;

    @BeforeEach
    public void before() {
        AcceptStatusCategories acceptStatusCategories1 = new AcceptStatusCategories(AcceptStatusName.ACCEPT);
        AcceptStatusCategories acceptStatusCategories2 = new AcceptStatusCategories(AcceptStatusName.CANCEL);
        AcceptStatusCategories acceptStatusCategories3 = new AcceptStatusCategories(AcceptStatusName.COMPLETED);
        acceptService.acceptTypeSave(acceptStatusCategories1);
        acceptService.acceptTypeSave(acceptStatusCategories2);
        acceptService.acceptTypeSave(acceptStatusCategories3);
    }

    @Test
    public void saveAcceptTest() {
        Member member = new Member("abc", "aaa");

        memberService.save(member);
        BloodTypeCategories bloodTypeCategories = new BloodTypeCategories(BloodTypeName.A_PLUS);
        RequestStatusCategories byRequestStatus = requestService.findByRequestStatus(RequestStatusName.APPLICATION);
        Request request = new Request(member, "질병", "제목","내용", LocalDateTime.now().plusDays(3),
                "1111-2222", "병원", byRequestStatus, bloodTypeCategories,
                "가족", "혈소판 헌혈", "인천");

        requestService.save(request);
        AcceptStatusCategories byAcceptStatus = acceptService.findByAcceptStatus(AcceptStatusName.ACCEPT);
        Accept accept = new Accept(member, request, byAcceptStatus);

        Long id = acceptService.save(accept);

        Accept findAccept = acceptService.findById(id);
        assertThat(id).isEqualTo(findAccept.getId());
    }

    @Test
    public void update_test() {
        Member member = new Member("abc", "aaa");

        memberService.save(member);
        BloodTypeCategories bloodTypeCategories = new BloodTypeCategories(BloodTypeName.A_PLUS);
        RequestStatusCategories byRequestStatus = requestService.findByRequestStatus(RequestStatusName.APPLICATION);
        Request request = new Request(member, "질병", "제목","내용", LocalDateTime.now().plusDays(3),
                "1111-2222", "병원", byRequestStatus, bloodTypeCategories,
                "가족", "혈소판 헌혈", "인천");

        requestService.save(request);

        AcceptStatusCategories byAcceptStatus1 = acceptService.findByAcceptStatus(AcceptStatusName.ACCEPT);
        AcceptStatusCategories byAcceptStatus2 = acceptService.findByAcceptStatus(AcceptStatusName.CANCEL);
        AcceptStatusCategories byAcceptStatus3 = acceptService.findByAcceptStatus(AcceptStatusName.COMPLETED);
        Accept accept = new Accept(member, request, byAcceptStatus1);

        Long id = acceptService.save(accept);
        Accept findAccept = acceptService.findById(id);
        findAccept.update_finish();
        assertThat(findAccept.getAcceptStatusId() == AcceptStatusName.COMPLETE_ID);
        findAccept.update_cancel();
        assertThat(findAccept.getAcceptStatusId() == AcceptStatusName.CANCEL_ID);
        findAccept.update_request();
        assertThat(findAccept.getAcceptStatusId() == AcceptStatusName.ACCEPT_ID);
    }
}
