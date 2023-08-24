package ISTP.controller;

import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questionTypeCategories")
public class QuestionTypeCategoriesController {
    private final QuestionService questionService;

    @GetMapping
    public List<QuestionTypeCategories> questionTypeCategories() {
        return questionService.findQuestionTypeAll();
    }
}
