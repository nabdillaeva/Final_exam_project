package crud_lms.models;


import crud_lms.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private StudyFormat studyFormat;

    @Transient
    private long groupId;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Group group;

    public Student(String firstName, String lastName, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
