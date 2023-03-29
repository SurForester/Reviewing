package ru.rev.lesson6.converters;

import org.springframework.stereotype.Component;
import ru.rev.lesson6.persist.Student;
import ru.rev.lesson6.persist.StudentDTO;

@Component
public class StudentToDto {

    public StudentDTO studentToDTO(Student student) {
        return new StudentDTO().builder()
                .id(student.getId())
                .name(student.getName())
                .mark(student.getMark())
                .build();
    }

    public Student DTOToStudent(StudentDTO studentDTO) {
        return new Student().builder()
                .id(studentDTO.getId())
                .name(studentDTO.getName())
                .mark(studentDTO.getMark())
                .build();
    }

}
