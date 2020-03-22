package ru.otus.homework.exam.config;

import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.exam.service.InputOutputServiceImpl;
import ru.otus.homework.exam.utils.LocalizationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class UtilsConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public LocalizationProperties localizationProperties(@Value("${questions.file.name}") String questionsFileName,
                                                         @Value("${questions.file.extension}") String questionsFileExtension,
                                                         @Value("${locale}") String locale) {
        if (StringUtils.isBlank(locale)) {
            return new LocalizationProperties(questionsFileName, questionsFileExtension, Locale.getDefault());
        } else {
            return new LocalizationProperties(questionsFileName, questionsFileExtension, Locale.forLanguageTag(locale));
        }
    }

    @Bean
    public InputOutputServiceImpl consoleIO(LocalizationProperties localizationProperties) {

        return new InputOutputServiceImpl(messageSource(), System.out, new Scanner(System.in), localizationProperties);
    }
}