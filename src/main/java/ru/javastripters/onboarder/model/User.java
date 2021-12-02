package ru.javastripters.onboarder.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
@ToString
public class User {
    enum PrivilegeLevel {
        NEWBIE,
        MENTOR,
        OWNER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    String fullName;

    String profession;

    @ManyToOne(cascade = CascadeType.ALL)
    Department department;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    Set<Project> projects;

    protected User() {}

    public User(String fullName, String profession, Department department) {
        this.fullName = fullName;
        this.profession = profession;
        this.department = department;
        projects = new HashSet<>();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
