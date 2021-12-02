package ru.javastripters.onboarder.model;

import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(columnDefinition = "text")
    String name;

    @Column(columnDefinition = "text")
    String description;

    @Column(columnDefinition = "text")
    String direction;

    @ElementCollection
    List<String> tags;

    @Column(columnDefinition = "text")
    String goals;

    @Column(columnDefinition = "text")
    String instruments;

    @ElementCollection
    List<String> diagramPaths;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    @ToString.Exclude
    Set<User> users;

    protected Project() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
