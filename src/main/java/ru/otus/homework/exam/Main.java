package ru.otus.homework.exam;

import ru.otus.homework.exam.service.ExamService;
import ru.otus.homework.exam.service.ExamServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        ExamService examService = context.getBean(ExamServiceImpl.class);

        System.out.println(examService.exam());
    }
}