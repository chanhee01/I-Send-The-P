package ISTP.service;

import ISTP.domain.help.Answer;
import ISTP.domain.help.faq.FaqTypeCategories;
import ISTP.domain.help.question.Question;
import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.repository.AnswerRepository;
import ISTP.repository.FaqTypeCategoriesRepository;
import ISTP.repository.QuestionRepository;
import ISTP.repository.QuestionTypeCategoriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionTypeCategoriesRepository questionTypeCategoriesRepository;
    private final FaqTypeCategoriesRepository faqTypeCategoriesRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    @Transactional
    public Long save(Question question) {
        Question saveQuestion = questionRepository.save(question);
        log.info("{} 1:1 문의글 생성", saveQuestion.getTitle());
        return saveQuestion.getId();
    }

    //질문 타입 생성하는 건데 미리 데이터넣어놀 거라 실제로 사용할일 거의 없음
    @Transactional
    public Long faqTypeSave(FaqTypeCategories faqTypeCategories) {
        FaqTypeCategories save = faqTypeCategoriesRepository.save(faqTypeCategories);
        return save.getId();
    }

    @Transactional
    public Long questionTypeSave(QuestionTypeCategories questionTypeCategories) {
        QuestionTypeCategories saveQuestionTypeCategories = questionTypeCategoriesRepository.save(questionTypeCategories);
        return saveQuestionTypeCategories.getId();
    }

    public Question findById(Long questionId) {
        Question findQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문의입니다"));
        log.info("아이디로 문의 찾기 {}", findQuestion);
        return findQuestion;
    }
    public QuestionTypeCategories findByQuestionType(String questionTypeName) {
        QuestionTypeCategories questionTypeCategories = questionTypeCategoriesRepository.findByQuestionType(questionTypeName);
        log.info("질문타입이름으로 질문타입 찾기 {}", questionTypeCategories);
        return questionTypeCategories;
    }

    public FaqTypeCategories findByFaqType(String faqType) {
        FaqTypeCategories byFaqType = faqTypeCategoriesRepository.findByFaqType(faqType);
        log.info("faq타입이름으로 질문타입 찾기 {}", byFaqType);
        return byFaqType;
    }
    public List<Question> findAll(Long memberId) {
        log.info("모든 문의 조회 나중에 작성된 시간 순으로 조회");
        return questionRepository.findAllByMemberIdOrderByCreateDateDesc(memberId);
    }

    @Transactional
    public void updateQuestion(Question question, String updateTitle, String updateContent, QuestionTypeCategories updateQuestionTypeCategories) {
        if(question.isAnswerStatus()) {
            log.info("이미 문의완료 상태이기에 수정할 수 없음");
            return;
        }
        question.updateQuestion(updateTitle, updateContent, updateQuestionTypeCategories);
        log.info("문의글 수정 x완료 {}", question);
    }
    @Transactional
    public void deleteQuestion(Question question) {
        log.info("{} 문의글 삭제", question.getId());
        questionRepository.delete(question);
    }

    @Transactional
    public void deleteQuestionWithAnswers(Long questionId) {
        List<Answer> answers = answerRepository.findV2ByQuestionId(questionId);

        for (Answer answer : answers) {
            answerRepository.delete(answer);
        }
        questionRepository.deleteById(questionId);
    }

}
