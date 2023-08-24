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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static ISTP.domain.bloodDonation.BloodTypeName.*;
import static ISTP.domain.bloodDonation.BloodTypeName.AB_MINUS;
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

    @PersistenceContext
    EntityManager em;

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

    @Test
    public void count() {

        BloodTypeCategories bloodTypeCategories1 = new BloodTypeCategories(A_PLUS);
        BloodTypeCategories bloodTypeCategories2 = new BloodTypeCategories(B_PLUS);
        BloodTypeCategories bloodTypeCategories3 = new BloodTypeCategories(O_PLUS);
        BloodTypeCategories bloodTypeCategories4 = new BloodTypeCategories(AB_PLUS);

        Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천", true);
        Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, false, "010-3333-4444", bloodTypeCategories1, "bbb@naver.com", "서울시", true);
        Member member3 = new Member("loginId3", "password3", "test3", "별명3", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천시", true);
        Member member4 = new Member("loginId4", "password4", "test4", "별명4", 20, false, "010-3333-4444", bloodTypeCategories1, "bbb@naver.com", "인천시", true);

        BloodTypeCategories bloodTypeCategories = new BloodTypeCategories(BloodTypeName.A_PLUS);
        RequestStatusCategories byRequestStatus = requestService.findByRequestStatus(COMPLETED);

        Request request1 = new Request(member2, "질병", "제목", "내용", LocalDateTime.now().plusDays(3),
                "1111-2222", "병원", byRequestStatus, bloodTypeCategories,
                "가족", "혈소판 헌혈", "인천");

        Request request2 = new Request(member3, "질병", "제목", "내용", LocalDateTime.now().plusDays(3),
                "1111-2222", "병원", byRequestStatus, bloodTypeCategories,
                "가족", "혈소판 헌혈", "인천");

        Request request3 = new Request(member4, "질병", "제목", "내용", LocalDateTime.now().plusDays(3),
                "1111-2222", "병원", byRequestStatus, bloodTypeCategories,
                "가족", "혈소판 헌혈", "인천");


        requestService.save(request1);
        requestService.save(request2);
        requestService.save(request3);

        AcceptStatusCategories byAcceptStatus = acceptService.findByAcceptStatus(AcceptStatusName.COMPLETED);


        Accept accept1 = new Accept(member4, request1, byAcceptStatus);
        Accept accept2 = new Accept(member4, request2, byAcceptStatus);
        Accept accept3 = new Accept(member4, request3, byAcceptStatus);


        Long count = acceptService.count(member1);
        System.out.println(count);
    }
}
