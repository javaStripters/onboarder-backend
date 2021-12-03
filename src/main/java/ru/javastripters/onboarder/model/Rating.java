package ru.javastripters.onboarder.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    User user;

    @ManyToOne
    Question question;

    int vote;

    protected Rating() {}

    public Rating(User user, Question question, int vote) {
        this.user = user;
        this.question = question;
        this.vote = vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rating rating = (Rating) o;
        return id != null && Objects.equals(id, rating.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
