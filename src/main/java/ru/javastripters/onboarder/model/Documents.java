package ru.javastripters.onboarder.model;

import javax.persistence.*;

@Entity
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer integer;

    String path;

    @ManyToOne
    Project project;
}
