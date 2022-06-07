package crud_lms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;

    @Transient
    private long courseId;//list

    @ManyToOne(cascade = CascadeType.MERGE)
    private Course course;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students=new ArrayList<>();


    public void setStudent(Student student){
        this.students.add(student);
    }


}
