package ru.otus.homework.exam.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalizedFileNameProvider {

    private final String questionsFileName;
    private final String questionsFileExtension;
    private final String locale;

    public LocalizedFileNameProvider(@Value("${questions.file.name}") String questionsFileName,
                                     @Value("${questions.file.extension}") String questionsFileExtension,
                                     @Value("${locale}") String locale) {
        this.questionsFileName = questionsFileName;
        this.questionsFileExtension = questionsFileExtension;
        this.locale = locale;
    }

    public String getLocalizedFileName() {
        StringBuilder stringBuilder = new StringBuilder(questionsFileName);
        if (StringUtils.isNotBlank(locale)) {
            stringBuilder.append("_").append(locale);
        }
        stringBuilder.append(".").append(questionsFileExtension);

        return stringBuilder.toString();
    }
}