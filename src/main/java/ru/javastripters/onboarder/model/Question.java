package ru.javastripters.onboarder.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    User author;

    @ManyToOne
    Project project;

    @Column(columnDefinition = "text")
    String name;

    @Column(columnDefinition = "text")
    String content;

    @ElementCollection
            @Builder.Default
    List<String> tags = new ArrayList<>();

    @Builder.Default
    Date createdAt = new Date();

    @OneToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Builder.Default
    @ToString.Exclude
    List<Rating> ratings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Builder.Default
    @ToString.Exclude
    List<Answer> answers = new ArrayList<>();

    protected Question() {}

    public int countRating() {
        return ratings.stream().mapToInt(Rating::getVote).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return id != null && Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
