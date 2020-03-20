package ru.otus.homework.exam.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.exam.model.Student;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final InputOutputServiceImpl inputOutputUtil;

    public Student registerStudent() {
        inputOutputUtil.printMessage("name.get");
        String name = inputOutputUtil.readLine();
        while (StringUtils.isBlank(name)) {
            inputOutputUtil.printMessage("name.error.empty");
            name = inputOutputUtil.readLine();
        }

        return new Student(name);
    }
}