package ru.otus.homework.exam.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Student {

    private final String name;
    private int score;
    private boolean examPassed;
}
