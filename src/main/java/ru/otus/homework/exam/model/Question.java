package ru.otus.homework.exam.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Question {

    private final String question;
    private final String answer;
}
