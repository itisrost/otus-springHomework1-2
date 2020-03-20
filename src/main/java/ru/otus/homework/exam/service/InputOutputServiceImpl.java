package ru.otus.homework.exam.service;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.context.MessageSource;

public class InputOutputServiceImpl implements InputOutputService{

    private final MessageSource messageSource;
    private final PrintStream output;
    private final Scanner input;
    private final String locale;

    public InputOutputServiceImpl(MessageSource messageSource,
                                  PrintStream output,
                                  Scanner input,
                                  String locale) {
        this.messageSource = messageSource;
        this.output = output;
        this.input = input;
        this.locale = locale;
    }

    public String readLine() {
        return input.nextLine();
    }

    public void printLine(String text) {
        output.println(text);
    }

    public void printMessage(String messageName, Object... args){
        printLine(messageSource.getMessage(messageName, args, Locale.forLanguageTag(locale)));
    }
}