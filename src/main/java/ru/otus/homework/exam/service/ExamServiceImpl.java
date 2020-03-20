package ru.otus.homework.exam.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.exam.dao.QuestionsDao;
import ru.otus.homework.exam.model.Question;
import ru.otus.homework.exam.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionsDao questionsDao;
    private final RegistrationService registrationService;
    private final InputOutputService inputOutputService;
    private final String scoreToPass;

    private Student student;

    public ExamServiceImpl(@Value("${score.to.pass}") String scoreToPass,
                           QuestionsDao questionsDao,
                           RegistrationService registrationService,
                           InputOutputService inputOutputService) {
        this.scoreToPass = scoreToPass;
        this.questionsDao = questionsDao;
        this.registrationService = registrationService;
        this.inputOutputService = inputOutputService;
    }

    @Override
    public void exam() {
        student = registrationService.registerStudent();

        int score = takeExam(questionsDao.getQuestions());

        student.setScore(score);
        student.setExamPassed(score >= Integer.parseInt(scoreToPass));

        if (student.isExamPassed()) {
            inputOutputService.printMessage("exam.passed", student.getName(), score);
        } else {
            inputOutputService.printMessage("exam.failed", student.getName(), score);
        }
    }

    private int takeExam(List<Question> questions) {
        int score = 0;
        for (Question question : questions) {
            inputOutputService.printLine(question.getQuestion());
            if (StringUtils.equals(inputOutputService.readLine().toLowerCase(), question.getAnswer().toLowerCase())) {
                score++;
            }
        }
        return score;
    }
}