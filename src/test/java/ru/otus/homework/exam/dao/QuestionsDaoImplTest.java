package ru.otus.homework.exam.dao;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.exam.model.Question;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Тестируем QuestionsDaoImpl")
@ExtendWith(MockitoExtension.class)
public class QuestionsDaoImplTest {

    @Mock
    private MessageSource messageSource;

    @Test
    @DisplayName("Тестируем метод getQuestions")
    void getQuestionsTest() {

        Mockito.when(messageSource.getMessage("questions.file", null, Locale.getDefault()))
                .thenReturn("testQuestions.csv");

        QuestionsDao questionsDao = new QuestionsDaoImpl(messageSource);

        List<Question> questions = questionsDao.getQuestions();

        assertNotNull(questions, "question list is null");
        assertNotEquals(0, questions.size(), "question list is empty");
        assertEquals(2, questions.size());
        assertEquals("Test question 1?", questions.get(0).getQuestion());
        assertEquals("answer2", questions.get(1).getAnswer());
    }
}