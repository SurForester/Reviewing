import javax.persistence.*;

@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "allUsers", query = "from Student")
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 128)
    private String name;
    @Column(length = 21)
    private String mark;

    public Student() {
    }

    public Student(Long id, String name, String mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
