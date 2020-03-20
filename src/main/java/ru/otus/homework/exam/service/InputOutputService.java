package ru.otus.homework.exam.service;

public interface InputOutputService {

    String readLine();
    void printLine(String text);
    void printMessage(String messageName, Object... args);
}