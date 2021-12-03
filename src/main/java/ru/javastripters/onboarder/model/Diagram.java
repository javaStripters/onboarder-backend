package ru.javastripters.onboarder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "diagram")
@Entity
@Getter
@Setter
@ToString
public class Diagram {
    public enum Type { CLASS, DEPLOYMENT }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Type diagramType;
    private String path;
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    protected Diagram() {}

    public Diagram(Type diagramType, String path, String description) {
        this.diagramType = diagramType;
        this.path = path;
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Diagram diagram = (Diagram) o;
        return id != null && Objects.equals(id, diagram.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}