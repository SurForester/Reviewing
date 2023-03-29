import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class StudentRepository {

    private final EntityManagerFactory entityManagerFactory;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void close() {
        entityManagerFactory.close();
    }

    public List<Student> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("allUsers", Student.class).getResultList()
        );
    }

    public Student findById(long id) {
        return executeForEntityManager(
                em -> em.find(Student.class, id)
        );
    }

    public Student findByName(String name) {
        return executeForEntityManager(
                em -> em.find(Student.class, name)
        );
    }

    public void insert(Student student) {
        executeInTransaction(em -> em.persist(student));
    }

    public void update(Student student) {
        executeInTransaction(em -> em.merge(student));
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
