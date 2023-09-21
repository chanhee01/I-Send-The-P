package ISTP.controller;

import ISTP.domain.help.Answer;
import ISTP.domain.help.faq.Faq;
import ISTP.domain.help.question.Question;
import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.domain.member.Member;
import ISTP.dtos.faq.FaqDto;
import ISTP.dtos.help.*;
import ISTP.login.SessionConst;
import ISTP.service.AnswerService;
import ISTP.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/helps")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class HelpController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final HttpSession session;

    //1:1 문의 글 작성
    @PostMapping
    public Long save(@Validated @RequestBody QuestionSaveForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            throw new IllegalArgumentException("문의글 작성 시 오류 발생");
        }
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long questionTypeId = form.getQuestionType();
        QuestionTypeCategories questionTypeCategories = questionService.find(questionTypeId);
        Long saveQuestionId = questionService.save(form.getTitle(), form.getContent(), questionTypeCategories, member);
        return saveQuestionId;
    }

    //1:1 문의내역 리스트
    @ResponseBody
    @GetMapping
    public List<QuestionSummaryDto> questionList(@RequestParam Long typeId) {
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        List<Question> questions = questionService.findAllByQuestionTypeId(member.getId(), typeId);
        List<QuestionSummaryDto> questionSummaryDtos = new ArrayList<>();
        for(Question question : questions) {
            QuestionSummaryDto questionSummaryDto = new QuestionSummaryDto(question);
            questionSummaryDtos.add(questionSummaryDto);
        }
        return questionSummaryDtos;
    }

    //문의 완료가 되지 않은 상태에서 1:1 문의글 수정하기
    @PatchMapping("/{questionId}")
    public Long editQuestion(@Validated @RequestBody QuestionEditForm form, BindingResult bindingResult, @PathVariable Long questionId) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            throw new IllegalArgumentException("문의글 수정 시 오류 발생");
        }
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long questionTypeId = form.getQuestionType();
        QuestionTypeCategories questionTypeCategories1 = questionService.find(questionTypeId);
        QuestionTypeCategories questionTypeCategories = questionService.findByQuestionType(questionTypeCategories1.getQuestionType());
        Question question = questionService.findById(questionId);
        questionService.updateQuestion(question, form.getTitle(), form.getContent(), questionTypeCategories);
        return question.getId();
    }

    //상세 문의내역
    @ResponseBody
    @GetMapping("/{questionId}")
    public HelpDto help(@PathVariable Long questionId) {
        Question question = questionService.findById(questionId);
        Answer answer = answerService.findByQuestionId(questionId);
        HelpDto helpDto = new HelpDto(question, answer);
        return helpDto;
    }

    //1:1문의내역 답변 달기
    @PostMapping("/{questionId}/answer")
    public Long answerSave(@Validated @RequestBody AnswerSaveForm form, BindingResult bindingResult, @PathVariable Long memberId, @PathVariable Long questionId) {
        if(bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            //에러처리 어케 할까여
            throw new IllegalArgumentException("게시글 작성 시 오류 발생");
        }
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member.getId() == 1) {
            Question question = questionService.findById(questionId);
            Answer answer = answerService.createAnswer(form.getContent(), member, question);
            return answer.getId();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    //faq 답변 보여주기
    @GetMapping("/faq")
    public List<FaqDto> faqList(@RequestParam Long typeId) {
        List<Faq> allByFaqTypeId = questionService.findAllByFaqTypeId(typeId);
        List<FaqDto> faqDtos = new ArrayList<>();
        for (Faq faq : allByFaqTypeId) {
            FaqDto faqDto = new FaqDto(faq);
            faqDtos.add(faqDto);
        }
        return faqDtos;
    }

}
