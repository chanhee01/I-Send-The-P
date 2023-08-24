package ISTP.controller;

import ISTP.domain.help.question.QuestionTypeCategories;
import ISTP.dtos.categories.QuestionTypeDto;
import ISTP.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questionTypeCategories")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class QuestionTypeCategoriesController {
    private final QuestionService questionService;

    @GetMapping
    public List<QuestionTypeDto> questionTypeCategories() {
        List<QuestionTypeDto> questionTypeDtos = new ArrayList<>();
        List<QuestionTypeCategories> questionTypeAll = questionService.findQuestionTypeAll();
        for (QuestionTypeCategories questionTypeCategories : questionTypeAll) {
        QuestionTypeDto questionTypeDto = new QuestionTypeDto(questionTypeCategories);
            questionTypeDtos.add(questionTypeDto);
        }
        return questionTypeDtos;
    }
}
