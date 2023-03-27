package ru.rev.lesson6.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rev.lesson6.persist.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
