package ru.otus.homework.exam.dao;

import java.util.List;

import ru.otus.homework.exam.model.Question;

public interface QuestionsDao {

    List<Question> getQuestions();

}
