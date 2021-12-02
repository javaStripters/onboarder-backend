package ru.javastripters.onboarder.model;

import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

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
    List<String> tags = new ArrayList<>();

    @Column(columnDefinition = "text")
    @Builder.Default
    String goals = """
                   # Цели проекта\n\n
                   # Задачи проекта\n\n
                   # Описание проекта \n\n
                   """;

    @Column(columnDefinition = "text")
    String instruments;

    @ElementCollection
    List<String> diagramPaths = new ArrayList<>();

    @OneToMany
    Set<Documents> documents = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    @ToString.Exclude
    List<User> users = new ArrayList<>();

    protected Project() {}

    public Project addUsers(Collection<User> users) {
        this.users.addAll(users);
        return this;
    }

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
