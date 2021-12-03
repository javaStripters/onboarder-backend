package ru.javastripters.onboarder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ElementCollection
    @Builder.Default
    List<String> instruments = List.of(
                "https://github.com/javaStripters/onboarder-backend",
                "https://www.figma.com/file/5oLTHj6T8tINHN7xNfUnIU/Hacaton-training?node-id=245%3A8"
            );


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
            @Builder.Default
            @JsonManagedReference
    @ToString.Exclude
    List<Diagram> diagrams = new ArrayList<>();

    @OneToMany
    @Builder.Default
    @ToString.Exclude
    Set<Documents> documents = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    @ToString.Exclude
    @Builder.Default
            @JsonManagedReference
    List<User> users = new ArrayList<>();

    protected Project() {
    }

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
