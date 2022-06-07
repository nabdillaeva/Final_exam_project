package crud_lms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter @Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String courseName;

    private String duration;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @OneToMany(mappedBy = "course",orphanRemoval = true)
    private List<Group> groups = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "course", orphanRemoval = true)
    private Teacher teacher;

    @Transient
    private Long companyId;

    @Transient
    private Long groupId;

    public Course(Company company) {
        this.company = company;
    }

    public void setGroup(Group group) {
        this.groups.add(group);
    }
}
