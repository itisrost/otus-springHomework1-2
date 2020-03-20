package ru.otus.homework.exam.config;

import java.util.Scanner;

import ru.otus.homework.exam.service.InputOutputServiceImpl;
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
        ms.setBasename("bundle");
        return ms;
    }

    @Bean
    public InputOutputServiceImpl consoleIO(@Value("${locale}") String locale) {

        return new InputOutputServiceImpl(messageSource(), System.out, new Scanner(System.in), locale);
    }
}