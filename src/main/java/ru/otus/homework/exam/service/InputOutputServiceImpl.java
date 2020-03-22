package ru.otus.homework.exam.service;

import java.io.PrintStream;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.exam.utils.LocalizationProperties;
import org.springframework.context.MessageSource;

@RequiredArgsConstructor
public class InputOutputServiceImpl implements InputOutputService{

    private final MessageSource messageSource;
    private final PrintStream output;
    private final Scanner input;
    private final LocalizationProperties localizationProperties;

    public String readLine() {
        return input.nextLine();
    }

    public void printLine(String text) {
        output.println(text);
    }

    public void printMessage(String messageName, Object... args){
        printLine(messageSource.getMessage(messageName, args, localizationProperties.getLocale()));
    }
}