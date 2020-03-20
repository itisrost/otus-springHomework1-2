package ru.otus.homework.exam.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.exam.model.Question;
import ru.otus.homework.exam.utils.LocalizedFileNameProvider;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final LocalizedFileNameProvider localizedFileNameProvider;

    @Override
    public List<Question> getQuestions() {
        List<Question> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(getFileFromResources(localizedFileNameProvider.getLocalizedFileName()))) {
            while (scanner.hasNextLine()) {
                result.add(getQuestionFromLine(scanner.nextLine(), ";"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    private static Question getQuestionFromLine(String line, String delimeter) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimeter);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return new Question(values.get(0), values.get(1));
    }
}