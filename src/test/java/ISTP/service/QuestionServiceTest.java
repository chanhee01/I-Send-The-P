package ISTP.service;

import ISTP.domain.bloodDonation.BloodTypeCategories;
import ISTP.domain.bloodDonation.BloodTypeName;
import ISTP.domain.help.question.Question;
import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static ISTP.domain.help.question.QuestionTypeName.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;
    @Autowired
    MemberService memberService;

    @BeforeEach
    public void before() {
        BloodTypeCategories bloodTypeCategoriesA = new BloodTypeCategories(BloodTypeName.A_PLUS);
        BloodTypeCategories bloodTypeCategoriesB = new BloodTypeCategories(BloodTypeName.B_PLUS);
        Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, true, "010-1111-2222", bloodTypeCategoriesA.getId(), "aaa@naver.com", "인천시", true);
        Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, false, "010-3333-4444", bloodTypeCategoriesB.getId(), "bbb@naver.com", "서울시", true);
        memberService.save(member1);
        memberService.save(member2);
        QuestionTypeCategories questionTypeCategories1 = new QuestionTypeCategories(PARTICIPATION);
        questionService.questionTypeSave(questionTypeCategories1);
        QuestionTypeCategories questionTypeCategories2 = new QuestionTypeCategories(COMMON_SENSE);
        questionService.questionTypeSave(questionTypeCategories2);
        QuestionTypeCategories questionTypeCategories3 = new QuestionTypeCategories(ACCOUNT);
        questionService.questionTypeSave(questionTypeCategories3);
        QuestionTypeCategories questionTypeCategories4 = new QuestionTypeCategories(PROGRAM);
        questionService.questionTypeSave(questionTypeCategories4);
        for(int i = 1; i <= 12; i++) {
            Question question;
            if(i <= 3) {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(PARTICIPATION);
                questionService.save("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else if(i <= 6) {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(ACCOUNT);
                questionService.save("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else if(i <= 9) {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(COMMON_SENSE);
                questionService.save("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(PROGRAM);
                questionService.save("title" + i, "content" + i, questionTypeCategories, member1);
            }

        }
    }
    @Test
    public void findByIdError() {
        assertThrows(IllegalArgumentException.class, () -> questionService.findById(14L));
    }

    @Test
    public void updateQuestion() {
        QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(PARTICIPATION);
        Long saveQuestionId = questionService.save("abc", "abc", questionTypeCategories, null);
        Question question = questionService.findById(saveQuestionId);
        QuestionTypeCategories updateQUestionTypeCategories = questionService.findByQuestionType(COMMON_SENSE);
        questionService.updateQuestion(question, "updateTitle", "updateContent", updateQUestionTypeCategories);
        assertThat(question.getTitle()).isEqualTo("updateTitle");
    }

    @Test
    public void deleteQuestion() {
        QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(PARTICIPATION);
        Long saveQuestionId = questionService.save("abc", "abc", questionTypeCategories, null);
        Question question = questionService.findById(saveQuestionId);
        questionService.deleteQuestion(question);
        assertThrows(IllegalArgumentException.class, () -> questionService.findById(question.getId()));
    }


}