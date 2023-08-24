package ISTP.service;

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
        Member member1 = new Member("loginId1", "password1", "test1", "별명1", 10, Gender.MAN, "010-1111-2222", BloodType.A_PLUS, "aaa@naver.com", "인천시", true);
        Member member2 = new Member("loginId2", "password2", "test2", "별명2", 20, Gender.WOMAN, "010-3333-4444", BloodType.B_PLUS, "bbb@naver.com", "서울시", true);
        memberService.save(member1);
        memberService.save(member2);
        QuestionTypeCategories questionTypeCategories1 = new QuestionTypeCategories(ACCOUNT);
        questionService.saveV2(questionTypeCategories1);
        QuestionTypeCategories questionTypeCategories2 = new QuestionTypeCategories(PROGRAM);
        questionService.saveV2(questionTypeCategories2);
        QuestionTypeCategories questionTypeCategories3 = new QuestionTypeCategories(SUGGESTION);
        questionService.saveV2(questionTypeCategories3);
        QuestionTypeCategories questionTypeCategories4 = new QuestionTypeCategories(ETC);
        questionService.saveV2(questionTypeCategories4);
        for(int i = 1; i <= 12; i++) {
            Question question;
            if(i <= 3) {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(ACCOUNT);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else if(i <= 6) {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(SUGGESTION);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member2);
            }
            else if(i <= 9) {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(PROGRAM);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member1);
            }
            else {
                QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(ETC);
                question = new Question("title" + i, "content" + i, questionTypeCategories, member2);
            }
            questionService.save(question);
        }
    }

    @Test
    public void findById() {
        QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(ACCOUNT);
        Question question = new Question("abc", "abc", questionTypeCategories, null);
        questionService.save(question);
        Question findQuestion = questionService.findById(question.getId());
        assertThat(findQuestion).isEqualTo(question);
    }

    @Test
    public void findByIdError() {
        assertThrows(IllegalArgumentException.class, () -> questionService.findById(14L));
    }

    @Test
    public void updateQuestion() {
        QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(ACCOUNT);
        Question question = new Question("abc", "abc", questionTypeCategories, null);
        questionService.save(question);
        QuestionTypeCategories updateQUestionTypeCategories = questionService.findByQuestionType(PROGRAM);
        questionService.updateQuestion(question, "updateTitle", "updateContent", updateQUestionTypeCategories);
        assertThat(question.getTitle()).isEqualTo("updateTitle");
    }

    @Test
    public void deleteQuestion() {
        QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(ACCOUNT);
        Question question = new Question("abc", "abc", questionTypeCategories, null);
        questionService.save(question);
        questionService.deleteQuestion(question);

        assertThrows(IllegalArgumentException.class, () -> questionService.findById(question.getId()));
    }

}