import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        StudentRepository studentRepository = new StudentRepository(entityManagerFactory);

        for (int i = 0; i < 1000; i++) {
            studentRepository.insert(new Student(null, "Student N"+i, "marked"));
        }

        Student student = new Student(2L, "Another student", "nomark");
        studentRepository.update(student);

        studentRepository.delete(15L);

        for (Student studentLst : studentRepository.findAll()) {
            System.out.println(studentLst);
        }

        studentRepository.close();
    }

}
