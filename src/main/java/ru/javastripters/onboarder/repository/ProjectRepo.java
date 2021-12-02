package ru.javastripters.onboarder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.Project;
import ru.javastripters.onboarder.model.User;

import java.util.List;

public interface ProjectRepo extends CrudRepository<Project, Integer> {
    List<Project> findAll();
}
