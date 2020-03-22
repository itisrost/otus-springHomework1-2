package ru.otus.homework.exam.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.exam.model.Student;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final InputOutputServiceImpl inputOutputService;

    public Student registerStudent() {
        inputOutputService.printMessage("name.get");
        String name = inputOutputService.readLine();
        while (StringUtils.isBlank(name)) {
            inputOutputService.printMessage("name.error.empty");
            name = inputOutputService.readLine();
        }

        return new Student(name);
    }
}