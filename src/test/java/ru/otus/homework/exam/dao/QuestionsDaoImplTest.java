package ru.otus.homework.exam.dao;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.exam.model.Question;
import ru.otus.homework.exam.utils.LocalizedFileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Тестируем QuestionsDaoImpl")
@ExtendWith(MockitoExtension.class)
public class QuestionsDaoImplTest {

    @Mock
    private LocalizedFileNameProvider localizedFileNameProvider;

    @Test
    @DisplayName("Тестируем метод getQuestions")
    void getQuestionsTest() {

        Mockito.when(localizedFileNameProvider.getLocalizedFileName())
                .thenReturn("testQuestions.csv");

        QuestionsDao questionsDao = new QuestionsDaoImpl(localizedFileNameProvider);

        List<Question> questions = questionsDao.getQuestions();

        assertNotNull(questions, "question list is null");
        assertNotEquals(0, questions.size(), "question list is empty");
        assertEquals(2, questions.size());
        assertEquals("Test question 1?", questions.get(0).getQuestion());
        assertEquals("answer2", questions.get(1).getAnswer());
    }
}