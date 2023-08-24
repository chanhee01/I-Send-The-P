package ISTP;

import ISTP.domain.bloodDonation.BloodDonationCategories;
import ISTP.domain.bloodDonation.BloodDonationName;
import ISTP.domain.banner.Banner;
import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusCategories;
import ISTP.domain.bloodDonation.accept.AcceptStatusName;
import ISTP.domain.bloodDonation.request.Request;
import ISTP.domain.bloodDonation.request.RequestStatusCategories;
import ISTP.domain.board.Board;
import ISTP.domain.help.Answer;
import ISTP.domain.help.faq.Faq;
import ISTP.domain.help.question.Question;
import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.domain.help.question.QuestionTypeName;
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
            BloodDonationCategories bloodDonationCategories1 = new BloodDonationCategories(BloodDonationName.WHOLE_BLOOD);
            BloodDonationCategories bloodDonationCategories2 = new BloodDonationCategories(BloodDonationName.PLATELET);
            BloodDonationCategories bloodDonationCategories3 = new BloodDonationCategories(BloodDonationName.PLATELET);
            BloodDonationCategories bloodDonationCategories4 = new BloodDonationCategories(BloodDonationName.PLASMA_PLATELET);
            em.persist(bloodDonationCategories1);
            em.persist(bloodDonationCategories2);
            em.persist(bloodDonationCategories3);
            em.persist(bloodDonationCategories4);

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

            Banner banner1 = new Banner("https://istp.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20230824_224713138_03.png");
            Banner banner2 = new Banner("https://istp.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20230824_224713138_01.jpg");
            Banner banner3 = new Banner("https://istp.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20230824_224713138_02.png");
            Banner banner4 = new Banner("https://istp.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20230824_224713138_04.png");

            em.persist(banner1);
            em.persist(banner2);
            em.persist(banner3);
            em.persist(banner4);

            Faq faq1 = new Faq("Q 1. 헌혈을 하려면 신분증이 꼭 필요한가요?", "A 1. 혈액관리법 시행규칙에 따라 개인신상정보가 확인된 분들만 헌혈에 참여하실 수 있도록 헌혈 전에 신분증을 확인합니다. 신분증을 확인함으로써 헌혈자는 헌혈기록 및 검사결과의 정확한 관리가 가능하고 수혈자는 대리헌혈 및 검사목적의 헌혈이 방지되어 안전한 혈액을 수혈 받을 수 있습니다.", QuestionTypeName.PARTICIPATION_ID);
            Faq faq2 = new Faq("Q 2. 지정 헌혈이란 무엇인가요?", "A 2. 의료기관 및 환자가 지정 의뢰한 헌혈지원자가 대한적십자사 혈액원에서 헌혈 후 그 혈액을 지정된 수혈자에게 수혈되는 헌혈을 말합니다. 본인이 지정헌혈자인 경우 의료기관이 발급한 '지정헌혈 안내문'을 환자 또는 보호자로부터 문자로 전송 받거나 혹은 출력물을 받아 지참하고 헌혈의집을 방문하여 주시기 바랍니다.\n" +
                    "\n" +
                    "* 지정헌혈 안내문에는 수혈자 등록번호 등 환자에게 필요한 정보를 포함하고 있으며, 지정헌혈 안내문을 지참하지  않은 경우에는 지정헌혈이 불가하니, 반드시 환자 또는 보호자에게 '지정헌혈 안내문'을 받아 오시는 것이 중요합니다.", QuestionTypeName.PARTICIPATION_ID);
            Faq faq3 = new Faq("Q 3. 헌혈하기 전에 주의해야 할 점은 무엇인가요?", "A 3. 과음과 과로를 하지 않고 제때에 식사를 한 상태가 좋습니다. 헌혈 전에 적어도 72시간 동안은 혈소판 기능을 저하시키는 아스피린, 치료목적의 약물을 복용하지 않아야 하며, 전신상태가 양호한 경우에 헌혈에 참여하실 수 있습니다. 단, 복용하신 약물의 종류나 기타 건강상태에 따라 헌혈 유보 기간이 달라질 수 있습니다.", QuestionTypeName.PARTICIPATION_ID);

            Faq faq4 = new Faq("Q. 1. 혈액형은 어떻게 결정되나요?", "A. 1. 우리가 현재 사용하고 있는 혈액형을 ABO 혈액형이라 합니다. 우리 몸에 있는 혈액에는 응집원과 응집소가 존재하는데, 응집원에는 A, B가 있고, 응집소에는 α,β가 있습니다. 응집원 A와 응집소 α가 만나면 응집하고, 응집원 B와 응집소 β가 만나면 응집하는데, 여기서 응집한다는 말은 피가 모여 덩어리가 된다는 것입니다. 우리 몸의 혈관에서 혈액이 응집하게 되면, 혈관이 막히게 되므로, 혈액이 응집하게 되면 매우 위험할 수 있습니다.\n" +
                    "응집원 A와 응집소 β를 가지고 있으면, A형, 응집원 B와 응집소 α를 가지고 있으면, B형이라 합니다. 응집원 A와 B를 모두 가지고 있으며, 응집소 α,β를 모두 가지고 있지 않으면, AB형. 응집원을 모두 가지고 있지 않으며, 응집소 α,β를 모두 가지고 있으면, O형입니다.", QuestionTypeName.COMMON_SENSE_ID);
            Faq faq5 = new Faq("Q. 2. 저희 혈액형에 +, -가 있는 것으로 알고 있는데, 이건 뭔가요?", "A. 2. 몸에 항원이 들어오면, 면역 체계에 의해, 이에 저항할 항체가 생성되게 됩니다. 응집원은 항원이고 응집소는 항체인데, RH 혈액형은 이 면역 반응을 통해 이해할 수 있는데, 붉은 털 원숭이의 피를 토끼에게 주입하였을 경우, 붉은 털 원숭이 혈액의 항원에 의해, 이에 저항할 항체가 토끼의 피에 생성됩니다. 이 토끼의 피를 채집한 후, 사람들의 피와 반응시켰을 때, 반응하여 응집하는 경우가 있고, 반응하지 않는 경우가 존재합니다. 사람들의 피와 토끼의 피가 반응하여 응집하면, 붉은털 원숭이와 동일한 항원이 있다는 뜻으로, RH+형이고, 토끼의 피와 응집하지 않으면, 붉은 털 원숭이와 동일한 항원이 없다는 뜻으로, RH-형입니다.", QuestionTypeName.COMMON_SENSE_ID);
            Faq faq6 = new Faq("Q. 3. 성분 헌혈이 무엇인가요?", "A. 3. 혈액의 성분 중 필요한 성분만 채취하는 헌혈로, 혈액을 일정량 뽑은 후, 원심 분리기를 이용하여, 필요한 성분을 여과한 후, 나머지 성분은 생리식염수에 섞어, 헌혈자에게 다시 돌려주는 헌혈입니다. 헌혈 시간은 대체로 30분~90분 이내이며, 전혈에 비해 신체적인 부담이 적은 편입니다. 일정 성분만 얻어내기 때문에, 회복 속도가 빨라 성분 헌혈의 주\n" +
                    "기는 2주이며, 연 24회까지 진행할 수 있습니다. 성분 헌혈은 혈장, 혈소판, 혈장+혈소판 헌혈로 구분되는데, 혈소판 성분 헌혈은 혈액의 혈소판을 채집하고, 혈소판 혈장 성분 헌혈은 혈액의 혈장과 혈소판을 동시에 채집합니다. 또한, 혈장 성분 헌혈은 혈액의 성분 내 혈장을 채집합니다.", QuestionTypeName.COMMON_SENSE_ID);

            Faq faq7 = new Faq("Q 1. 회원탈퇴는 어떻게 할 수 있나요?", "A 1. 로그인을 하신 후, 마이페이지 - 회원탈퇴 메뉴를 클릭하시면 됩니다. ", QuestionTypeName.ACCOUNT_ID);
            Faq faq8 = new Faq("Q 2. 이제까지 시행한 지정 헌혈의 횟수는 어떻게 확인할 수 있나요?", "A 2. 로그인 하신 후, 마이페이지 - 헌혈 정보 탭에 들어가시면, 누적 헌혈 횟수를 확인하실 수 있습니다.", QuestionTypeName.ACCOUNT_ID);
            Faq faq9 = new Faq("Q 3. 헌혈 횟수가 달라요~", "A 3. 헌혈횟수에 대한 오류 원인으로는 전자문진 시행 이전에 헌혈자들이 기록한 ‘헌혈기록카드’의 판독 오류와 주민등록번호 뒷자리 미기록에서 발생하는 경우가 가장 많습니다. 현재, 누락된 헌혈횟수가 있으시면 가까운 혈액원으로 연락해 주시기 바랍니다.", QuestionTypeName.ACCOUNT_ID);

            Faq faq10 = new Faq("Q 1. 로그인이 안돼요", "A 1. 홈페이지를 새로 고침하거나, 새 인터넷 창을 통해, 홈페이지에서 로그인을 시도해보시고, 이로도 해결되지 않으면, 1:1 고객센터로 문의해주세요.", QuestionTypeName.PROGRAM_ID);
            Faq faq11 = new Faq("Q 2. 헌혈 횟수가 많은데, 명예의 전당에 이름이 안 올라와요!", "A 2. 명예의 전당은 사용자의 마이 페이지에 기록된 누적 횟수의 크기에 따라, Top 100을 선정하여, 명예의 전당에 이름을 올려드리고 있습니다. 명예의 전당에 이름이 없다면, 고객님의 헌혈 횟수가 부족한 것으로 추정됩니다.", QuestionTypeName.PROGRAM_ID);

            em.persist(faq1);em.persist(faq2);em.persist(faq3);em.persist(faq4);em.persist(faq5);
            em.persist(faq6);em.persist(faq7);em.persist(faq8);em.persist(faq9);em.persist(faq10);em.persist(faq11);



            Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, true, "010-1111-2222", bloodTypeCategories5, "aaa@naver.com", "인천", true);
            Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, false, "010-3333-4444", bloodTypeCategories5, "bbb@naver.com", "서울시", true);
            Member member3 = new Member("loginId3", "password3", "test3", "별명3", 10, true, "010-1111-2222", bloodTypeCategories5, "aaa@naver.com", "인천시", true);
            Member member4 = new Member("loginId4", "password4", "test4", "별명4", 20, false, "010-3333-4444", bloodTypeCategories5, "bbb@naver.com", "인천시", true);
            Member member5 = new Member("loginId5", "password5", "test5", "별명5", 10, true, "010-1111-2222", bloodTypeCategories5, "aaa@naver.com", "인천시", true);
            Member member6 = new Member("loginId6", "password6", "test6", "별명6", 20, false, "010-3333-4444", bloodTypeCategories5, "bbb@naver.com", "인천시", true);
            Member member7 = new Member("loginId7", "password7", "test7", "별명7", 10, true, "010-1111-2222", bloodTypeCategories5, "aaa@naver.com", "인천시", true);
            Member member8 = new Member("loginId8", "password8", "test8", "별명8", 20, false, "010-3333-4444", bloodTypeCategories5, "bbb@naver.com", "인천시", true);
            Member member9 = new Member("loginId9", "password9", "test9", "별명9", 10, true, "01041539032", bloodTypeCategories1, "aaa@naver.com", "인천시", true);
            Member member10 = new Member("loginId10", "password10", "test10", "별명10", 20, false, "01076645199", bloodTypeCategories1, "bbb@naver.com", "인천시", true);
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

            Request request1 =  new Request("title1", "1111-1111", "hospital1",
                    "11-11", bloodTypeCategories1.getId(), "product1", LocalDateTime.now().plusDays(1),
                    "부", "content1", bloodDonationCategories1.getId(), requestStatusCategories1.getId(), member1 );

            Request request2 =  new Request("title2", "2222-2222", "hospital2",
                    "22-22", bloodTypeCategories1.getId(), "product2", LocalDateTime.now().plusDays(2),
                    "부", "content2", bloodDonationCategories1.getId(), requestStatusCategories1.getId(), member1 );

            Request request3 =  new Request("title3", "3333-3333", "hospital3",
                    "33-33", bloodTypeCategories1.getId(), "product3", LocalDateTime.now().plusDays(3),
                    "부", "content3", bloodDonationCategories2.getId(), requestStatusCategories1.getId(), member1 );

            Request request4 =  new Request("title4", "4444-4444", "hospital4",
                    "44-44", bloodTypeCategories1.getId(), "product4", LocalDateTime.now().plusDays(4),
                    "부", "content4", bloodDonationCategories2.getId(), requestStatusCategories1.getId(), member2 );

            Request request5 =  new Request("title5", "5555-5555", "hospital5",
                    "55-55", bloodTypeCategories1.getId(), "product5", LocalDateTime.now().plusDays(5),
                    "부", "content5", bloodDonationCategories3.getId(), requestStatusCategories1.getId(), member2 );

            Request request6 =  new Request("title6", "6666-6666", "hospital6",
                    "66-66", bloodTypeCategories1.getId(), "product6", LocalDateTime.now().plusDays(6),
                    "부", "content6", bloodDonationCategories1.getId(), requestStatusCategories1.getId(), member2 );

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

            QuestionTypeCategories questionTypeCategories1 = new QuestionTypeCategories(PARTICIPATION);
            em.persist(questionTypeCategories1);
            QuestionTypeCategories questionTypeCategories2 = new QuestionTypeCategories(COMMON_SENSE);
            em.persist(questionTypeCategories2);
            QuestionTypeCategories questionTypeCategories3 = new QuestionTypeCategories(ACCOUNT);
            em.persist(questionTypeCategories3);
            QuestionTypeCategories questionTypeCategories4 = new QuestionTypeCategories(PROGRAM);
            em.persist(questionTypeCategories4);
            for(int i = 1; i <= 12; i++) {
                Board board;
                if(i <= 3) {
                    board = new Board("title" + i, "contentasdasdsadasdasdasd1" + i, true, member1);
                }
                else if(i <= 6) {
                    board = new Board("title" + i, "content1" + i, false, member1);
                }
                else if(i <= 9) {

                    board = new Board("title" + i, "content1" + i, true, member2);
                }
                else {
                    board = new Board("title" + i, "content1" + i, false, member2);
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
