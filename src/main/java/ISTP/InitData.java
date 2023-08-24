package ISTP;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusName;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.bloodDonation.request.RequestStatusName;
import ISTP.domain.board.Board;
import ISTP.domain.board.BoardType;
import ISTP.domain.help.Answer;
import ISTP.domain.help.question.Question;
import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.domain.member.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static ISTP.domain.bloodDonation.BloodTypeName.*;
import static ISTP.domain.bloodDonation.request.RequestStatusName.*;
import static ISTP.domain.help.question.QuestionTypeName.*;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.init();
    }
    @Component
    static class InitService {

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            BloodTypeCategories bloodTypeCategories1 = new BloodTypeCategories(A_PLUS);
            BloodTypeCategories bloodTypeCategories2 = new BloodTypeCategories(B_PLUS);
            BloodTypeCategories bloodTypeCategories3 = new BloodTypeCategories(O_PLUS);
            BloodTypeCategories bloodTypeCategories4 = new BloodTypeCategories(AB_PLUS);
            BloodTypeCategories bloodTypeCategories5 = new BloodTypeCategories(A_MINUS);
            BloodTypeCategories bloodTypeCategories6 = new BloodTypeCategories(B_MINUS);
            BloodTypeCategories bloodTypeCategories7 = new BloodTypeCategories(O_MINUS);
            BloodTypeCategories bloodTypeCategories8 = new BloodTypeCategories(AB_MINUS);
            em.persist(bloodTypeCategories1);
            em.persist(bloodTypeCategories2);
            em.persist(bloodTypeCategories3);
            em.persist(bloodTypeCategories4);
            em.persist(bloodTypeCategories5);
            em.persist(bloodTypeCategories6);
            em.persist(bloodTypeCategories7);
            em.persist(bloodTypeCategories8);

            AcceptStatusCategories acceptStatusCategories1 = new AcceptStatusCategories(AcceptStatusName.ACCEPT);
            AcceptStatusCategories acceptStatusCategories2 = new AcceptStatusCategories(AcceptStatusName.CANCEL);
            AcceptStatusCategories acceptStatusCategories3 = new AcceptStatusCategories(AcceptStatusName.COMPLETED);
            em.persist(acceptStatusCategories1);
            em.persist(acceptStatusCategories2);
            em.persist(acceptStatusCategories3);





            Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천", true);
            Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, false, "010-3333-4444", bloodTypeCategories2, "bbb@naver.com", "서울시", true);
            Member member3 = new Member("loginId3", "password3", "test3", "별명3", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천시", true);
            Member member4 = new Member("loginId4", "password4", "test4", "별명4", 20, false, "010-3333-4444", bloodTypeCategories2, "bbb@naver.com", "인천시", true);
            Member member5 = new Member("loginId5", "password5", "test5", "별명5", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천시", true);
            Member member6 = new Member("loginId6", "password6", "test6", "별명6", 20, false, "010-3333-4444", bloodTypeCategories2, "bbb@naver.com", "인천시", true);
            Member member7 = new Member("loginId7", "password7", "test7", "별명7", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천시", true);
            Member member8 = new Member("loginId8", "password8", "test8", "별명8", 20, false, "010-3333-4444", bloodTypeCategories2, "bbb@naver.com", "인천시", true);
            Member member9 = new Member("loginId9", "password9", "test9", "별명9", 10, true, "010-1111-2222", bloodTypeCategories1, "aaa@naver.com", "인천시", true);
            Member member10 = new Member("loginId10", "password10", "test10", "별명10", 20, false, "010-3333-4444", bloodTypeCategories2, "bbb@naver.com", "인천시", true);
            for(int i = 0; i  < 14; i++) {
                member1.countPlus();
            }
            for(int i = 0; i  < 12; i++) {
                member2.countPlus();
            }
            for(int i = 0; i  < 15; i++) {
                member3.countPlus();
            }
            for(int i = 0; i  < 12; i++) {
                member4.countPlus();
            }
            for(int i = 0; i  < 11; i++) {
                member5.countPlus();
            }
            for(int i = 0; i  < 20; i++) {
                member6.countPlus();
            }
            member1.changeAlarm();
            em.persist(member1);em.persist(member2);em.persist(member3);em.persist(member4);em.persist(member5);
            em.persist(member6);em.persist(member7);em.persist(member8);em.persist(member9);em.persist(member10);

            RequestStatusCategories requestStatusCategories1 = new RequestStatusCategories(APPLICATION);
            RequestStatusCategories requestStatusCategories2 = new RequestStatusCategories(PROGRESS);
            RequestStatusCategories requestStatusCategories3 = new RequestStatusCategories(COMPLETED);
            em.persist(requestStatusCategories1);
            em.persist(requestStatusCategories2);
            em.persist(requestStatusCategories3);
            Request request1 = new Request(member1, "sickness1", "title1", "content1", LocalDateTime.now().plusDays(1), "111-111", "나사렛병원", requestStatusCategories1, bloodTypeCategories1, "부", "혈소판1", "인천");
            Request request2 = new Request(member1, "sickness2", "title2", "content2", LocalDateTime.now().plusDays(2), "222-222", "나사렛병원", requestStatusCategories1, bloodTypeCategories2, "모", "혈소판2", "인천");
            Request request3 = new Request(member1, "sickness3", "title3", "content3", LocalDateTime.now().plusDays(3), "333-333", "나사렛병원", requestStatusCategories1, bloodTypeCategories1, "친구", "혈소판3", "인천");
            Request request4 = new Request(member2, "sickness4", "title4", "content4", LocalDateTime.now().plusDays(4), "444-444", "인하대병원", requestStatusCategories1, bloodTypeCategories1, "지인", "혈소판4", "인천");
            Request request5 = new Request(member2, "sickness5", "title5", "content5", LocalDateTime.now().plusDays(5), "555-555", "인하대병원", requestStatusCategories1, bloodTypeCategories1, "동생", "혈소판5", "인천");
            Request request6 = new Request(member2, "sickness6", "title6", "content6", LocalDateTime.now().plusDays(6), "666-666", "인하대병원", requestStatusCategories1, bloodTypeCategories2, "형", "혈소판6", "인천");

            member1.addRequest(request1);
            member1.addRequest(request2);
            member1.addRequest(request3);
            member2.addRequest(request4);
            member2.addRequest(request5);
            member2.addRequest(request6);

            em.persist(request1);
            em.persist(request2);
            em.persist(request3);
            em.persist(request4);
            em.persist(request5);
            em.persist(request6);

            QuestionTypeCategories questionTypeCategories1 = new QuestionTypeCategories(ACCOUNT);
            em.persist(questionTypeCategories1);
            QuestionTypeCategories questionTypeCategories2 = new QuestionTypeCategories(PROGRAM);
            em.persist(questionTypeCategories2);
            QuestionTypeCategories questionTypeCategories3 = new QuestionTypeCategories(SUGGESTION);
            em.persist(questionTypeCategories3);
            QuestionTypeCategories questionTypeCategories4 = new QuestionTypeCategories(ETC);
            em.persist(questionTypeCategories4);
            for(int i = 1; i <= 12; i++) {
                Board board;
                if(i <= 3) {
                    board = new Board("title" + i, "contentasdasdsadasdasdasd1" + i, BoardType.공지사항, member1);
                }
                else if(i <= 6) {
                    board = new Board("title" + i, "content1" + i, BoardType.인터뷰, member1);
                }
                else if(i <= 9) {

                    board = new Board("title" + i, "content1" + i, BoardType.공지사항, member2);
                }
                else {
                    board = new Board("title" + i, "content1" + i, BoardType.인터뷰, member2);
                }
                em.persist(board);
            }
            for(int i = 1; i <= 12; i++) {
                Question question;
                if(i <= 3) {
                    QuestionTypeCategories questionTypeCategories = em.find(QuestionTypeCategories.class, 1L);
                    question = new Question("title" + i, "content" + i, questionTypeCategories, member1);
                    if(i == 1) {
                        question.changeStatus();
                        Answer answer1 = new Answer("answer1", member1, question);
                        em.persist(answer1);
                    }
                    if(i == 2) {
                        question.changeStatus();
                        Answer answer2 = new Answer("answer2", member2, question);
                        em.persist(answer2);
                    }
                }
                else if(i <= 6) {
                    QuestionTypeCategories questionTypeCategories = em.find(QuestionTypeCategories.class, 2L);
                    question = new Question("title" + i, "content" + i, questionTypeCategories, member2);
                }
                else if(i <= 9) {
                    QuestionTypeCategories questionTypeCategories = em.find(QuestionTypeCategories.class, 3L);
                    question = new Question("title" + i, "content" + i, questionTypeCategories, member1);
                }
                else {
                    QuestionTypeCategories questionTypeCategories = em.find(QuestionTypeCategories.class, 4L);
                    question = new Question("title" + i, "content" + i, questionTypeCategories, member2);
                }
                em.persist(question);
            }

        }
    }

}
