package crud_lms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToMany
    private List<Course> courses;

    @OneToMany(mappedBy = "group")
    private List<Student> students;


}
