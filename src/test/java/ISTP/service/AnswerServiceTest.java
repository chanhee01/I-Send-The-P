package ISTP.service;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.BloodTypeName;
import ISTP.domain.help.Answer;
import ISTP.domain.help.question.Question;
import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ISTP.domain.help.question.QuestionTypeName.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AnswerServiceTest {

    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;
    @Autowired
    MemberService memberService;

    @BeforeEach
    public void before() {
        BloodTypeCategories bloodTypeCategoriesA = new BloodTypeCategories(BloodTypeName.A_PLUS);
        BloodTypeCategories bloodTypeCategoriesB = new BloodTypeCategories(BloodTypeName.A_PLUS);
        Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, true, "010-1111-2222", bloodTypeCategoriesA, "aaa@naver.com", "인천시", true);
        Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, false, "010-3333-4444", bloodTypeCategoriesB, "bbb@naver.com", "서울시", true);
        memberService.save(member1);
        memberService.save(member2);
        for(int i = 1; i <= 12; i++) {
            Question question;
            if(i <= 3) {
                QuestionTypeCategories questionTypeCategories = new QuestionTypeCategories(PARTICIPATION);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else if(i <= 6) {
                QuestionTypeCategories questionTypeCategories = new QuestionTypeCategories(ACCOUNT);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member2);
            }
            else if(i <= 9) {
                QuestionTypeCategories questionTypeCategories = new QuestionTypeCategories(COMMON_SENSE);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else {
                QuestionTypeCategories questionTypeCategories = new QuestionTypeCategories(PROGRAM);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member2);
            }
            questionService.save(question);
        }
        Question question1 = questionService.findById(1L);
        Answer answer1 = answerService.createAnswer("answer1", member1, question1);
        answerService.save(answer1);

        Question question2 = questionService.findById(2L);
        Answer answer2 = answerService.createAnswer("answer2", member2, question2);
        answerService.save(answer2);
        System.out.println("member1 = " + member1.getAnswers());
    }

    @Test
    void findAll() {
        List<Answer> all = answerService.findAll();
        assertThat(all.size()).isEqualTo(2);
    }
}