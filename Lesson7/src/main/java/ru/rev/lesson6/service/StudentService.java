package ru.rev.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rev.lesson6.persist.Student;
import ru.rev.lesson6.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return (List<Student>)(studentRepository.findAll());
    }

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
