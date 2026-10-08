package com.piyus.aopIntroduction.service;
import com.piyus.aopIntroduction.dto.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ExecutionTimeService implements StudentService {

    private LoggingDecorator loggingDecorator;

    public ExecutionTimeService(LoggingDecorator loggingDecorator) {
        this.loggingDecorator = loggingDecorator;
    }

    @Override
    public void createStudent(Student student) {
        long start = System.currentTimeMillis();

        loggingDecorator.createStudent(student);

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}