package ISTP.repository;

import ISTP.domain.help.question.QuestionTypeCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeCategoriesRepository extends JpaRepository<QuestionTypeCategories, Long> {

    QuestionTypeCategories findByQuestionType(String questionTypeName);
}
