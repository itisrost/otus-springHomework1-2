package ru.otus.homework.exam.service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.exam.dao.QuestionsDao;
import ru.otus.homework.exam.model.Question;
import ru.otus.homework.exam.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionsDao questionsDao;

    private final MessageSource messageSource;

    private final String scoreToPass;

    private Student student;

    public ExamServiceImpl(@Value("${score.to.pass}") String scoreToPass, QuestionsDao questionsDao, MessageSource messageSource) {
        this.questionsDao = questionsDao;
        this.messageSource = messageSource;
        this.scoreToPass = scoreToPass;
    }

    @Override
    public String exam() {
        registerStudent();

        int score = takeExam(questionsDao.getQuestions());

        student.setScore(score);
        student.setExamPassed(score >= Integer.parseInt(scoreToPass));

        if (student.isExamPassed()) {
            return student.getName() + getMessageWithDefaultLocale("exam.passed") + student.getScore();
        } else {
            return student.getName() + getMessageWithDefaultLocale("exam.failed") + student.getScore();
        }
    }

    private void registerStudent() {
        System.out.println(getMessageWithDefaultLocale("name.get"));
        Scanner consoleScanner = new Scanner(System.in);
        String name = consoleScanner.nextLine();
        while (StringUtils.isBlank(name)) {
            System.out.println(getMessageWithDefaultLocale("name.error.empty"));
            name = consoleScanner.nextLine();
        }

        student = new Student(name);
    }

    private int takeExam(List<Question> questions){
        int score = 0;
        Scanner consoleScanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            if (StringUtils.equals(consoleScanner.nextLine().toLowerCase(), question.getAnswer().toLowerCase())) {
                score++;
            }
        }
        return score;
    }

    private String getMessageWithDefaultLocale(String messageName) {
        return messageSource.getMessage(messageName, null, Locale.getDefault());
    }
}